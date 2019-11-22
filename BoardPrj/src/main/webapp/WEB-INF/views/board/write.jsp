<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/board/write.css'/>">
<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>
<script type="text/javascript" src="<c:url value='/js/write.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#title").focus();
		$("#subBtn").click(function(){
			var memberid = $("#memberid").val();
			var password = $("#password").val();
			var title = $("#title").val();
			var content = CKEDITOR.instances.content.getData();
			var fileSize = $("#fileSize").val();
			var fileExt = $("#fileExt").val();
			
			if(title.length < 1){
				alert("제목을 입력하세요.");
				$("#title").focus();
				return false;
			}else if(title.length > 70){
				alert("제목은 70글자 이내만 가능합니다.");
				$("#title").focus();
				return false;
			}else if(memberid.length < 1){
				alert("닉네임을 입력하세요.");
				$("#memberid").focus();
				return false;
			}else if(memberid.length > 10){
				alert("닉네임은 10글자 이하만 가능합니다.");
				$("#memberid").focus();
				return false;
			}else if(password.length < 1){
				alert("비밀번호를 입력하세요.");
				$("#password").focus();
				return false;
			}else if(password.length > 10){
				alert("비밀번호는 10글자 이하만 가능합니다.");
				$("#password").focus();
				return false;
			}else if(content == ''){
				alert("내용을 입력하세요.");
				CKEDITOR.instances.content.focus();
				return false;
			}else if(fileSize > 10){
				alert("첨부파일은 10MB 이내만 가능합니다.");
				return false;
			}else if(fileExt == 'exe'){
				alert("첨부할 수 없는 파일입니다.");
				return false;
			}else {
				$("form[name=write]").submit();
			}
			
		})
		
		//파일을 첨부할 경우
		$("#attach").change(function(){
			fileCheck(document.getElementById("attach"));
		})
	})
	
</script>
</head>
<body>
	<h1>글쓰기</h1>
	<form name="writeform" action="<c:url value='/board/write.do'/>" method="post" enctype="multipart/form-data">
		<table class="write_tbl" summary="글쓰기에 관한 표로써 제목, 아이디, 비밀번호, 이메일, 내용, 아이피, 포트, 브라우저, 파일명에 관한 정보를 제공합니다.">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberid" id="memberid"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" id="email"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" class="text" id="content" placeholder="내용을 입력하세요."></textarea>
					<script type="text/javascript">
						CKEDITOR.replace('content',{
							height:'500px'
						})
					</script>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="attach" id="attach" accept="*">
					<span class="fileSpn">( <span id="sizeSpn">0</span> / 10MB )</span>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" id="subBtn" class="subBtn" value="글쓰기">
					<input type="button" id="reBtn" class="reBtn" value="취소" onclick="history.back()">
				</th>
			</tr>
		</table>
		<input type="text" name="ip" value="${ip}">
		<input type="text" name="port" value="${port}">
		<input type="text" name="browser" value="${browser}">
		<input type="text" name="fileSize" value="0">
		<input type="text" name="fileExt">
	</form>
</body>
</html>
<%@ include file="../inc/bottom.jsp" %>