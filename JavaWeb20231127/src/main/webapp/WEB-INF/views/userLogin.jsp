<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Login</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		<form action="${pageContext.request.contextPath}/user/login" method="post" class="pure-form">
			<fieldset>
				<legend>User Login</legend>
				🙍‍♂️帳號: <input type="text" id="username" name="username" required placeholder="請輸入帳號"><p />
				🔑密碼: <input type="password" id="password" name="password" required placeholder="請輸入密碼"><p />
				🔐驗證: <input type="text" id="code" name="code" required placeholder="請輸入驗證碼">
					 <img src="${pageContext.request.contextPath}/code/image" alt="驗證碼" title="驗證碼"><p />
				<button type="submit" class="pure-button pure-button-primary">登入</button>	
			</fieldset>
			
		</form>
	</body>
</html>