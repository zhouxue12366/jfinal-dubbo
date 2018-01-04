package com.zhougao.demo.consumer.factory.controller;

import java.lang.reflect.Field;

import com.jfinal.core.Controller;
import com.jfinal.core.ControllerFactory;
import com.zhougao.demo.consumer.factory.controller.annotation.Inject;
import com.zhougao.demo.consumer.factory.controller.ioc.IocKit;

/**
 * 最好的整合方式是通过扩展一个 ControllerFactory 出来，接管 controller 的创建，然后对创建出来的 controller
 * 进行注入动作
 * 
 * @Title IocControllerFactory.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2017年12月29日 下午4:20:01
 * @version V1.0
 */
public class IocControllerFactory extends ControllerFactory {

	public Controller getController(Class<? extends Controller> controllerClass)
			throws InstantiationException, IllegalAccessException {
		Controller target = controllerClass.newInstance();
		Field[] fields = controllerClass.getDeclaredFields();
		for (Field field : fields) {
			Object bean = null;
			if (field.isAnnotationPresent(Inject.BY_NAME.class)) {
				bean = IocKit.getBean(field.getName());
			} else if (field.isAnnotationPresent(Inject.BY_TYPE.class)) {
				bean = IocKit.getBeanByType(field.getType());
			} else {
				continue;
			}

			try {
				if (bean != null) {
					field.setAccessible(true);
					field.set(target, bean);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return target;
	}

}
