package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 다오
	public int joinInsert(UserVo userVo) {
		System.out.println("회원가입 다오");
		
		return sqlSession.insert("user.joinInsert", userVo);
	}
	
	//블로그 다오
	public int blogInsert(BlogVo blogVo) {
		System.out.println("블로그 다오");
		System.out.println(blogVo.toString());
	
		return sqlSession.insert("user.blogInsert", blogVo);
	}
	
	//카데고리 다오
	public int cateInsert(CategoryVo categoryVo) {
		System.out.println("카데고리 다오");
		
		return sqlSession.insert("user.cateInsert", categoryVo);
	}
	
	
	
	//로그인 다오
	public UserVo loginSelectOne(UserVo userVo) {
		System.out.println("로그인 다오");
		
		
		return sqlSession.selectOne("user.loginSelectOne", userVo);
	}

}
