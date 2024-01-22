<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<title>自動販賣機</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
		<style type="text/css">
			.circle30 {
	            width: 30px;
	            height: 30px;
	            border-radius: 50%;
	            background-color: #eeeeee; /* Replace with your desired color */
	            background-size: contain;
	            align-items: center;
	            justify-content: center;
	            text-align: center;
	        }
		</style>
		
		<script>
			function checkSubmit() {
				var totalPrice = document.getElementById('total_price').value;
				var coinAmount = document.getElementById('coin_amount').value;
				if(totalPrice == 0) {
					alert('尚未購買任何商品');
					return false;
				}
				if(coinAmount == 0) {
					alert('尚未投幣');
					return false;
				}
				if(coinAmount < totalPrice) {
					var diff = totalPrice - coinAmount;
					alert('投幣不足 ' + diff + ' 元');
					return false;
				}
				return true;
			}
		</script>
		
	</head>
	<body style="padding: 15px">
		<form method="post" onsubmit="return checkSubmit();" action="${pageContext.request.contextPath}/vending/submit">
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
		</form>
	</body>
</html>