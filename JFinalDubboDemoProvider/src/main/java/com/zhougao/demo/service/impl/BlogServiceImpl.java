package com.zhougao.demo.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.zhougao.demo.model.Blog;
import com.zhougao.demo.service.BlogService;

public class BlogServiceImpl implements BlogService {

	private Blog blogDao;
	
	public Page<Blog> paginate(int pageNumber, int pageSize) {
		return blogDao.paginate(pageNumber, pageSize, "select *", "from blog order by id asc");
	}

	public void update(Blog blog) {
		if (blog == null) {
			return;
		}
		blog.update();
	}

	public Blog save(Blog blog) {
		if (blog == null) {
			return null;
		}
		blog.save();
		return blog;
	}

	public Blog findById(String id) {
		Blog blog = blogDao.findById(id);
		return blog;
	}

	public void deleteById(String id) {
		blogDao.deleteById(id);
	}
	
	/**
	 * 通过Spring配置文件注入Blog的dao
	 * @param blogDao
	 */
	public void setBlogDao(Blog blogDao) {
		this.blogDao = blogDao;
	}
}
