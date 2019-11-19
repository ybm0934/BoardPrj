<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	console.log('msg=${msg}');
	console.log('url=${url}');
	
	<c:if test="${!empty msg }">
		alert("${msg}");
	</c:if>
	location.href="<c:url value='${url}'/>";
</script>