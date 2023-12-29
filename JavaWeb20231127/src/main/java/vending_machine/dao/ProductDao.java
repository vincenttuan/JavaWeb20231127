package vending_machine.dao;

import java.util.List;

import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;

public interface ProductDao {
	List<Product> findAllProducts();
	List<SalesItem> findAllSalesItems();
	Product getProductByName(String name);
	Product getProductById(Integer id);
}
