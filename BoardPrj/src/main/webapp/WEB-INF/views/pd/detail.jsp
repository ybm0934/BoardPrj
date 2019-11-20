<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>
</head>
<body>
<h1>상품 상세보기</h1>
상품명 : ${pdVo.pdName}<br>
가격 : ${pdVo.price}<br>
등록일 : <fmt:formatDate value="${pdVo.regdate}" pattern="yyyy.MM.dd. HH:mm:ss"/><br><br>
<a href="#" onclick="location.href='<c:url value='/pd/list.do'/>'">목록</a>&nbsp;
<a href="#" onclick="location.href='<c:url value='/pd/edit1.do?no=${pdVo.no}'/>'">수정</a>&nbsp;
<a href="#" onclick="location.href='<c:url value='/pd/delete.do?no=${pdVo.no}'/>'">삭제</a>
</body>
</html>