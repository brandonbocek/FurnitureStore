package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.CreditCard;
import model.Inventory;
import model.Product;

public class AdminController {

	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Ottomans";

	Product product;
	CreditCard card;
	
	public AdminController() {
		
	}

	private boolean infoIsValid(String name, double price) {
		boolean validProduct = false;
		if(price>0 && !name.equals("")){
			validProduct=true;
		}
		
		return validProduct;
	}
	
	public boolean addInventory(String name, String description, double price, String imageName, String kw){
		if(infoIsValid(name, price)){
			return addProductToInventoryTable(name, description, price, imageName, kw);
		}else{
			return false;
		}
	}
	private boolean addProductToInventoryTable(String name, String desc, double price, String imageName, String pkw) {
		product = new Product(name, desc, price, imageName);
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = " INSERT INTO Inventory (idProduct, name, description, price, image_name, keywords)" +
			" values (?, ?, ?, ?, ?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, desc);
			preparedStmt.setDouble(4, price);
			preparedStmt.setString(5, imageName);
			preparedStmt.setString(6, pkw);
			
			// execute the prepared statement
			preparedStmt.execute();

			conn.close();
			
			product.setWasAddedSuccessfully(true);
			return true;
		} catch (Exception e) {
			System.err.println("I can't write to the data base!");
			System.err.println(e.getMessage());
		}
		return false;
	}
	public boolean addCreditCard(String cardNum){
		
		try{
			Double.parseDouble(cardNum);
		}catch(NumberFormatException exc){
			System.out.println("Failed to parse");
			return false;
		}
		
		System.out.println("Admin before encryption "+cardNum);
		//String encrNum = Bitshifter.encrypt(cardNum);
		String encrNum = cardNum;
		System.out.println("Admin after encryption "+encrNum);
		
		if(addCardToTable(encrNum)){
			return true;
		}
		return false;
	}
	
	private boolean addCardToTable(String number){
		card = new CreditCard();
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = " INSERT INTO Credit_Cards (idCard, card_num)" + " values (?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, number);
			

			// execute the prepared statement
			preparedStmt.execute();
			String query1 = " SELECT * FROM Credit_Cards";
			ResultSet rs = preparedStmt.executeQuery(query1);
			while (rs.next()) {
				String read_card = rs.getString("card_num");
				System.out.println("This is after the db"+read_card);
			}
			card.setWasAddedToTable(true);
			conn.close();
			return true;
		} catch (Exception e) {
			System.err.println("I can't write to the data base!");
			System.err.println(e.getMessage());
		}
		return false;
	}
	public boolean getProductAddStatus(){
		try{
			return product.isWasAddedSuccessfully();
		}catch(NullPointerException exc){
			return false;
		}
	}
	public boolean getCardAddStatus(){
		try{
			return card.isWasAddedToTable();
		}catch(NullPointerException exc){
			return false;
		}
	}
}

