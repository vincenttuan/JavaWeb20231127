package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import dao.model.BookingRoom;
import dao.model.Room;

public class BookingDaoImpl implements BookingDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public BookingDaoImpl() {
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
	public int addBookingRoom(BookingRoom bookingRoom) {
		String sql = "insert into BookingRoom(roomid, name, date) values(?, ?, ?)";
		return jdbcTemplate.update(sql, 
				bookingRoom.getRoomId(), bookingRoom.getName(), bookingRoom.getDate());
	}

	@Override
	public int cancelBookingRoomById(Integer bookingId) {
		String sql = "delete from BookingRoom where bookingId = ?";
		return jdbcTemplate.update(sql, bookingId);
	}

	@Override
	public List<BookingRoom> findAllBookingRooms() {
		String sql = "select bookingId, roomId, name, date, createTime from BookingRoom order by bookingId";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookingRoom.class));
	}

	@Override
	public BookingRoom getBookingRoomById(Integer bookingId) {
		String sql = "select bookingId, roomId, name, date, createTime from BookingRoom where bookingId = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookingRoom.class), bookingId);
	}

	@Override
	public List<Room> findAllRooms() {
		String sql = "select roomId, name from Room order by roomId";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
	}

	@Override
	public Room getRoomById(Integer roomId) {
		String sql = "select roomId, name from Room where roomId = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), roomId);
	}

}
