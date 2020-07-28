package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogAdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping("/{id}/admin")
public class BlogAdminController {
	
	@Autowired
	private BlogAdminService blogAdminService;

	
	//내 블로그 관리
	@RequestMapping ("/basic")
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("1.내 블로그 관리 컨트롤러");
		System.out.println(id);
		
		BlogVo blogVo = blogAdminService.blogMainSelecList(id);
		model.addAttribute("blogVo", blogVo);

		String status = "basic";
		model.addAttribute("admin", status);
		
		return "blog/admin/blog-admin-basic";
	}
	
	//내 블로그 관리 - 기본설정
	@RequestMapping ("/upload")
	public String upload(@PathVariable("id") String id, @RequestParam("logoFile") MultipartFile logoFile, @RequestParam("blogTitle") String blogTitle) {
		System.out.println("로고파일, 타이틀수정");
		
		blogAdminService.uploadService(id, logoFile, blogTitle);
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	//내 블로그 관리 - 카테고리
	@RequestMapping("/category")
	public String cate(@PathVariable("id") String id, Model model) {
		System.out.println("1.카테고리 컨트롤러");
		
		return "blog/admin/blog-admin-cate";
	}
	
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/category") public List<CategoryVo>
	 * category(@PathVariable("id") String id, Model model) {
	 * System.out.println("카테고리 컨트롤러");
	 * 
	 * BlogVo blogVo = blogAdminService.blogMainSelecList(id);
	 * model.addAttribute("blogVo", blogVo);
	 * 
	 * List<CategoryVo> cateList = blogAdminService.adminCateService(id);
	 * 
	 * System.out.println(cateList.toString());
	 * 
	 * 
	 * return cateList; }
	 */
	
	
	
}
