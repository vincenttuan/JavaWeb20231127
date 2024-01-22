package vending_machine;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/vending/main")
public class MainServlet extends HttpServlet {
	
	//private ProductDao productDao = new ProductDaoImplInMemory();
	private ProductDao productDao = new ProductDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}
	
	private void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vending_machine/main.jsp");
		List<Product> products = productDao.findAllProducts(); // 所有的進貨資料
		List<SalesItem> salesItems = productDao.findAllSalesItems(); // 所有的銷貨資料
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user"); // 取得使用者的登入資訊
		List<SalesItem> salesItemList = productDao.findAllSalesItemsByUserId(user.getId()); // 查詢該登入者所有的銷貨資料
		
		List<Map<String, Object>> groupBySalesItemList = productDao.groupBySalesItemByUserId(user.getId()); //  彙整銷售資料
		
		// deepClone
		List<Product> cloneProducts = new ArrayList<>();
		products.forEach(product -> {
			cloneProducts.add(new Product(product.getId(), product.getName(), product.getImageName(), product.getPrice(), product.getStock()));
		});
		
		// cloneProducts 扣除銷貨資料
		for(Product product : cloneProducts) {
			for(SalesItem salesItem : salesItems) {
				if(product.getId().equals(salesItem.getProductId())) {
					// 最新庫存量 = 目前庫存量 - 銷貨量
					product.setStock(product.getStock() - salesItem.getTotalAmount());
				}
			}
		}
		
		req.setAttribute("products", cloneProducts);
		req.setAttribute("salesItemList", salesItemList);
		req.setAttribute("groupBySalesItemList", groupBySalesItemList);
		rd.forward(req, resp);
	}
	
}
