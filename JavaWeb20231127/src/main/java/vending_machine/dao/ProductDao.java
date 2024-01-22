package vending_machine.dao;

import java.util.List;
import java.util.Map;

import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;
import vending_machine.entity.User;

public interface ProductDao {
	List<User> findAllUsers();
	void addUser(User user);
	User getUser(String username);
	
	List<Product> findAllProducts();
	List<SalesItem> findAllSalesItems();
	Product getProductByName(String name);
	Product getProductByImageName(String name);
	Product getProductById(Integer id);
	
	void addSalesItem(List<SalesItem> salesItems);
	List<SalesItem> findAllSalesItemsByUserId(Integer userId);
	List<Map<String, Object>> groupBySalesItemByUserId(Integer userId);
}
