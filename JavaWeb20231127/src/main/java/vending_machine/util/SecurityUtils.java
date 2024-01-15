package vending_machine.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 用於生成鹽(salt)和哈希(Hash)密碼的工具
 * */
public class SecurityUtils {
	
	// 生成鹽(salt)
	public static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt); // 隨機生成一組 byte[] 資訊
		// 將鹽轉大正整數
		// return new BigInteger(1, salt).toString(); // 將 byte[] 轉換成一個數字字串, 1 表示正數
		// 將鹽轉base64(比較常見)
		return Base64.getEncoder().encodeToString(salt);
	}
	
	// 生成哈希
	public static String getHashPassword(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((salt + password).getBytes()); // 鹽 + password(明碼)
			byte[] hashedPassword = md.digest(); // 取得哈希值
			// 將哈希值轉一個大正整數
			//return new BigInteger(1, hashedPassword).toString();
			// 將哈希轉base64(比較常見)
			return Base64.getEncoder().encodeToString(hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String salt = generateSalt();
		System.out.println("salt: " + salt);
		String hashedPassword = getHashPassword("1234", salt);
		System.out.println("hashedPassword: " + hashedPassword);
		// 產生預設 user 資料表的資訊
		String[][] users = {
				{"john", "1234", "john@gmail.com"},
				{"jack", "1234", "jack@gmail.com"},
				{"rose", "1234", "rose@gmail.com"}
		};
		// 生成有 hash + salt 的 sql
		for(String[] user : users) {
			String username = user[0];
			String password = user[1];
			String email = user[2];
			String userSalt = generateSalt();
			String userHashedPassword = getHashPassword(password, userSalt);
			String sql = "insert into user(username, password, salt, email) values('%s', '%s', '%s', '%s')";
			System.out.printf(sql+"%n", username, userHashedPassword, userSalt, email);
		}
		
	}
	
}
