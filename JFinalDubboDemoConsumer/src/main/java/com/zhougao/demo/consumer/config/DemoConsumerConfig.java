package com.zhougao.demo.consumer.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
import com.zhougao.demo.business.blog.controller.BlogController;
import com.zhougao.demo.business.common.controller.CommonController;
import com.zhougao.demo.consumer.factory.controller.IocControllerFactory;
import com.zhougao.demo.consumer.plugin.SpringPlugin;

/**
 * consumer主配置类
 * @Title DemoConsumerConfig.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2018年1月4日 上午10:22:22
 * @version V1.0
 */
public class DemoConsumerConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setControllerFactory(new IocControllerFactory ());
		me.setDevMode(true);
	}

	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configPlugin(Plugins me) {
		me.add(new SpringPlugin("classpath:applicationContext.xml"));
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/blog", BlogController.class);
	}

	@Override
	public void afterJFinalStart() {
//		Map<String, Class<?>> blogColumnMap = new HashMap<String, Class<?>>();
//		blogColumnMap.put("id", Integer.class);
//		blogColumnMap.put("title", String.class);
//		blogColumnMap.put("content", String.class);
//
//		TableInitKit.init("blog", Blog.class, blogColumnMap);
//		
//		System.out.println("Blog表字段模拟完成。");
		
		System.out.println("consumer for Dubbo启动完成。");
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
		
	}

}
