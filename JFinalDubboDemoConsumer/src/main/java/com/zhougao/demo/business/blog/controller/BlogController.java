package com.zhougao.demo.business.blog.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.zhougao.demo.business.blog.interceptor.BlogInterceptor;
import com.zhougao.demo.business.blog.validator.BlogValidator;
//import com.zhougao.demo.consumer.plugin.Inject;
import com.zhougao.demo.consumer.plugin.IocInterceptor;
import com.zhougao.demo.model.Blog;
import com.zhougao.demo.service.BlogService;

@Before({ BlogInterceptor.class, IocInterceptor.class })
public class BlogController extends Controller {

//	@Inject.BY_NAME
	private BlogService blogService;

	public void index() {
		setAttr("blogPage", blogService.paginate(getParaToInt(0, 1), 10));
		render("/views/blog/blog.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(BlogValidator.class)
	public void save() {
		Blog blog = getModel(Blog.class, "blog");
		blogService.save(blog);
		redirect("/blog");
	}

	public void edit() {
		setAttr("blog", blogService.findById(getPara()));
	}

	@Before(BlogValidator.class)
	public void update() {
		Blog blog = getModel(Blog.class, "blog");
		blogService.update(blog);
		redirect("/blog");
	}

	public void delete() {
		blogService.deleteById(getPara());
		redirect("/blog");
	}
}
