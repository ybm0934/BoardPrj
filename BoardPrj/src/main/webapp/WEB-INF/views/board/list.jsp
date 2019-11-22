<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/board/list.css'/>">
<script type="text/javascript">
	function pageFunc(curPage){
		document.frmPage.currentPage.value = curPage;
		frmPage.submit();
	}
</script>
</head>
<body>
<form name="frmPage" action="<c:url value='/board/list.do'/>" method="post">
	<input type="text" name="currentPage">
</form>
<div>
	<h1>게시판</h1>
	<div class="list_tbl_body">
		<table class="list_table" summary="게시판에 관한 표로써 번호, 제목, 닉네임, 등록일, 조회수를 제공한다.">
			<colgroup>
				<col style="width:10%;">
				<col style="width:*;">
				<col style="width:15%;">
				<col style="width:15%;">
				<col style="width:10%;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">아이디</th>
					<th scope="col">등록일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
					<td colspan="5">글이 존재하지 않습니다.</td>
				</c:if>
				<c:if test="${!empty list}">
					<c:forEach var="vo" items="${list}">
						<tr>
							<td>${vo.no}</td>
							<td>
								<a href="#" onclick="location.href='<c:url value='/board/detail.do?no=${vo.no}'/>'">
									<c:if test="${fn:length(vo.title) > 35}">
										${fn:substring(vo.title, 0, 35)}.....
									</c:if>
									<c:if test="${fn:length(vo.title) <= 35}">
										${vo.title}
									</c:if>
								</a>
								<c:if test="${vo.newImgTerm < 24}">
									<img alt="new 이미지" src="<c:url value='/images/new.png'/>">
								</c:if>
							</td>
							<td>
								<c:if test="${fn:length(vo.memberid) > 10}">
									${fn:substring(vo.memberid, 0, 10)}...
								</c:if>
								<c:if test="${fn:length(vo.memberid) <= 10}">
									${vo.memberid}
								</c:if>
							</td>
							<td>
								<c:if test="${vo.newImgTerm < 24}">
									<fmt:formatDate value="${vo.regdate}" pattern="HH:mm:ss"/>
								</c:if>
								<c:if test="${vo.newImgTerm >= 24}">
									<fmt:formatDate value="${vo.regdate}" pattern="yyyy.MM.dd"/>
								</c:if>
							</td>
							<td>${vo.cnt}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="search_div">
		<select name="searchCondition" class="searchCondition">
			<option value="title" selected="selected">제목</option>
			<option value="content" selected="selected">내용</option>
			<option value="memberid" selected="selected">작성자</option>
		</select>
		<input type="text" class="searchKeyword" name="searchKeyword" placeholder="검색">
		<input type="submit" name="searchBtn" class="searchBtn" value="검색">
	</div>
	<div class="write_div">
		<input type="button" class="write_btn" value="글쓰기" onclick="location.href='<c:url value='/board/write.do'/>'">
	</div>
	<div class="list_paging">
		<c:if test="${pageVo.firstPage > 1}">
			<a href="#" onclick="pageFunc(${pageVo.firstPage - 1})"><span>PREV</span></a>
		</c:if>
		
		<c:forEach var="i" begin="${pageVo.firstPage}" end="${pageVo.lastPage}">
			<c:if test="${i == pageVo.currentPage}">
				<span>${i}</span>
			</c:if>
			<c:if test="${i != pageVo.currentPage}">
				<a href="#" onclick="pageFunc('${i}')"><span>${i}</span></a>
			</c:if>
		</c:forEach>
		
		<c:if test="${pageVo.lastPage < pageVo.totalPage}">
			<a href="#" onclick="pageFunc(${pageVo.lastPage + 1})"><span>NEXT</span></a>
		</c:if>
	</div>
</div>
</body>
</html>
<%@ include file="../inc/bottom.jsp" %>