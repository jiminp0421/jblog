package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("컨트롤러 로그인폼");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession httpSession) {
		System.out.println("컨트롤러 로그인");
		System.out.println(userVo.toString());
		UserVo authUser = userService.loginService(userVo);
	
		System.out.println(authUser);
		if(authUser !=null) { //로그인 성공
			httpSession.setAttribute("authUser", authUser);
			return "redirect:/";
		}else {//로그인 실패일때
			System.out.println("로그인 실패");
			return "redirect/user/loginForm?result=fail";
		}
		
	}
		
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {
		System.out.println("컨트롤러 로그아웃");
		
		httpSession.removeAttribute("authUser");
		httpSession.invalidate();
		
		return"redirect:/";
	}
	
	
	//회원폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		
		System.out.println("유저 회원폼");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo, @ModelAttribute BlogVo blogVo,
			@ModelAttribute CategoryVo categoryVo) {
		
		System.out.println("회원가입컨트롤러.join");
		
		System.out.println(userVo.toString());
		System.out.println(blogVo.toString());
		System.out.println(categoryVo.toString());
		
		userService.joinService(userVo, blogVo, categoryVo);
		
		
		return "user/joinSuccess";
	}
	
	/*
	 * //블로그 로고파일 넣기
	 * 
	 * @RequestMapping("/blogLogoUpload") public String
	 * blogLogoUpload(@RequestParam("file") MultipartFile file, Model model) {
	 * System.out.println("블로그로고업로드");
	 * System.out.println(file.getOriginalFilename());
	 * 
	 * String saveName = userService.blogLogoUploadService(file);
	 * model.addAttribute("saveName", saveName);
	 * 
	 * return ""; }
	 */
	
	

}
