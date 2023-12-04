package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 會議室預訂系統
 * 假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。
 * 
 * 功能要求：
 * -----------------------------------------------------------------------------------------------
 * 1.預訂會議室：
 * 路徑：/booking/bookRoom
 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * 返回：預訂成功或失敗的消息
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/JavaWeb20231127/booking/bookRoom?roomId=101&name=Tom&date=2023-12-04
 * 範例：http://localhost:8080/JavaWeb20231127/booking/bookRoom?roomId=102&name=Mary&date=2023-12-05
 * 範例：http://localhost:8080/JavaWeb20231127/booking/bookRoom?roomId=201&name=Jack&date=2023-12-06
 * 範例：http://localhost:8080/JavaWeb20231127/booking/bookRoom?roomId=403&name=Rose&date=2023-12-06
 * 
 * -----------------------------------------------------------------------------------------------
 * 2.取消預訂：
 * 路徑：/booking/cancelBooking?bookingId=?
 * 參數：預訂ID (bookingId)
 * 返回：取消成功或失敗的消息
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/JavaWeb20231127/booking/cancelBooking?bookingId=1
 * 
 * -----------------------------------------------------------------------------------------------
 * 3.查看所有預訂：
 * 路徑：/booking/viewBookings
 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/JavaWeb20231127/booking/viewBookings
 * */

@WebServlet(value = "/booking/*")
public class BookingServlet extends HttpServlet {
	
	private static List<Map<String, Object>> bookings = new CopyOnWriteArrayList<>();
	private AtomicInteger bookingIdCount = new AtomicInteger(0);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}

	private void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		
		String contextPath = getServletContext().getContextPath();
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();
		
		PrintWriter out = resp.getWriter();
		out.println("contextPath = " + contextPath);
		out.println("servletPath = " + servletPath);
		out.println("pathInfo = " + pathInfo);
		
		switch(pathInfo) {
			case "/bookRoom": // 預約房間
				
				String roomId = req.getParameter("roomId");
				String name = req.getParameter("name");
				String date = req.getParameter("date");
				
				boolean isBooked = bookings.stream()
						   .anyMatch(b -> b.get("roomId").equals(roomId) && b.get("date").equals(date));
				
				if(isBooked) {
					out.println(String.format("%s 於 %s 已被預訂", roomId, date));
					return;
				}
				
				Map<String, Object> bookRoom = new LinkedHashMap<>();
				int bookingId = bookingIdCount.incrementAndGet();
				bookRoom.put("bookingId", bookingId);
				bookRoom.put("roomId", roomId);
				bookRoom.put("name", name);
				bookRoom.put("date", date);
				
				bookings.add(bookRoom);
				
				out.print("預約成功, 預約編號: " + bookingId);
				
				break;
			case "/cancelBooking": // 取消預約
				int bId = Integer.parseInt(req.getParameter("bookingId"));
				// 是否有此預約紀錄 ?
				Optional<Map<String, Object>> opt = bookings.stream()
															.filter(b -> b.get("bookingId").equals(bId))
															.findFirst();
				if(opt.isEmpty()) {
					out.println(String.format("預約編號: %d 無此預約紀錄", bId));
					return;
				}
				
				// 將預約資料移除
				bookings.remove(opt.get());
				out.println(String.format("預約編號: %d 取消成功", bId));
				
				break;	
			case "/viewBookings": // 查看預約
				if(bookings.size() > 0) {
					bookings.forEach(booking -> out.println(booking));
				} else {
					out.println("None booking");
				}
				break;
			default: // 其他
				resp.sendError(500, "別鬧了~");
				break;
		}
		
	}
	
	
	
}
