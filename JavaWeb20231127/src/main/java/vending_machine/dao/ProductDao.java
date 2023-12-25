package vending_machine.dao;

import java.util.List;

import vending_machine.entity.Product;

public interface ProductDao {
	List<Product> findAllProducts();
	Product getProductByName(String name);
	Product getProductById(Integer id);
}
