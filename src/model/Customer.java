package model;

import java.util.ArrayList;
import java.util.Date;

public class Customer {

	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private int age;
	private String billingAddress;
	private String shippingAddress;
	private int cardNum;//?
	private boolean pointEnabled;
	private int cartAmount=7;
	
	private boolean isLoggedIn=false;
	
	private Cart cart;
	
	public Customer(){
		cart = new Cart();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public boolean isPointEnabled() {
		return pointEnabled;
	}

	public void setPointEnabled(boolean pointEnabled) {
		this.pointEnabled = pointEnabled;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public int getCartAmount() {
		return cart.getCartArray().size();
	}

	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}
	

	public ArrayList<Product> getCustomerOrders(){
		return cart.getCartArray();
	}
	public void fillCustomerCart(ArrayList<Product> cart){
		this.cart.clearAll();
		for(Product p : cart){
			this.cart.addProductToCart(p);
			System.out.println("Added a product to the user's cart");
		}
	}
	public void incrementCart(){
		cart.addProductToCart(new Product());
	}
	public Product getAnOrder(int i){
		return cart.getProductFromCart(i);
	}
	public int getMaxInCart(){
		return cart.getCartArray().size();
	}
	public void removeAnOrder(int orderID){
		cart.removeOrder(orderID);	
		
	}
	public Cart getCart(){
		return cart;
	}
	
	
}
