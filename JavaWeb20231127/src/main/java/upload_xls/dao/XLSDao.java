package upload_xls.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import dao.model.BookingRoom;

public class XLSDao {
	
private JdbcTemplate jdbcTemplate;
	
	public XLSDao() {
		String driverName = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/web?serverTimezone=Asia/Taipei";
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
	
	public int addEmployee(String name, String sex, int salary, int age) {
		String sql = "insert into employee(name, sex, salary, age) values(?, ?, ?, ?)";
		return jdbcTemplate.update(sql, name, sex, salary, age);
	}
	
}
