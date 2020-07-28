package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;



@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 메인 
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("블로그 메인페이지");
		System.out.println(id);
		
		//블로그정보가져오기
		BlogVo blogVo = blogService.getBlogMainService(id);
		System.out.println(blogVo);
	
		System.out.println(blogVo.toString());
		model.addAttribute("blogVo", blogVo);
		
	
		//카테고리정보가져오기
		List<CategoryVo> cVo = blogService.getCategoryService(id);
		model.addAttribute("cateList", cVo);
		

	
		return "blog/blog-main";
	}
	

}
