package dao;

import java.util.List;
import java.util.Map;

import dao.model.User;

public interface UserDao {
	
	User login(String username, String password);
	
	List<Map<String, Object>> findServiceByActorId(Integer a_Id);
	
}
