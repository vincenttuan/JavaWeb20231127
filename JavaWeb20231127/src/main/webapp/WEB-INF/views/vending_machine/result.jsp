<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Result</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		<div class="pure-form">
			<fieldset>
				<legend>Result</legend>
				${ currentSalesItems } <p>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>序號</th>
							<th>商品序號</th>
							<th>商品名稱</th>
							<th>數量</th>
							<th>小計</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="salesItem" items="${ currentSalesItems }" varStatus="status">
							<tr>
								<td>${ (status.index + 1) }</td>
								<td>${ salesItem.productId }</td>
								<td>${ salesItem.productName }</td>
								<td>${ salesItem.totalAmount }</td>
								<td>${ salesItem.totalPrice }</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3"></td>
							<td>${ sumAmount }</td>
							<td>${ sumPrice }</td>
						</tr>
					</tfoot>
				</table>
				<p />
				<a href="./main" class="pure-button pure-button-primary">回首頁</a>
			</fieldset>
			
		</div>
	</body>
</html>