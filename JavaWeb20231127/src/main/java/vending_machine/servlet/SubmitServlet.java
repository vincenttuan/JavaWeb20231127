package vending_machine.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vending_machine.dao.ProductDao;
import vending_machine.dao.ProductDaoImplInMemory;
import vending_machine.dao.ProductDaoMySQL;
import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;
import vending_machine.entity.User;

@WebServlet(value = "/vending/submit")
public class SubmitServlet extends HttpServlet {
	
	//private ProductDao productDao = new ProductDaoImplInMemory();
	private ProductDao productDao = new ProductDaoMySQL();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		List<SalesItem> salesItems = productDao.findAllSalesItems();
		List<SalesItem> currentSalesItems = new ArrayList<>(); // 紀錄本次消費紀錄
		
		Map<String, String[]> map = req.getParameterMap();
		map.entrySet().stream()
			.filter(entry -> entry.getKey().contains("_amount"))
			.forEach(entry -> {
			try {
				//resp.getWriter().println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
				String productName = entry.getKey().split("_")[0];
				Integer productAmount = Integer.parseInt(entry.getValue()[0]);
				if(productAmount > 0 && !productName.contains("coin")) {
					//resp.getWriter().println(productName + ":" + productAmount);
					Product product = productDao.getProductByImageName(productName);
					SalesItem salesItem = new SalesItem();
					salesItem.setProductId(product.getId());
					salesItem.setProductName(productName);
					salesItem.setTotalPrice(product.getPrice());
					salesItem.setTotalAmount(productAmount);
					salesItem.setUserId(user.getId());
					salesItems.add(salesItem); // 加入到總資料庫
					currentSalesItems.add(salesItem); // 加入到本次消費紀錄集合
				}
				
			} catch (Exception e) {
			}
		});
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vending_machine/result.jsp");
		req.setAttribute("currentSalesItems", currentSalesItems);
		rd.forward(req, resp);
		
	}
	
}
