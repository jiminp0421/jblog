package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;


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
	
	//블로그 카테고리 다오, 글쓰기
	public List<CategoryVo> blogCategoryList(String id) {
		System.out.println("블로그카테고리 다오");
		
		List<CategoryVo> cVo = sqlSession.selectList("category.blogCategoryList", id);
		
		return cVo;
	}
	
	//기본설정 타이틀, 사진

	public int updateBlogBasic(BlogVo blogVo) {
		System.out.println("블로그 기본설정 다오");
		
		return sqlSession.update("blog.blogUpdate", blogVo);
	}
	
	
	
	 //admin 카테고리 관리 
	public List<CategoryVo> adminCateSelectList(String id) {
		System.out.println("admin카테고리 다오");
	 
		return sqlSession.selectList("category.adminCateSelectList", id); 
		
	}
	
	//카테고리 추가 ajax
	public int addCateInsert(CategoryVo categoryVo) {
		System.out.println("adminAddCate");
		System.out.println(categoryVo.toString());
		
		return sqlSession.insert("category.addCateInsert",categoryVo);
	}
	 
	
	//카테고리 삭제
	public int cateDelete(int cateNo) {
		System.out.println("카테고리 삭제 다오");
		
		return sqlSession.delete("category.cateDelete", cateNo);
	}
	

	//카테고리 글작성
	public int cateWriteInsert(String id, PostVo postVo) {
		System.out.println("카테고리 글작성 다오.");
		
		return sqlSession.insert("category.insertPost", postVo);
	}
	

	

}
