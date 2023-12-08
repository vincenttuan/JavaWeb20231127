package dao.model;

// Entity 物件
public class Room {
	private Integer roomId;
	private String name;
	
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
	
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + "]";
	}
	
	
	
}
