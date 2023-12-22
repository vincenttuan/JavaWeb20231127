<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User main</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		<form action="${pageContext.request.contextPath}/user/login" method="post">
			帳號: <input type="text" id="username" name="username"><p />
			密碼: <input type="password" id="password" name="password"><p />
			驗證: <img src="${pageContext.request.contextPath}/code/image" alt="驗證碼"><p />
		</form>
	</body>
</html>