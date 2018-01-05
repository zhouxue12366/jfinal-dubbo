package com.zhougao.demo.service;

import com.jfinal.plugin.activerecord.Page;
import com.zhougao.demo.model.Blog;

/**
 * 
 * @Title BlogService.java
 * @Description TODO
 * @Company: 软岛
 * @author zg
 * @date 2018年1月4日 下午1:53:09
 * @version V1.0
 */
public interface BlogService {
	Page<Blog> paginate(int pageNumber, int pageSize);

	void update(Blog blog);

	Blog save(Blog blog);

	Blog findById(String id);

	void deleteById(String id);
}
