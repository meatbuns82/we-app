package com.luwh.we.app.server.aop;

import com.luwh.we.app.common.annotations.AopLogIgnore;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lu.wh
 * @date 2023/09/27 16/02/06
 * @description
 */
@Aspect
@Configuration
public class AopIntercept {

    private Logger logger = LoggerFactory.getLogger(AopIntercept.class);

    @Pointcut(value = "execution(public * com.luwh.we.app.server.controller..*.*(..))")
    public void admin() {

    }

    @Around("admin()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Method visitMethod = findVisitMethod(joinPoint);
        String method = null;
        // log aop
        if (visitMethod.isAnnotationPresent(GetMapping.class)) {
            method = HttpMethod.GET.name();
        } else if (visitMethod.isAnnotationPresent(PostMapping.class)) {
            method = HttpMethod.POST.name();
        } else if (visitMethod.isAnnotationPresent(DeleteMapping.class)) {
            method = HttpMethod.DELETE.name();
        } else if (visitMethod.isAnnotationPresent(PutMapping.class)) {
            method = HttpMethod.PUT.name();
        } else if (visitMethod.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping declaredAnnotation = visitMethod.getDeclaredAnnotation(RequestMapping.class);
            RequestMethod[] requestMethods = declaredAnnotation.method();
            method = requestMethods[0].name();
        }

        if(!shouldIgnoreLog(visitMethod)){
            logger.info("visit {} {}{ start, args{}.........", visitMethod.getDeclaringClass().getName() + "." + visitMethod.getName(), method,
                    (joinPoint.getArgs().getClass().isArray()) ? Arrays.toString(joinPoint.getArgs()) : Arrays.stream(joinPoint.getArgs()).toArray());
        }
        // 这里可以把日志写入到 队列里，然后由一个线程池负责从队列写入到kafka,考虑
        //  需要大量并发写入，队列爆满的场景
        // 暂定解决方案： 可以采取分为多个队列，以及线程池增长的情况，建议自定义一个线程池
        try {
            Object proceed = joinPoint.proceed();
            if(!shouldIgnoreLog(visitMethod)){
                logger.info("visit {} {} finish, cost:{} ms", visitMethod.getDeclaringClass().getName() + "." + visitMethod.getName(), method, System.currentTimeMillis() - start);
            }
            return proceed;
        } catch (Throwable e) {
            throw new OrderException(e.toString());
        }
    }

    private boolean shouldIgnoreLog(Method visitMethod) {
        Annotation[] declaredAnnotations = visitMethod.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if(declaredAnnotation.annotationType() == AopLogIgnore.class){
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
