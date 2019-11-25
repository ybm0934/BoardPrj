<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판 목록</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ajax/list.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#create').click(function() {
		$.ajax({
			type : "POST",
			url : "<c:url value='/ajax/write.do'/>",
			data : {
				"name" : $('#name').val(),
				"age" : $('#age').val(),
				"gender" : $('#gender').val(),
				"email" : $('#email').val()
			},
			success : function() {
				location.reload();
			},
			error : function() {
				alert('게시글 등록 실패');
			}
		});
	});
});

	function pageFunc(curPage){
		document.frmPage.currentPage.value = curPage;
		frmPage.submit();
	}
</script>
</head>
<body>
<div class="firstDiv">
<h1>게시판</h1>
	<div class="secondDiv">
		<table class="listTbl">
			<colgroup>
				<col style="width:10%;">
				<col style="width:20%;">
				<col style="width:20%;">
				<col style="width:20%;">
				<col style="width:*;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">이름</th>
					<th scope="col">나이</th>
					<th scope="col">성별</th>
					<th scope="col">이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
					<tr>
						<th colspan="5">글이 존재하지 않습니다.</th>
					</tr>
				</c:if>
				<c:if test="${!empty list}">
					<c:forEach var="vo" items="${list}">
						<tr>
							<td>${vo.no}</td>
							<td>${vo.name}</td>
							<td>${vo.age}</td>
							<td>${vo.gender}</td>
							<td>${vo.email}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="searchDiv">
		<form name="frmPage" action="<c:url value='/ajax/list.do'/>" method="post">
			<select name="searchCondition" class="searchCondition">
				<option value="name" 
					<c:if test="${'name' == searchVo.searchCondition}">
						selected="selected"
					</c:if>
					>이름</option>
				<option value="age" 
					<c:if test="${'age' == searchVo.searchCondition}">
						selected="selected"
					</c:if>
					>나이</option>
				<option value="gender" 
					<c:if test="${'gender' == searchVo.searchCondition}">
						selected="selected"
					</c:if>
					>성별</option>
			</select>
			<input type="text" class="searchKeyword" name="searchKeyword" placeholder="검색어 입력" value="${searchVo.searchKeyword}">
			<input type="submit" name="searchBtn" class="searchBtn" value="검색">
			<br><br><br>
			<input type="text" name="currentPage" value="1">
		</form>
	</div>
</div>
<br>
<hr>
<br>
	이름 : <input type="text" id="name"><br>
	나이 : <input type="text" id="age"><br>
	성별 : <textarea id="gender"></textarea><br>
	이메일 : <input type="text" id="email"><br>
	<input type="button" id="create" value="전송">
</body>
</html>
