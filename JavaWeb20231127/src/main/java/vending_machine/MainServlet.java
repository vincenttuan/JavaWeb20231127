package vending_machine;

import java.io.IOException;
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
		List<Product> products = productDao.findAllProducts();
		List<SalesItem> salesItems = productDao.findAllSalesItems();
				
		req.setAttribute("products", productDao.findAllProducts());
		rd.forward(req, resp);
	}
	
}
