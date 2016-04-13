package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Product;
import model.Search;

public class SearchController {

	//this controller will take in the text that the user originally wrote and that the servlet passes to this
	//the product table is scanned and the string is compared to every product name and description
	
	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Ottomans";
	
	String search;

	Search itemsToDisplay;
	
	public SearchController(String search){
		this.search=search;
		itemsToDisplay = new Search();
	}
	public SearchController(){
		itemsToDisplay = new Search();
	}
	
	public Search getSearchResults(){
		scanProductsForMatches();
		itemsToDisplay.setStart(0);
		itemsToDisplay.setEnd(itemsToDisplay.getSearchArray().size());
		return itemsToDisplay;
	}
	public Search getRandomResults(){
		scanForRandomItems();
		itemsToDisplay.setStart(0);
		itemsToDisplay.setEnd(itemsToDisplay.getSearchArray().size());
		return itemsToDisplay;
	}
	private void scanProductsForMatches(){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT name, description, price, image_name FROM Inventory WHERE name LIKE '%"+search+"%'"+
			" OR keywords LIKE '%"+search+"%'";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				
				// Retrieve by column name
				
				String read_name = rs.getString("name");
				String read_desc = rs.getString("description");
				double read_price = rs.getDouble("price");
				String read_image_name = rs.getString("image_name");
			
				itemsToDisplay.addAResult(new Product(read_name, read_desc, read_price, read_image_name));
			}
			
			
			int i = itemsToDisplay.getSearchArray().size();
			int page = 1;
			while(i>itemsToDisplay.getPageResultMax()){
				i-=itemsToDisplay.getPageResultMax();
				page++;
			}
			itemsToDisplay.setNumOfPagesNeeded(page);
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
	}
	private void scanForRandomItems(){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT idProduct FROM Inventory";
			
			ResultSet rs = stmt.executeQuery(sql);
			int numFound=0;
			while (rs.next()) {
				numFound++;
			}
			
			sql = "SELECT name, description, price, image_name FROM Inventory";
			rs = stmt.executeQuery(sql);
			int index=0;
			int rowLine=0;
			int[] myArr = getRandomNums(numFound, 10);	//get a sorted array of at most 10 random integers between 0 and the amount of items
			while (rs.next()) {
				if(myArr[index]==rowLine){
					String read_name = rs.getString("name");
					String read_desc = rs.getString("description");
					double read_price = rs.getDouble("price");
					String read_image_name = rs.getString("image_name");
					itemsToDisplay.addAResult(new Product(read_name, read_desc, read_price, read_image_name));
					index++;
					if(index==10){	//prevents index out of bounds exception from occurring
						break;
					}
				}
				rowLine++;
			}
			Collections.shuffle(itemsToDisplay.getSearchArray());
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
	}
	private boolean isUnique(int num, int[] arr, int limit){
	for(int i=0;i<limit;i++){
		if(num==arr[i])
			return false;
	}
	return true;
	}
	private int[] getRandomNums(int randLimit, int pageLimit){
		int i=0, b=0;
		
		Random rand = new Random();
		if(randLimit>10){
			pageLimit=10;
		}else{
			pageLimit=randLimit;
		}
		int[] myArr = new int[pageLimit];
		while(i<pageLimit){
			b=rand.nextInt(randLimit);
			while(!isUnique(b, myArr, i)){
				b=rand.nextInt(randLimit);
			}
			
			myArr[i]=b;	
			i++;
		}
		Arrays.sort(myArr);
		return myArr;
	}
}
