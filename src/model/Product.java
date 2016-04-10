package model;

public class Product {

	private String name;
	private String description;
	private double price;
	private String imageName;
	private boolean wasAddedSuccessfully=false;
	private int orderId;
	
	public Product(int orderId, String name, String description, double price, String imageName){
		this.orderId=orderId;
		this.name=name;
		this.description=description;
		this.price=price;
		this.imageName=imageName;
	}
	public Product(String name, String description, double price, String imageName){
		
		this.name=name;
		this.description=description;
		this.price=price;
		this.imageName=imageName;
	}
	public Product(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isWasAddedSuccessfully() {
		return wasAddedSuccessfully;
	}

	public void setWasAddedSuccessfully(boolean wasAddedSuccessfully) {
		this.wasAddedSuccessfully = wasAddedSuccessfully;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
