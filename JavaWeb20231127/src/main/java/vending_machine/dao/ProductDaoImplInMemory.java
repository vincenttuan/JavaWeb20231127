package vending_machine.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;
import vending_machine.entity.User;

public class ProductDaoImplInMemory implements ProductDao {
	private static List<User> users = new ArrayList<>();
	private static List<Product> products = new ArrayList<>();
	private static List<SalesItem> salesItems = new ArrayList<>();
	
	static {
		// 使用者
		users.add(new User(1, "john", "1234", "john@gmail.com"));
		users.add(new User(2, "jack", "5678", "jack@gmail.com"));
		users.add(new User(3, "rose", "1111", "rose@gmail.com"));
		
		// 進貨
		products.add(new Product(1, "台灣啤酒", "beer", 50, 5));
		products.add(new Product(2, "可口可樂", "cola", 25, 10));
		products.add(new Product(3, "柚子茶", "crapefruit", 20, 7));
		products.add(new Product(4, "提神飲料", "energy", 30, 10));
		products.add(new Product(5, "檸檬茶", "lemon", 20, 10));
		products.add(new Product(6, "橘子汽水", "orange", 35, 10));
		products.add(new Product(7, "覆盆子茶", "raspberry", 45, 10));
		products.add(new Product(8, "氣泡水", "soda", 15, 10));
		// 銷貨
		salesItems.add(new SalesItem(1, 1, "beer", 50, 1, 1));
		salesItems.add(new SalesItem(2, 2, "cola", 50, 2, 3));
		salesItems.add(new SalesItem(3, 8, "soda", 45, 3, 1));
		
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

	@Override
	public List<SalesItem> findAllSalesItems() {
		return salesItems;
	}

	@Override
	public Product getProductByImageName(String name) {
		return products.stream().filter(p -> p.getImageName().equals(name)).findFirst().get();
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public User getUser(String username) {
		Optional<User> userOpt = users.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
		return userOpt.isPresent() ? userOpt.get() : null;
	}

}
