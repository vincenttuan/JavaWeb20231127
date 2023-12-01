package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bmi") // servlet path = "/servlet/bmi"
public class BmiServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼設定
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 取得 form 表單傳來的參數
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");
		
		// 計算 bmi
		double h = Double.parseDouble(height);
		double w = Double.parseDouble(weight);
		double bmi = w / Math.pow(h/100, 2);
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>BMI</title>");
		out.println("<link rel=\"stylesheet\" href=\"../css/demo.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1 class=\"my_red_color\">BMI Result !</h1>");
		out.println("<h2>身高: " + h + "</h2>");
		out.println("<h2>體重: " + w + "</h2>");
		out.println("<h2>BMI: " + String.format("%.2f", bmi) + "</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
