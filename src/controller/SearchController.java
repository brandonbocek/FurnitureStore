package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public Search getSearchResults(){
		scanProductsForMatches();
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
}
