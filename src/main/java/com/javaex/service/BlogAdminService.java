package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogAdminService {
	
	@Autowired
	private BlogDao blogDao;
	
	//내블로그 관리 서비스
	public BlogVo blogMainSelecList(String id) {
		System.out.println("2.내 블로그 관리 서비스");
		
		return blogDao.blogMainSelecList(id);
	}
	
	//기본설정 업로드
	public int uploadService(String id, MultipartFile logoFile, String blogTitle) {
		System.out.println("2.기본설정 업로드 서비스");
		
		String saveDir = "C:\\javaStudy\\upload";
		String saveName = "";
		
		if(logoFile.getOriginalFilename() != "") {
			// 확장자
			String exName = logoFile.getOriginalFilename().substring(logoFile.getOriginalFilename().lastIndexOf("."));
			
			// 저장 파일 이름
			saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			
			// 파일 경로
			String filePath = saveDir + "\\" + saveName;
			
			// 파일을 서버에 복사
			try {
				byte[] fileData = logoFile.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
				bout.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// basic정보 vo에 저장
		BlogVo blogVo = new BlogVo(id, blogTitle, saveName);
		
		return blogDao.updateBlogBasic(blogVo);
	}
	
	//카테고리 ajax
	public List<CategoryVo> adminCateService(String id) {
		System.out.println("카테고리 서비스");
		
		
		
		return blogDao.adminCateDao(id);
	}
	

}
