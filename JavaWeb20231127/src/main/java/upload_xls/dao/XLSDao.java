package upload_xls.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import dao.model.BookingRoom;
import upload_xls.entity.Employee;

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
	
	public int[] batchAddemployees(List<Employee> employees) {
		String sql = "insert into employee(name, sex, salary, age) values(?, ?, ?, ?)";
		
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getName());
				ps.setString(2, employee.getSex());
				ps.setInt(3, employee.getSalary());
				ps.setInt(4, employee.getAge());
			}
			
			@Override
			public int getBatchSize() {
				return employees.size();
			}
		};
		
		int[] counts = jdbcTemplate.batchUpdate(sql, setter); 
		return counts;		
	} 
	
}
