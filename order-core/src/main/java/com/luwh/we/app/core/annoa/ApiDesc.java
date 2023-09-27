package com.luwh.we.app.core.annoa;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口描述，一个标志性的注解，
 * 后续可以根据这个注解仿写一个 类似 swagger的接口文档生成器
 * 暂时不想引入 swagger, 太重了
 * @author lu.wh
 * @date 2023/09/27 10/25/09
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface ApiDesc {
    String desc() default "";
}
