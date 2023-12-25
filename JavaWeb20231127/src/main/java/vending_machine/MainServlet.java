package vending_machine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vending_machine.dao.ProductDao;
import vending_machine.dao.ProductDaoImplInMemory;

@WebServlet("/vending_machine/main")
public class MainServlet extends HttpServlet {
	
	private ProductDao productDao = new ProductDaoImplInMemory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/vending_machine/main.jsp");
		req.setAttribute("products", productDao.findAllProducts());
		rd.forward(req, resp);
	}
	
}
