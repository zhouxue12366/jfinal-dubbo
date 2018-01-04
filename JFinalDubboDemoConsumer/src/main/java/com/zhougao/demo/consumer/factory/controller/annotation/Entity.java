package com.zhougao.demo.consumer.factory.controller.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface Entity {
    String value();

    String comment() default "";
}
