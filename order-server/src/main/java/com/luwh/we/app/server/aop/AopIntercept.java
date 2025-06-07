package com.luwh.we.app.server.aop;

import com.luwh.we.app.common.annotations.AopLogIgnore;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author lu.wh
 * @date 2023/09/27 16/02/06
 * @description
 */
@Aspect
@Configuration
public class AopIntercept {

    private Logger logger = LoggerFactory.getLogger(AopIntercept.class);
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Resource
    private HttpServletRequest httpRequest;

    @Pointcut(value = "execution(public * com.luwh.we.app.server.controller..*.*(..))")
    public void admin() {

    }

    @Around("admin()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Method visitMethod = findVisitMethod(joinPoint);
        String method = analyseRequestMethod(visitMethod);
        // log aop
        if (!shouldIgnoreLog(visitMethod)) {
            String logRequestBody = extractLogRequestBody(method, visitMethod, joinPoint);
            logger.info("visit {} - {} start, args: {{}}", httpRequest.getRequestURI(), method,
                    logRequestBody);
        }
        // 这里可以把日志写入到 队列里，然后由一个线程池负责从队列写入到kafka,考虑
        //  需要大量并发写入，队列爆满的场景
        // 暂定解决方案： 可以采取分为多个队列，以及线程池增长的情况，建议自定义一个线程池
        Object proceed = joinPoint.proceed();
        if (!shouldIgnoreLog(visitMethod)) {
            logger.info("visit {} - {} finish, cost:[{}] ms", httpRequest.getRequestURI(), method, System.currentTimeMillis() - start);
        }
        return proceed;
    }

    private String extractLogRequestBody(String method, Method visitMethod, ProceedingJoinPoint joinPoint) {
        StringBuilder logBody = new StringBuilder();
        HttpMethod resolve = HttpMethod.resolve(method);
        switch (Objects.requireNonNull(resolve)) {
            case GET:
                String[] parameterNames = parameterNameDiscoverer.getParameterNames(visitMethod);
                if (parameterNames == null || parameterNames.length == 0) {
                    break;
                }
                Object[] args = joinPoint.getArgs();
                for (int i = 0; i < parameterNames.length; i++) {
                    if (i > 0) {
                        logBody.append(", ");
                    }
                    logBody.append(parameterNames[i]);
                    logBody.append("=");
                    logBody.append(args[i]);
                }
                break;
            case POST:
            case PUT:
                logBody.append(Arrays.toString(joinPoint.getArgs()));
                break;
            default:
                break;
        }
        return logBody.toString();
    }

    private String analyseRequestMethod(Method visitMethod) {
        if (visitMethod.isAnnotationPresent(GetMapping.class)) {
            return HttpMethod.GET.name();
        } else if (visitMethod.isAnnotationPresent(PostMapping.class)) {
            return HttpMethod.POST.name();
        } else if (visitMethod.isAnnotationPresent(DeleteMapping.class)) {
            return HttpMethod.DELETE.name();
        } else if (visitMethod.isAnnotationPresent(PutMapping.class)) {
            return HttpMethod.PUT.name();
        } else if (visitMethod.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping declaredAnnotation = visitMethod.getDeclaredAnnotation(RequestMapping.class);
            RequestMethod[] requestMethods = declaredAnnotation.method();
            return requestMethods[0].name();
        }
        return null;
    }

    private boolean shouldIgnoreLog(Method visitMethod) {
        if (visitMethod == null) {
            // Can not find request method, not log record
            return true;
        }
        Annotation[] declaredAnnotations = visitMethod.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if (declaredAnnotation.annotationType() == AopLogIgnore.class) {
                return true;
            }
        }
        return false;
    }

    private Method findVisitMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Method[] declaredMethods = joinPoint.getSignature().getDeclaringType().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (name.equals(declaredMethod.getName())) {
                return declaredMethod;
            }
        }
        return null;
    }
}
