package vending_machine.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import vending_machine.entity.Product;
import vending_machine.entity.SalesItem;
import vending_machine.entity.User;

public class ProductDaoMySQL implements ProductDao {
	
private JdbcTemplate jdbcTemplate;
	
	public ProductDaoMySQL() {
		String driverName = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/vending_machine?serverTimezone=Asia/Taipei";
		String username = "root";
		String password = "12345678";
		
		// 設定資料來源
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		// 將資料來源注入給 jdbcTemplate
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> findAllUsers() {
		String sql = "select id, username, password, salt, email from user";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		return users;
	}

	@Override
	public void addUser(User user) {
		String sql = "insert into user(username, password, salt, email) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getEmail());
	}

	@Override
	public User getUser(String username) {
		String sql = "select id, username, password, salt, email from user where username = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
	}

	@Override
	public List<Product> findAllProducts() {
		String sql = "select id, name, image_name, price, stock from product";
		List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
		return products;
	}

	@Override
	public List<SalesItem> findAllSalesItems() {
		String sql = "select id, product_id, product_name, total_amount, total_price, user_id from sales_item";
		List<SalesItem> salesItems = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SalesItem.class));
		return salesItems;
	}

	@Override
	public Product getProductByName(String name) {
		String sql = "select id, name, image_name, price, stock from product where name = ?";
		Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), name);
		return product;
	}

	@Override
	public Product getProductByImageName(String imageName) {
		String sql = "select id, name, image_name, price, stock from product where image_name = ?";
		Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), imageName);
		return product;
	}

	@Override
	public Product getProductById(Integer id) {
		String sql = "select id, name, image_name, price, stock from product where id = ?";
		Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
		return product;
	}

	@Override
	public void addSalesItem(List<SalesItem> salesItems) {
		String sql = "insert into sales_item(product_id, product_name, total_amount, total_price, user_id) values (?, ?, ?, ?, ?)";
		List<Object[]> batchArgs = new ArrayList<>();
		for(SalesItem salesItem : salesItems) {
			Object[] batchArg = {
					salesItem.getProductId(), 
					salesItem.getProductName(), 
					salesItem.getTotalAmount(), 
					salesItem.getTotalPrice(), 
					salesItem.getUserId()
			};
			batchArgs.add(batchArg);
		}
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Override
	public List<SalesItem> findAllSalesItemsByUserId(Integer userId) {
		String sql = "select id, product_id, product_name, total_amount, total_price, user_id from sales_item where user_id = ?";
		List<SalesItem> salesItems = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SalesItem.class), userId);
		return salesItems;
	}

	@Override
	public List<Map<String, Object>> groupBySalesItemByUserId(Integer userId) {
		String sql = "SELECT "
				+ "	p.name, SUM(s.total_amount) as total_amount "
				+ " FROM "
				+ "	sales_item s, product p "
				+ " WHERE user_id = ? AND s.product_name = p.image_name "
				+ " group by p.name";
		return jdbcTemplate.queryForList(sql, userId);
	}

}
