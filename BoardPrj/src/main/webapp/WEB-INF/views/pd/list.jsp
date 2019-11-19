<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<script type="text/javascript">
	function pageFunc(curPage){
		document.frmPage.currentPage.value=curPage;
		frmPage.submit();
	}
</script>
</head>
<body>
<form name="frmPage" method="post" action="<c:url value='/pd/list.do'/>">
	<input type="text" name="currentPage">
</form>
<h1>상품 목록</h1>
<table>
	<colgroup>
		<col style="width:10%;">
		<col style="width:*;">
		<col style="width:15%;">
		<col style="width:20%;">
	</colgroup>
	<tr>
		<th scope="col">번호</th>
		<th scope="col">품명</th>
		<th scope="col">가격</th>
		<th scope="col">등록일</th>
	</tr>
	<c:if test="${empty list}">
		<tr>
			<td colspan="4" style="text-align: center">글이 존재하지 않습니다.</td>
		</tr>
	</c:if>
	<c:if test="${!empty list}">
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.no}</td>
				<td>
					<a href="<c:url value='/pd/detail.do?no=${vo.no}'/>">${vo.pdName}</a>
					<c:if test="${vo.newImgTerm < 24}">
						<img alt="new 이미지" src="<c:url value='/images/new.png'/>">
					</c:if>
				</td>
				<td>${vo.price}</td>
				<td>${vo.regdate}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<div>
	<c:if test="${pageVo.firstPage > 1}">
		<a href="#" onclick="pageFunc(${pageVo.firstPage-1})">
			<span>PREV</span>
		</a>
	</c:if>
		
	<c:forEach var="i" begin="${pageVo.firstPage}" end="${pageVo.lastPage}">
		<c:if test="${i == pageVo.currentPage}">
			<span>${i}</span>
		</c:if>
		<c:if test="${i != pageVo.currentPage}">
			<a href="#" onclick="pageFunc(${i})">
				<span>${i}</span>
			</a>
		</c:if>
	</c:forEach>
		
	<c:if test="${pageVo.lastPage < pageVo.totalPage}">
		<a href="#" onclick="pageFunc(${pageVo.lastPage+1})">
			<span>NEXT</span>
		</a>
	</c:if>
</div>
<div>
	<input type="button" value="글쓰기" onclick="location.href='<c:url value='/pd/write.do'/>'">
</div>
</body>
</html>