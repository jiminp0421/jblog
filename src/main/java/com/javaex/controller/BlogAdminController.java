package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogAdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/{id}/admin")
public class BlogAdminController {

	@Autowired
	private BlogAdminService blogAdminService;

	// 내 블로그 관리
	@RequestMapping("/basic")
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("1.내 블로그 관리 컨트롤러");
		System.out.println(id);

		BlogVo blogVo = blogAdminService.blogMainSelecList(id);
		model.addAttribute("blogVo", blogVo);

		String status = "basic";
		model.addAttribute("admin", status);

		return "blog/admin/blog-admin-basic";
	}

	// 내 블로그 관리 - 기본설정
	@RequestMapping("/upload")
	public String upload(@PathVariable("id") String id, @RequestParam("logoFile") MultipartFile logoFile,
			@RequestParam("blogTitle") String blogTitle) {
		System.out.println("로고파일, 타이틀수정");

		blogAdminService.uploadService(id, logoFile, blogTitle);

		return "redirect:/" + id + "/admin/basic";
	}

	// 내 블로그 관리 - 카테고리
	@RequestMapping("/category")
	public String cate(@PathVariable("id") String id, Model model) {
		System.out.println("카테고리 컨트롤러");
		//상단 카테고리 페이지 보여줌
		BlogVo blogVo = blogAdminService.blogMainSelecList(id);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-cate";
	}

	
	 @ResponseBody
	 @RequestMapping("/cateList") 
	 public List<CategoryVo> cateList(@PathVariable("id") String id, Model model) {
		 System.out.println("카테고리 컨트롤러 리스트" + id);
	  
		 BlogVo blogVo = blogAdminService.blogMainSelecList(id);
		 model.addAttribute("blogVo",blogVo);
	 
		 List<CategoryVo> cateList = blogAdminService.adminCateListService(id);
		 System.out.println(cateList.toString());
	  
		 return cateList; 
	}
	
	@ResponseBody
	@RequestMapping("/addCate")
	public CategoryVo addCate(HttpSession session,
			@RequestParam String cateName, String description) {
		System.out.println("addCate 컨트롤러");
		UserVo vo = (UserVo)session.getAttribute("authUser");
		String id = vo.getId();
		
		CategoryVo cateVo = blogAdminService.addCateService(id,cateName,description);
		
		System.out.println(cateVo.toString());
		
		return cateVo;
	}
	 
	@ResponseBody
	@RequestMapping("/removeCate")
	public int removeCate(@RequestParam int cateNo) {
		System.out.println("BlogAdmin. removeCate 컨트롤러");
		
		
		int no = blogAdminService.removeCateService(cateNo);
		
		return no;
	}
	
	//내 블로그 관리 - 글작성
	@RequestMapping("/writeForm")
	public String writeForm(@PathVariable("id") String id, Model model) {
		System.out.println("글작성 컨트롤러"+ id);
		
		
		BlogVo blogVo = blogAdminService.blogMainSelecList(id);
		model.addAttribute("blogVo",blogVo);
		
		List<CategoryVo> cateList = blogAdminService.adminCateListService(id);
		 System.out.println(cateList.toString());
		 model.addAttribute("cateList",cateList);
		
		return "blog/admin/blog-admin-write";
		
	}
	
	//내 블로그 관리 글쓰기
	@RequestMapping("/write")
	public String write(HttpSession session,@ModelAttribute PostVo postVo) {
		System.out.println("글작성 포스트하기 컨트롤러" + postVo.toString());
		
		UserVo vo = (UserVo)session.getAttribute("authUser");
		String id = vo.getId();
		
		blogAdminService.adminCateWriteService(id, postVo);
		
		
		return "redirect:/" + id + "/admin/writeForm";
	}
	

	 

}
