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
		
	}
	public FindChosenProductController(String name, ArrayList<Product> products){
		this.name=name;
		this.products=products;
	}
	public FindChosenProductController(Customer customer, String productName){
		this.toAdd=getProductFromDataBase(productName);
		this.customer = customer;
		writeHomeOrderToDataBase();
	}
	public FindChosenProductController(String productName){
		this.toAdd = getProductFromDataBase(productName);
	}
	public Product getProductToViewFromDataBase(){
		return toAdd;
	}
	
	public Product getChosenProduct(){
		for(Product p : products){
			if(name.equals(p.getName())){
				return p;
			}
		}
		return null;
	}
	private boolean writeOrderToDataBase(String customerEmail, String productName, double productPrice, String pd, String img){
		
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
	private boolean writeHomeOrderToDataBase(){
			
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
			preparedStmt.setString(2, customer.getEmail());
			preparedStmt.setString(3, toAdd.getName());
			preparedStmt.setDouble(4, toAdd.getPrice());
			preparedStmt.setString(5, toAdd.getDescription());
			preparedStmt.setString(6, toAdd.getImageName());
			
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
	private Product getProductFromDataBase(String productName){
		Product toReturn=null;
		Connection conn = null;
		Statement stmt = null;
		boolean foundAMatch=false;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idProduct, name, description, price, image_name FROM Inventory";
			ResultSet rs = stmt.executeQuery(sql);
			
			//password = Bitshifter.encrypt(password);	//encrypt password so it can potentially match one in the database
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				int idProduct = rs.getInt("idProduct");
				String nameOfProduct = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				String imageName = rs.getString("image_name");
				if(productName.equals(nameOfProduct)){
					toReturn = new Product(nameOfProduct, description, price, imageName);
					break;
				}
				
				
				
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return toReturn;
	}
}
