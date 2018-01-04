package com.zhougao.demo.provider.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Inject.
 * 
 */
public class Inject {

	private Inject() {
	}

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

	/*
	 * 
	 * @Inherited
	 * 
	 * @Retention(RetentionPolicy.RUNTIME)
	 * 
	 * @Target({ElementType.FIELD})
	 * 
	 * public static @interface IGNORE {}
	 * 
	 */
}
