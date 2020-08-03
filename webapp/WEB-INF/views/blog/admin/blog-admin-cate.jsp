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
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
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
		      		<tbody id="cateListArea">
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
	$.ajax({
			//보내줄경우
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateList",		
			type : "post",
			//contentType : "application/json",
			//data : {id : id},
			
			//받아올경우
			dataType : "json",
			success : function(cateList){
				console.log(cateList);
				/*성공시 처리해야될 코드 작성*/
				//$("#guestbookListArea").html()
				
				
				for(var i=0; i<cateList.length; i++){
					render(cateList[i]);
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
		});

}
		

function render(cateList) {
	var str =""; //10+10=20, "10"+"10" = "1010"
	str +="<tr>"
	str += "<td>"+cateList.cateNo+"</td>"
	str += "<td>"+cateList.cateName+"</td>"
	str += "<td>"+cateList.cateCount+"</td>"
	str += "<td>"+cateList.description+"</td>"
    str += "<td class='text-center'>"
    str += "<img class='btnCateDel' data-cateCount = "+ cateList.cateCount +" data-cateno = "+ cateList.cateNo +" src='${pageContext.request.contextPath}/assets/images/delete.jpg'>"
    str += "</td>"
	str += "</tr>"
	
	
	$("#cateListArea").prepend(str);
	
}



//카테고리추가 ajax
$("#btnAddCate").on("click",function(){
	console.log("btnAddCate.click");
	
	var cateName = $("[ name = name ]").val();
	var description = $("[ name = desc ]").val();
	
	$.ajax({
		//보내줄경우
		url : "${pageContext.request.contextPath}/${authUser.id}/admin/addCate",		
		type : "post",
		//contentType : "application/json",
		data : {cateName : cateName, description : description},
		
		//받아올경우
		dataType : "json",
		success : function(cateVo){
			console.log(cateVo);
			render(cateVo);
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		
	});
});


//카테고리 삭제
$("#cateListArea").on("click",".btnCateDel", function(){
	var thisArea = $(this)
	var count = thisArea.data("catecount");
	console.log("btnCateDel.click, "+thisArea+count);
	
	var no = thisArea.data("cateno");
	console.log("btnCateDel" +thisArea+no);
	
	if(count !=0){
		console.log("없어지면 안돼");
		alert("삭제할 수 없습니다.");
	}else {
		console.log("넌 없어져");
		
		$.ajax({
			//보내줄경우
		    type: "post",
		    url: "${pageContext.request.contextPath}/${authUser.id}/admin/removeCate",
		    //dataType: 'json',
		    data: {cateNo : no},
		    
		    
		  	//받아줄경우
		    success: function(data){
		       // success
		       
		       if(data>0){
		    	   console.log("1."+thisArea.parents("tr"));
		    	   thisArea.parents("tr").remove();
		    	   
		       }
		    }    
		})
	}
	
});


</script>


</html>