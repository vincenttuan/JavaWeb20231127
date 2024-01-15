package vending_machine.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * 用於生成鹽(salt)和哈希(Hash)密碼的工具
 * */
public class SecurityUtils {
	
	// 生成鹽(salt)
	public static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt); // 隨機生成一組 byte[] 資訊
		String saltString = new BigInteger(1, salt).toString(); // 將 byte[] 轉換成一個數字字串, 1 表示正數
		return saltString;
	}
	
	public static void main(String[] args) {
		System.out.println("salt: " + generateSalt());
	}
	
}
