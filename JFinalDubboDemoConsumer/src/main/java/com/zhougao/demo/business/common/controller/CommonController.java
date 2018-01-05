package com.zhougao.demo.business.common.controller;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jfinal.core.Controller;
import com.zhougao.demo.consumer.factory.controller.annotation.Inject.BY_NAME;
import com.zhougao.demo.service.BlogService;

/**
 * CommonController
 */
@Component
public class CommonController extends Controller {
	
	@Reference
	@BY_NAME
	private BlogService blogService;

	
	public void index() {
		setAttr("blogPage", blogService.paginate(getParaToInt(0, 1), 10));
		render("/views/common/index.html");
	}
}
