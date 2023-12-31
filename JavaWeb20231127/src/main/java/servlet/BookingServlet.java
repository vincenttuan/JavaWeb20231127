package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDao;
import dao.BookingDaoImpl;
import dao.model.BookingRoom;

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
	
	private BookingDao dao = new BookingDaoImpl();
	
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
				// 得到表單傳來的資料
				String roomId = req.getParameter("roomId");
				String name = req.getParameter("name");
				String date = req.getParameter("date");
				// 將表單資料注入到 BookingRoom 物件 
				BookingRoom bookingRoom = new BookingRoom();
				bookingRoom.setRoomId(Integer.valueOf(roomId));
				bookingRoom.setName(name);
				bookingRoom.setDate(date);
				// 新增預約單
				
				String resultMessage = null;
				try {
					int rowcount = dao.addBookingRoom(bookingRoom);
					resultMessage = rowcount == 0 ? "新增失敗" : "新增成功";
				} catch (Exception e) {
					resultMessage = "新增失敗, 原因:" + 
							(e.getMessage().contains("Duplicate entry")?"重複預約":"其他錯誤");
				}
				
				// 傳送到指定 jsp 進行資料渲染
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bookingResult.jsp");
				req.setAttribute("resultMessage", resultMessage);
				rd.forward(req, resp);
				
				break;
			case "/cancelBooking": // 取消預約
				// 得到表單傳來要取消的預約單編號
				int bId = Integer.parseInt(req.getParameter("bookingId"));
				// 取消預約單
				int cancelRowcount = dao.cancelBookingRoomById(bId);
				String cancelResultMessage = cancelRowcount == 0 ? "取消失敗" : "取消成功";
				
				// 傳送到指定 jsp 進行資料渲染
				RequestDispatcher rd2 = req.getRequestDispatcher("/WEB-INF/views/bookingResult.jsp");
				req.setAttribute("resultMessage", cancelResultMessage);
				rd2.forward(req, resp);
				
				break;	
			case "/viewBookings": // 查看預約
			 	// 得到所有預約資料
				List<BookingRoom> bookingRooms = dao.findAllBookingRooms();
				// 傳送到指定 jsp 進行資料渲染
				RequestDispatcher rd3 = req.getRequestDispatcher("/WEB-INF/views/bookingList.jsp");
				req.setAttribute("bookingRooms", bookingRooms);
				rd3.forward(req, resp);
				
				break;
			default: // 其他
				String errorMessage = "無此路徑";
				// 傳送到指定 jsp 進行資料渲染
				RequestDispatcher rd4 = req.getRequestDispatcher("/WEB-INF/views/bookingResult.jsp");
				req.setAttribute("resultMessage", errorMessage);
				rd4.forward(req, resp);
				break;
		}
	}
}
