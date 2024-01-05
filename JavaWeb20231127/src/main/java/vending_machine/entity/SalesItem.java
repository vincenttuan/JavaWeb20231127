package vending_machine.entity;

// 銷貨紀錄
public class SalesItem {
	private Integer productId; // 商品 id
	private String productName;
	private Integer totalPrice; // 銷售金額
	private Integer totalAmount; // 銷售數量
	// 該銷售紀錄是屬於哪一個使用者 
	private Integer userId; // 使用者 id
	
	public SalesItem() {
		
	}
	
	public SalesItem(Integer productId, String productName, Integer totalPrice, Integer totalAmount, Integer userId) {
		this.productId = productId;
		this.productName = productName;
		this.totalPrice = totalPrice;
		this.totalAmount = totalAmount;
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SalesItem [productId=" + productId + ", productName=" + productName + ", totalPrice=" + totalPrice
				+ ", totalAmount=" + totalAmount + ", userId=" + userId + "]";
	}
	
	
	
}
