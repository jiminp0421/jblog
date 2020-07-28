package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;


@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	
	//블로그 메인 서비스
	public BlogVo getBlogMainService(String id) {
		System.out.println("블로그 메인 서비스");
		
		BlogVo blogVo = blogDao.blogMainSelecList(id);
		
		return blogVo;
	}
	
	//블로그 카테고리 서비스
	public List<CategoryVo> getCategoryService(String id) {
		
	
		List<CategoryVo> cVo = blogDao.blogCategoryList(id);
		
		return cVo;
	}
	


}
