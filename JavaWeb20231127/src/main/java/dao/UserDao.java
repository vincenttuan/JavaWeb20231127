package dao;

import java.util.List;
import java.util.Map;

import dao.model.User;

public interface UserDao {
	
	User login(String username, String password); // 驗證
	
	List<Map<String, Object>> findServicesByActorId(Integer a_Id); // 授權使用的服務
	
}
