package dao;

import java.util.List;

import dao.model.BookingRoom;
import dao.model.Room;

public interface BookingDao {
	int addBookingRoom(BookingRoom bookingRoom);
	int cancelBookingRoomById(Integer bookingId);
	List<BookingRoom> findAllBookingRooms();
	BookingRoom getBookingRoomById(Integer bookingId);
	List<Room> findAllRooms();
	Room getRoomById(Integer roomId);
}
