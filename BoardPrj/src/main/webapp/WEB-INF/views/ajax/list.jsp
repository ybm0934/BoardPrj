<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<title>Home Ajax</title>
<script type="text/javascript" src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>
</head>
<body>
<div class="container">
	<div class="form-group row pull-right">
		<div class="col-xs-8">
			<input type="text" class="form-control" size="20">
		</div>
		<div class="col-xs-2">
			<button class="btn btn-primary" type="button">검색</button>
		</div>
		<table class="table" style="text-align:center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center">이름</th>
					<th style="background-color: #fafafa; text-align: center">나이</th>
					<th style="background-color: #fafafa; text-align: center">성별</th>
					<th style="background-color: #fafafa; text-align: center">이메일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>홍길동</td>
					<td>22</td>
					<td>남자</td>
					<td>hong@naver.com</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>