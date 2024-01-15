package vending_machine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 銷貨紀錄
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesItem {
	private Integer id; // 銷貨 id
	private Integer productId; // 商品 id
	private String productName; // 商品名稱
	private Integer totalPrice; // 銷售金額
	private Integer totalAmount; // 銷售數量
	// 該銷售紀錄是屬於哪一個使用者 
	private Integer userId; // 使用者 id
	
	
}
