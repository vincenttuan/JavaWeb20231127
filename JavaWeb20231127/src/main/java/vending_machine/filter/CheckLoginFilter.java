package vending_machine.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vending_machine.dao.ProductDao;
import vending_machine.dao.ProductDaoImplInMemory;
import vending_machine.entity.User;

//@WebFilter(value = {"/vending/main", "/vending/submit"})
//@WebFilter(value = {"/vending/*"})
@WebFilter("/vending/*")
public class CheckLoginFilter extends HttpFilter {
	private ProductDao dao = new ProductDaoImplInMemory();
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// UTF-8 編碼
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		//res.setCharacterEncoding("text/html;chatset=utf-8");
		
		// 重導到登入頁面的位置
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vending_machine/userLogin.jsp");
		
		// 1. 先檢查 session 變數中是否有 user 物件資料
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null) { // session 變數中沒有 user 資料
			// 檢查是否有帶入 username 與 password
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String code = req.getParameter("code");
			if(username == null || password == null || code == null) {
				// 重導到登入頁面 
				rd.forward(req, res);
				return;
			}
			// 進行登入檢查
			// 檢查 code
			if(!code.equals(session.getAttribute("code")+"")) { // code 不一致
				// 重導到登入頁面 
				rd.forward(req, res);
				return;
			}
			
			// 檢查 username
			User user = dao.getUser(username);
			if(user == null) { // 無此使用者 
				// 重導到登入頁面 
				rd.forward(req, res);
				return;
			}
			
			// 檢查 password
			if(user.getPassword().equals(password)) { // 檢查密碼
				session.setAttribute("user", user); // 將 user 物件寫入到 session 變數中存放 
				
			} else { // 密碼比對失敗
				// 重導到登入頁面 
				rd.forward(req, res);
				return;
			}
			
		}
		
		// 2. 放行
		chain.doFilter(req, res);
	}
	
}
