package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@Autowired
	//private MainService mainService;
	
	@RequestMapping(value="/", method= {RequestMethod.GET, RequestMethod.POST})
	public String main() {
		
		System.out.println("메인화면");
		
		return "main/index";
	}
	

}
