<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<form action="insert.message" method="get">
		<input type="text" name="writer" placeholder="작성자명"> <input
			type="text" name="message" placeholder="메세지 내용"> <input
			type="submit">
	</form>
</body>
</html>