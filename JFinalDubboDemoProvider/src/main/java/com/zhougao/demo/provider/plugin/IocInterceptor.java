package com.zhougao.demo.provider.plugin;

import java.lang.reflect.Field;

import org.springframework.context.ApplicationContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class IocInterceptor implements Interceptor {

static ApplicationContext ctx;
	
	@Override
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Inject.BY_NAME.class))
				bean = ctx.getBean(field.getName());
			else if (field.isAnnotationPresent(Inject.BY_TYPE.class))
				bean = ctx.getBean(field.getType());
			else
				continue ;
			
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
