package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValidateRegister {

	private String fn, ln, pw, rpw, eA, bA, sA, encrCard, birthday;
	private String[] birthDayMonth;
	private int bM=0, bD=0, bY=0; 
	
	// Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "Pa$$word";

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Ottomans";
	
	
	public ValidateRegister(String firstName, String lastName, String password, String rePassword
			,String birthDay, String emailAddr
			,String addressBill, String addressShip, String cardNum){
//find all the errors and return a byte, every type of error is listed below
//any value with a null or blank ""
//any first or last name shorter than 2 chars or longer than 30
//password 
		fn=firstName;
		ln=lastName;
		pw=password;
		rpw=rePassword;
		birthday=birthDay;
		birthDayMonth=birthDay.split(",");
		birthDayMonth[1] = birthDayMonth[1].trim();
		bY = Integer.parseInt(birthDayMonth[1]);
		
		
		eA=emailAddr;
		bA=addressBill;
		sA=addressShip;
		//encrCard=Bitshifter.encrypt(cardNum);
		encrCard = cardNum;
	}
	SendEmail se;
	public boolean everythingIsValid(){
		if(namesAreValid()){
			if(passwordsAreValid()){
				if(birthIsValid()){
					if(emailIsValid()){
						if(physicalAddressIsValid()){
							if(cardIsValid()){
								se = new SendEmail();
								if(se.sendEmail(eA,"Go to this link to confirm your new Account!"
										+ " http://localhost:8080/FurnitureStore/OttoHome.jsp")){
									return addCustomerToTable(eA, pw, fn, ln,
											getAgeFromBD(), bA, sA, encrCard, birthday);
								}
								System.out.println("The email did not send");
							}
							System.out.println("The card is not valid");
						}
						System.out.println("Shipping or billing address not valid");
					}
					System.out.println("Email not valid according to my criteria");
				}
				System.out.println("Birth date not valid");
			}
			System.out.println("Names not valid");
		}
		return false;
	}
	private boolean namesAreValid(){
		if(!fn.equals("") && !ln.equals("")){
			return true;
		}
		return false;
	}
	private boolean passwordsAreValid(){	//I also encrypt the password here
		if(pw.equals("") && rpw.equals("")){
			return false;
		}
		if(pw.length()<1){
			return false;
		}
		if(!pw.equals(rpw)){
			return false;
		}
		//pw = Bitshifter.encrypt(pw);
		return true;
	}
	private boolean birthIsValid(){
		if(bY<1900 || bY>2000){
			return false;
		}
		return true;
	}
	
	private boolean emailIsValid(){
		if(eA.equals("")){
			return false;
		}
		if(!eA.contains("@")){
			return false;
		}
		if(eA.length()<5){
			return false;
		}
		if(!emailIsUnique()){
			return false;
		}
		return true;
	}
	
	private boolean emailIsUnique(){
		Connection conn = null;
		Statement stmt = null;
		boolean emailIsUnique=true;
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
			sql = "SELECT email FROM Users";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				String read_email = rs.getString("email");

				if(read_email.equals(eA)){
					emailIsUnique=false;
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
		return emailIsUnique;
	}//end check unique email
	
	private boolean physicalAddressIsValid(){
		if(bA.equals("") || sA.equals("")){
			return false;
		}
		return true;
	}
	
	//the card number the user tries to register is compared against the list of cards in the cards table
	private boolean cardIsValid(){
		System.out.println("I'm inside cardIsValid!!!");
		Connection conn = null;
		Statement stmt = null;
		boolean cardIsInTable=false;
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
			sql = "SELECT card_num FROM Credit_Cards";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				String encryptedCard = rs.getString("card_num");
				
				System.out.println("ValidateRegister from database: "+encryptedCard);
				System.out.println("ValidateRegister from user: "+encrCard+"\n");
	
				if(encryptedCard.equals(encrCard)){
					cardIsInTable=true;
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
		return cardIsInTable;
	}//end check if card is in table
	
	private int getAgeFromBD(){
		return 2016-bY;
	}
	
	private boolean addCustomerToTable(String eA, String pw, String fn, String ln,
			int age, String bill_address, String ship_address, String encrCard, String birthday) {
		try {
			// create a mysql database connection
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

			// the mysql insert statement
			String query = " INSERT INTO Users (idCustomer, email, password, first_name,"
			+ " last_name, age, bill_address, ship_address, card, birthday)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, eA);
			preparedStmt.setString(3, pw);
			preparedStmt.setString(4, fn);
			preparedStmt.setString(5, ln);
			preparedStmt.setInt(6, age);
			preparedStmt.setString(7, bill_address);
			preparedStmt.setString(8, ship_address);
			preparedStmt.setString(9, encrCard);
			preparedStmt.setString(10, birthday);

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
