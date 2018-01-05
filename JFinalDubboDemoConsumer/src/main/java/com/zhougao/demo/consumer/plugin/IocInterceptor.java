package com.zhougao.demo.consumer.plugin;

import java.lang.reflect.Field;

import org.springframework.context.ApplicationContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.zhougao.demo.consumer.factory.controller.annotation.Inject;
import com.zhougao.demo.consumer.factory.controller.ioc.IocKit;

/**
 * 使用拦截器的方式进行spring的ioc注入<br/>
 * 警告:已经不推荐这种方式使用;因为会被@clear给清除掉;<br/>
 * 建议使用controllerFactory的方式在config类中的configConstant方法中配置使用
 * @Title IocInterceptor.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2018年1月4日 上午10:19:08
 * @version V1.0
 */
@Deprecated
public class IocInterceptor implements Interceptor {

	static ApplicationContext ctx;

	@Override
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Inject.BY_NAME.class))
				bean =IocKit.getBean(field.getName());
			else if (field.isAnnotationPresent(Inject.BY_TYPE.class))
				bean = IocKit.getBean(field.getType());
			else
				continue;

			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(controller, bean);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		ai.invoke();
	}
}
