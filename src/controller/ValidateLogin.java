package controller;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Customer;


public class ValidateLogin {

	Customer loggedIn;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Ottomans";

	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	//search the table and make sure the username or email does not already exist
	public boolean validateUserLogin(String email, String password) {
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
			sql = "SELECT email, password, first_name, last_name FROM Users";
			ResultSet rs = stmt.executeQuery(sql);
			
			//password = Bitshifter.encrypt(password);	//encrypt password so it can potentially match one in the database
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				String read_email = rs.getString("email");
				String read_password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				if(read_email.equals(email)){
					if(read_password.equals(password)){
						loggedIn = new Customer();
						loggedIn.setFirstName(firstName);
						loggedIn.setLastName(lastName);
						loggedIn.setEmail(read_email);
						loggedIn.setLoggedIn(true);
						loggedIn.setCartAmount(0);
						//I don't think I need to set the password to the model but maybe I do
						foundAMatch=true;
						break;
					}
				}
				// Display values
				
				System.out.print("email: " + read_email);
				System.out.println(", password: " + password);
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
		return foundAMatch;
	}// end ValidateUserLogin
	
	public String getCustomerFullName(){
		return loggedIn.getFirstName()+" "+loggedIn.getLastName();
	}
	
	public Customer getCustomerLoggedIn(){
		return loggedIn;
	}
	
}
