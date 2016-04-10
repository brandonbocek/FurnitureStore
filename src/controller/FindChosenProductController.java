package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Product;

public class FindChosenProductController {

	String name;
	ArrayList<Product> products;
	Product toAdd;
	Customer customer;
	PopulateCartController pcc;
	
	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Ottomans";
	
	//write the order to the orders database and get back the number of items the customer has in the database
	
	public FindChosenProductController(String name, ArrayList<Product> products, Customer customer){
		this.name=name;
		this.products=products;
		this.customer = customer;
		writeOrderToDataBase(customer.getEmail(), getChosenProduct().getName(), getChosenProduct().getPrice(),
				getChosenProduct().getDescription(), getChosenProduct().getImageName());
		pcc = new PopulateCartController();
	}
	
	private Product getChosenProduct(){
		for(Product p : products){
			if(name.equals(p.getName())){
				return p;
			}
		}
		return null;
	}
	private boolean writeOrderToDataBase(String customerEmail, String productName, double productPrice, String pd, String img){
		System.out.println("What I'm trying to add into the table "+ customerEmail + " "+productName +" "+ productPrice);
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = " INSERT INTO Orders (idOrder, email, item_name, item_price, item_desc, image_name)"
			+ " values (?, ?, ?, ?, ?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, customerEmail);
			preparedStmt.setString(3, productName);
			preparedStmt.setDouble(4, productPrice);
			preparedStmt.setString(5, pd);
			preparedStmt.setString(6, img);
			
			// execute the prepared statement
			preparedStmt.execute();

			conn.close();
			return true;
		} catch (Exception e) {
			System.err.println("I can't write to the data base!");
			System.err.println(e.getMessage());
		}
		return false;
	}
	
}
