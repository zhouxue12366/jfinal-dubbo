package com.zhougao.demo.consumer.factory.controller.annotation;

import java.lang.annotation.*;

/**
 * 服务层注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface Service {
}