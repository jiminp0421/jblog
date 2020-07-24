package com.javaex.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	
	@Autowired
	private UserDao userDao;
	
	
	//회원가입 서비스
	public int joinService(UserVo userVo, BlogVo blogVo, CategoryVo categoryVo) {
		System.out.println("회원가입서비스.join");
		
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그입니다.");
		blogVo.setLogoFile("spring-logo.jpg");
		
		categoryVo.setId(userVo.getId());
		categoryVo.setCateName("미분류");
		categoryVo.setDescription("");
		
		
		System.out.println(userVo.toString());
		System.out.println(blogVo.toString());
		System.out.println(categoryVo.toString());
		
		userDao.joinInsert(userVo);
		userDao.blogInsert(blogVo);
		userDao.cateInsert(categoryVo);
		
		return 1;
	}
	
	//로그인 서비스
	public UserVo loginService(UserVo userVo) {
		System.out.println("로그인서비스.login");
		
		
		return userDao.loginSelectOne(userVo);
	}

}
