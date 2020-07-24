package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;


@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//블로그메인 다오
	public BlogVo blogMainSelecList(String id) {
		System.out.println("블로그메인 다오");
		
		BlogVo blogVo = (BlogVo) sqlSession.selectOne("blog.blogMainList", id);
		
		return blogVo;
		
	}

}
