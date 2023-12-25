package vending_machine.dao;

import java.util.ArrayList;
import java.util.List;

import vending_machine.entity.Product;

public class ProductDaoImplInMemory implements ProductDao {
	private List<Product> products;
	
	public ProductDaoImplInMemory() {
		products = new ArrayList<>();
		
	}
	
	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
