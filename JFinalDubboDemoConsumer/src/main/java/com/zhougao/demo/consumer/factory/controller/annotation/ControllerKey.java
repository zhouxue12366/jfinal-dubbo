package com.zhougao.demo.consumer.factory.controller.annotation;

import java.lang.annotation.*;

/**
 * 控制器注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface ControllerKey {
    String value() default "/";

    String viewPath() default "";
}