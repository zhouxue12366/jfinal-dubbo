package com.zhougao.demo.service;

import com.jfinal.plugin.activerecord.Page;
import com.zhougao.demo.model.Blog;

public interface BlogService {
	Page<Blog> paginate(int pageNumber, int pageSize);

	void update(Blog blog);

	Blog save(Blog blog);

	Blog findById(String id);

	void deleteById(String id);
}
