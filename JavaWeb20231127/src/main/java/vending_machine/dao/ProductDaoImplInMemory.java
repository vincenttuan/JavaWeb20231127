package vending_machine.dao;

import java.util.ArrayList;
import java.util.List;

import vending_machine.entity.Product;

public class ProductDaoImplInMemory implements ProductDao {
	private List<Product> products;
	
	public ProductDaoImplInMemory() {
		products = new ArrayList<>();
		products.add(new Product(1, "台灣啤酒", "beer", 50, 10));
		products.add(new Product(2, "可口可樂", "cola", 25, 10));
		products.add(new Product(3, "柚子茶", "crapefruit", 20, 10));
		products.add(new Product(4, "提神飲料", "energy", 30, 10));
		products.add(new Product(5, "檸檬茶", "lemon", 20, 10));
		products.add(new Product(6, "橘子汽水", "orange", 35, 10));
		products.add(new Product(7, "覆盆子茶", "raspberry", 45, 10));
		products.add(new Product(8, "氣泡水", "soda", 15, 10));
	}
	
	@Override
	public List<Product> findAllProducts() {
		return products;
	}

	@Override
	public Product getProductByName(String name) {
		return products.stream().filter(p -> p.getName().equals(name)).findFirst().get();
	}

	@Override
	public Product getProductById(Integer id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
	}

}
