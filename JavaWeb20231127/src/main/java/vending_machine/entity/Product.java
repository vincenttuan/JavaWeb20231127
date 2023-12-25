package vending_machine.entity;

public class Product {
	private Integer id;
	private String name;
	private String imageName;
	private Integer price;
	private Integer stock;
	
	public Product() {
		
	}
	
	public Product(Integer id, String name, String imageName, Integer price, Integer stock) {
		this.id = id;
		this.name = name;
		this.imageName = imageName;
		this.price = price;
		this.stock = stock;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
