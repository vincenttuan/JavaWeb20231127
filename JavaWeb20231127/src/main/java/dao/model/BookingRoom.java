package dao.model;

import java.sql.Timestamp;

// Entity 物件
public class BookingRoom {
	private Integer bookingId;
	private Integer roomId;
	private String name;
	private String date;
	private Timestamp createTime;
	
	// 關聯欄位
	private Room room;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "BookingRoom [bookingId=" + bookingId + ", roomId=" + roomId + ", name=" + name + ", date=" + date
				+ ", createTime=" + createTime + ", room=" + room + "]";
	}
	
	
	
}
