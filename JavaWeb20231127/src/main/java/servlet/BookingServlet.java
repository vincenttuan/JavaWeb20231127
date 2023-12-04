package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/booking/*")
public class BookingServlet extends HttpServlet {
	
	private static List<Map<String, Object>> bookings = new CopyOnWriteArrayList<>();
	private AtomicInteger bookingIdCount = new AtomicInteger(0);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
				
				break;	
			case "/viewBookings": // 查看預約
				
				break;
			default: // 其他
				resp.sendError(500, "別鬧了~");
				break;
		}
		
	}
	
	
	
}
