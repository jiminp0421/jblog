<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>


</head>

<body>
	<div id="wrap">
		
		<c:import url="/WEB-INF/views/include/blog-header.jsp"></c:import>
		<!-- 개인블로그 해더 -->


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/include/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
//레디를 만들고 fetchList를 호출한다
$(document).ready(function(){
	console.log("ready!");
	fetchList();
});



function fetchList(){
	var id = "${authUser.id}";
	console.log(id);
	$.ajax({
			
			url : "${pageContext.request.contextPath }/{id}",		
			type : "post",
			//contentType : "application/json",
			data : {id : id},
			
			
			dataType : "json",
			success : function(guestbookList){
				console.log(guestbookList);
				/*성공시 처리해야될 코드 작성*/
				//$("#guestbookListArea").html()
				
				
				for(var i=0; i<guestbookList.length; i++){
					render(guestbookList[i]);
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
		});

}
		

function render(guestVo) {
	var str ="";
	str +="<tr>"
	str +=""
	str += "<td>${cateList.cateNo}</td>"
	str += "<td>${cateList.cateName}</td>"
	str += "<td>${cateList.cateCount}</td>"
	str += "<td>${cateList.description}</td>"
    str += "<td class='text-center'>"
    str += "<img class='btnCateDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'>"
    str += "</td>"
	str += "</tr>"
	
	
	$("#guestbookListArea").prepend(str);
	
}
</script>


</html>