<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking List</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<style>
	        .button-success,
	        .button-error,
	        .button-warning,
	        .button-secondary {
	            color: white;
	            border-radius: 4px;
	            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
	        }
	
	        .button-success {
	            background: rgb(28, 184, 65);
	            /* this is a green */
	        }
	
	        .button-error {
	            background: rgb(202, 60, 60);
	            /* this is a maroon */
	        }
	
	        .button-warning {
	            background: rgb(223, 117, 20);
	            /* this is an orange */
	        }
	
	        .button-secondary {
	            background: rgb(66, 184, 221);
	            /* this is a light blue */
	        }
	    </style>
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
							<a href="javascript:void(0)" 
							   onClick="location.href='/JavaWeb20231127/booking/cancelBooking?bookingId=${ booking.bookingId }'"
							   class="button-error pure-button">
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