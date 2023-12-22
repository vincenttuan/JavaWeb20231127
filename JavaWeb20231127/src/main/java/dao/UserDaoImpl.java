package dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import dao.model.User;

public class UserDaoImpl implements UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl() {
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
	
	@Override
	public User login(String username, String password) {
		String sql = "select u_id, username, password, a_id from user where username = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			// 判斷 password 是否符合
			return user.getPassword().equals(password) ? user : null;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> findServicesByActorId(Integer a_Id) {
		String sql = "select a.a_id, s.s_id, s.servicename, s.serviceurl "
				+ "from actor a "
				+ "left join actor_ref_service r on a.a_id = r.a_id "
				+ "left join service s on r.s_id = s.s_id "
				+ "where a.a_id = ?";
		return jdbcTemplate.queryForList(sql, a_Id);
	}

}
