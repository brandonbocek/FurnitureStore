package model;

import java.util.ArrayList;
import model.Product;

public class Inventory {

	private ArrayList<Product> inventory;
	
	public Inventory(){
		inventory = new ArrayList<Product>();
	}
	
	public void addInventory(String name, String description, int price, String imageName){
		inventory.add(new Product(name, description, price, imageName));
	}
	
	public ArrayList<Product> getInventory(){
		return inventory;
	}
	
	public Product getProductFromInventory(int i){
		return inventory.get(i);
	}
	
	
}
