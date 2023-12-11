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
		
		<table class="pure-table-bordered">
			<thead>
				<tr>
					<th>bookingId</th><th>roomId</th><th>name</th>
					<th>date</th><th>createTime</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ bookingRooms }" var="booking">
					<tr>
						<td>${ booking.bookingId }</td>
						<td>${ booking.roomId }</td>
						<td>${ booking.name }</td>
						<td>${ booking.date }</td>
						<td>${ booking.createTime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</body>
</html>