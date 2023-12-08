package dao;

import java.util.List;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancelBookingRoomById(Integer bookingId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BookingRoom> findAllBookingRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingRoom getBookingRoomById(Integer bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findAllRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoomById(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
