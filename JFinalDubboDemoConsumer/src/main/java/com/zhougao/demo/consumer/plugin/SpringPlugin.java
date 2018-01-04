package com.zhougao.demo.consumer.plugin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jfinal.plugin.IPlugin;
import com.zhougao.demo.consumer.factory.controller.ioc.IocKit;

/**
 * 加载spring插件类
 * @Title SpringPlugin.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2018年1月3日 下午6:21:32
 * @version V1.0
 */
public class SpringPlugin implements IPlugin {

	private String[] configurations;
	private ApplicationContext ctx;
	
	/**

	 * Use configuration under the path of WebRoot/WEB-INF.

	 */
	public SpringPlugin() {
	}
	
	public SpringPlugin(String... configurations) {
		this.configurations = configurations;
	}
	
	public SpringPlugin(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	public boolean start() {
		if (ctx != null)
			IocKit.ctx = ctx;
		else if (configurations != null)
			IocKit.ctx = new FileSystemXmlApplicationContext(configurations);
		else
			IocKit.ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		return true;
	}
	
	public boolean stop() {
		return true;
	}
}
