<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품목 수정</title>
</head>
<body>
<form name="edit" action="<c:url value='/pd/edit2.do'/>" method="post">
	<input type="text" name="no" value="${pdVo.no}">
	상품명 : <input type="text" name="pdName" placeholder="상품명을 입력하세요." value="${pdVo.pdName}">
	가격 : <input type="text" name="price" placeholder="가격을 입력하세요." value="${pdVo.price}">
	<input type="submit" name="subbtn" id="subbtn" value="수정">
	<input type="reset" name="reset" id="reset" value="리셋">
</form>
</body>
</html>