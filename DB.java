

import java.sql.*;
import java.util.ArrayList;

/**
 * This class handles connecting to the database and retrieving data.
 * @author Joshua Blackman
 */
public class DB {
	
	Connection conn = null;
	
	public DB() {
		connect();
		init();
	}
	
	private void connect() {        
        try {
            String connectionUrl = "jdbc:sqlite:artatawe.db";
            conn = DriverManager.getConnection(connectionUrl);        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * This method sets up all the tables
	 */
	private void init() {
		String sql = "CREATE TABLE IF NOT EXISTS `users` (\r\n" + 
					" `userID` INTEGER PRIMARY KEY,\r\n" + 
					" `userName` text NOT NULL,\r\n" + 
					" `firstName` text NOT NULL,\r\n" + 
					" `lastName` text NOT NULL,\r\n" + 
					" `phoneNo` int(11) NOT NULL,\r\n" + 
					" `userAddress` text NOT NULL,\r\n" + 
					" `lastLogin` int(11) NOT NULL\r\n" + 
					");\r\n" +
					"CREATE TABLE IF NOT EXISTS `bids` (\r\n" + 
						" `bidID` INTEGER PRIMARY KEY,\r\n" + 
						" `bidderID` int(11) NOT NULL,\r\n" + 
						" `auctionID` int(11) NOT NULL,\r\n" + 
						" `amount` double NOT NULL,\r\n" + 
						" `timePlaced` float NOT NULL\r\n" + 
					");";
		
		query(sql);
	}
	
	/**
	 * This method is used for executing a query on the database.
	 * @param query
	 */
	public void query(String query) {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @return ArrayList of objects from the select query
	 */
	public ResultSet select(String sql) {
	     Statement stmt;
	     ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			ex.getMessage();
		}  
	    return rs;
	}

    
}
