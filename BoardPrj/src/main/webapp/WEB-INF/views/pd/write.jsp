<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
<script type="text/javascript" src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#subbtn").click(function(){
			
		})
	})
</script>
</head>
<body>
<form action='<c:url value='/pd/write.do'/>' name="write" method="post">
	상품명 : <input type="text" name="pdName" placeholder="상품명을 입력하세요.">
	가격 : <input type="text" name="price" placeholder="가격을 입력하세요.">
	<input type="submit" name="subbtn" id="subbtn" value="등록">
	<input type="reset" name="reset" id="reset" value="취소">
</form>
</body>
</html>