package model;

import java.util.ArrayList;

public class Cart {

	//a cart has a collection of orders
	ArrayList<Product> selections;
	
	public Cart(){
		selections = new ArrayList<Product>();
	}
	
	public void addProductToCart(Product p){
		selections.add(p);
	}
	
	public Product getProductFromCart(int i){
		return selections.get(i);
	}
	
	public ArrayList<Product> getCartArray(){
		return selections;
	}
	public boolean removeOrder(int order){
		int i=0;
		for(Product p : selections){
			if(order==p.getOrderId()){
				selections.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}
	public void clearAll(){
		selections.clear();
	}
	public double getPriceTotal(){
		int i=0;
		double totalPrice=0;
		for(Product p : selections){
			totalPrice += selections.get(i).getPrice();
			i++;
		}
		return totalPrice;
	}
}
