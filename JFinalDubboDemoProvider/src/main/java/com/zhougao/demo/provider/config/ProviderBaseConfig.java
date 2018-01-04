package com.zhougao.demo.provider.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.template.Engine;
import com.zhougao.demo.model.Blog;
import com.zhougao.demo.provider.plugin.SpringPlugin;

public class ProviderBaseConfig extends JFinalConfig {

	/**
	 * 配置常量和配置文件
	 */
	@Override
	public void configConstant(Constants me) {
		// 读取配置文件
		PropKit.use("provider_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode"));
		me.setEncoding("UTF-8");
		// me.setViewType(ViewType.JSP);
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
		// 声明Druid监控页面URL
		me.add(new DruidStatViewHandler("/druid"));
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置Druid数据库连接池插件
		DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		WallFilter wall = new WallFilter();
		wall.setDbType(JdbcConstants.MYSQL);
		dp.addFilter(wall);
		
		StatFilter stat = new StatFilter();
		stat.setMergeSql(true);
		dp.addFilter(stat);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(PropKit.getBoolean("devMode", false));
		arp.setDevMode(PropKit.getBoolean("devMode", false));
		arp.addMapping("blog", Blog.class); // 映射blog 表到 Blog模型
		arp.setDialect(new MysqlDialect());

		// 配置Spring插件
		SpringPlugin sp = new SpringPlugin("classpath:applicationContext.xml");
		
		// 手动启动各插件
		dp.start();
		arp.start();
		sp.start();
		
		// 加入各插件到Config
		me.add(dp);
		me.add(arp);
		me.add(sp);

	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
	}

	@Override
	public void afterJFinalStart() {
		System.out.println("provider for Dubbo启动完成");
	}

	/**
	 * 定义通用的模板
	 * 
	 * @Title configEngine
	 * @Description TODO
	 * @param arg0
	 * @since 2017年12月20日 下午1:51:57
	 */
	@Override
	public void configEngine(Engine arg0) {

	}
}
