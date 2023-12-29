package vending_machine.entity;

// 銷貨紀錄
public class SalesItem {
	private Integer productId;
	private String productName;
	private Integer totalPrice; // 銷售金額
	private Integer totalAmount; // 銷售數量
	
	public SalesItem() {
		
	}
	
	public SalesItem(Integer productId, String productName, Integer totalPrice, Integer totalAmount) {
		this.productId = productId;
		this.productName = productName;
		this.totalPrice = totalPrice;
		this.totalAmount = totalAmount;
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

	@Override
	public String toString() {
		return "SalesItem [productId=" + productId + ", productName=" + productName + ", totalPrice=" + totalPrice
				+ ", totalAmount=" + totalAmount + "]";
	}
	
	
}
