package com.zhougao.demo.business.common.controller;

import com.jfinal.core.Controller;
import com.zhougao.demo.consumer.factory.controller.annotation.Inject;
import com.zhougao.demo.service.BlogService;

/**
 * CommonController
 */
public class CommonController extends Controller {
	
	@Inject.BY_TYPE
	private BlogService blogService;

	
	public void index() {
		setAttr("blogPage", blogService.paginate(getParaToInt(0, 1), 10));
		render("/views/common/index.html");
	}
}
