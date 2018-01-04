package com.zhougao.demo.consumer.factory.controller.ioc;

import org.springframework.context.ApplicationContext;

/***
 * 专门负责管理管理spring对象的
 * @Title IocKit.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2018年1月3日 下午6:19:11
 * @version V1.0
 */
public class IocKit {
	private IocKit() {
	}

	public static ApplicationContext ctx;

	/**
	 * 根据注解名称获取springBean对象
	 * @Title getBean
	 * @Description TODO  
	 * @param beanName
	 * @return 
	 * @since 2018年1月3日 下午5:56:44
	 */
	public static Object getBean(String beanName) {
		Object target = ctx.getBean(beanName);
		if (target == null) {
			return null;
		}
		return target;
	}

	/**
	 * 根据注解类型获取springBean对象
	 * @Title getBeanByType
	 * @Description TODO  
	 * @param beanName
	 * @return 
	 * @since 2018年1月3日 下午5:57:22
	 */
	public static Object getBeanByType(Class<?> beanName) {
		Object target = ctx.getBean(beanName);
		if (target == null) {
			return null;
		}
		return target;
	}

}
