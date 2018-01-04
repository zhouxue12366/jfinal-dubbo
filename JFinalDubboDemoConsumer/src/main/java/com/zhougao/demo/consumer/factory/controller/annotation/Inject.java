package com.zhougao.demo.consumer.factory.controller.annotation;

import java.lang.annotation.*;

/**
 * 资源注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
	
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD })
	public static @interface BY_TYPE {
	}

	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD })
	public static @interface BY_NAME {
	}
}