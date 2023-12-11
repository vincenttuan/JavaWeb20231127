<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking List</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th nowrap>取消預約</th>
					<th nowrap>預約號碼</th><th nowrap>房間號碼</th><th nowrap>預約人</th>
					<th nowrap>預約日期</th><th nowrap>建立時間</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ bookingRooms }" var="booking">
					<tr>
						<td nowrap>
							<a href="javascript:void(0)" onClick="">
								取消
							</a>
						</td>
						<td nowrap>${ booking.bookingId }</td>
						<td nowrap>${ booking.roomId }</td>
						<td nowrap>${ booking.name }</td>
						<td nowrap>${ booking.date }</td>
						<td nowrap>${ booking.createTime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</body>
</html>