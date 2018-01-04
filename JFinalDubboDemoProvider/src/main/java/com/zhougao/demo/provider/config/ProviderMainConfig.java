package com.zhougao.demo.provider.config;

import com.jfinal.core.JFinal;

public class ProviderMainConfig {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("src/main/webapp/", 8080, "/", 5);
	}
}
