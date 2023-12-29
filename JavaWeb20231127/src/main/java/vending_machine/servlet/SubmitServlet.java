package vending_machine.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/vending/submit")
public class SubmitServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		Map<String, String[]> map = req.getParameterMap();
		map.entrySet().forEach(entry -> {
			try {
				resp.getWriter().println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
			} catch (Exception e) {
			}
		});
	}
	
}
