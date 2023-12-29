package vending_machine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vending_machine.dao.ProductDao;
import vending_machine.dao.ProductDaoImplInMemory;
import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;

@WebServlet("/vending_machine/main")
public class MainServlet extends HttpServlet {
	
	private ProductDao productDao = new ProductDaoImplInMemory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vending_machine/main.jsp");
		List<Product> products = productDao.findAllProducts(); // 所有的進貨資料
		List<SalesItem> salesItems = productDao.findAllSalesItems(); // 所有的銷貨資料
		
		// deepClone
		List<Product> cloneProducts = new ArrayList<>();
		products.forEach(product -> {
			cloneProducts.add(new Product(product.getId(), product.getName(), product.getImageName(), product.getPrice(), product.getStock()));
		});
		
		req.setAttribute("products", cloneProducts);
		rd.forward(req, resp);
	}
	
}
