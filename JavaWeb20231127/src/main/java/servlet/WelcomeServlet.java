package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/welcome")
public class WelcomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼設定
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(new Date());
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>歡迎頁</title>");
		out.println("<link rel=\"stylesheet\" href=\"../css/demo.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1 class=\"my_red_color\">歡迎光臨~ Welcome !</h1>");
		out.println("<h2>現在時間: " + now + "</h2>");
		out.println("<img src=\"../image/car.webp\">");
		out.println("</body>");
		out.println("</html>");
	}
	
}
