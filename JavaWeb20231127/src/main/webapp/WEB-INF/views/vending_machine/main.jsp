<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<title>自動販賣機</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
		
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<!-- 左邊投幣區 -->
				<td valign="top">
					<%@ include file="left_coin.jspf" %>
				</td>
				<!-- 右邊商品區 -->
				<td valign="top">
					<%@ include file="right_product.jspf" %>
				</td>
			</tr>
		</table>
	</body>
</html>