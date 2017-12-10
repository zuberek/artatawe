package src;

import java.sql.*;


/**
 * This class handles connecting to the database and retrieving data.
 * @author Joshua Blackman 
 * @author Jan Dabrowski
 */
public class DB {
	
	static Connection conn = null;
	
	/**
	 * Constructor create src.DB connection and calls init
	 */
	public DB() {
		connect();
		init();
	}
	
	/**
	 * Connects to the database. 
	 */
	private void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:artatawe.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * This method sets up all the tables
	 * TODO: Move to external file to make cleaner
	 */
	private void init() {
		String sql =
				"CREATE TABLE IF NOT EXISTS `artworks` (\r\n" +
						" `artworkID` INTEGER PRIMARY KEY,\r\n" +
						" `userID` int(11) NOT NULL,\r\n" +
						" `artist` text NOT NULL,\r\n" + 
						" `title` text NOT NULL,\r\n" + 
						" `description` text NOT NULL,\r\n" + 
						" `type` text NOT NULL,\r\n" + 
						" `material` text,\r\n" + 
						" `paintingType` text,\r\n" + 
						" `width` double NOT NULL,\r\n" +
						" `height` double NOT NULL,\r\n" +
						" `depth` double,\r\n" +
						" `photographPath` text NOT NULL,\r\n" +
						" `dateCreated` int(11)\r\n" +
						");\r\n" +
				"CREATE TABLE IF NOT EXISTS `auctions` (\r\n" +
						" `auctionID` INTEGER PRIMARY KEY,\r\n" +
						" `sellerID` int(11) NOT NULL,\r\n" +
						" `artworkID` int(11) NOT NULL,\r\n" +
						" `maxBids` int(11) NOT NULL,\r\n" +
						" `reservePrice` double NOT NULL,\r\n" +
						" `lastBidID` int(11),\r\n" +
						" `active` tinyint,\r\n" +
						" `timeAdded` DATETIME DEFAULT CURRENT_TIMESTAMP\r\n" +
						");\r\n" +
				"CREATE TABLE IF NOT EXISTS `users` (\r\n" +
					" `userID` INTEGER PRIMARY KEY,\r\n" + 
					" `userName` text UNIQUE NOT NULL,\r\n" + 
					" `firstName` text,\r\n" +
					" `lastName` text,\r\n" +
					" `phoneNo` text,\r\n" +
					" `userAddress` text,\r\n" +
					" `avatarPath` text,\r\n" +
					" `lastLogin` DATETIME DEFAULT CURRENT_TIMESTAMP\r\n" +
					");\r\n" +
				"CREATE TABLE IF NOT EXISTS `bids` (\r\n" +
				" `bidID` INTEGER PRIMARY KEY,\r\n" +
				" `bidderID` int(11) NOT NULL,\r\n" +
				" `auctionID` int(11) NOT NULL,\r\n" +
				" `amount` double NOT NULL,\r\n" +
				" `timePlaced` DATETIME DEFAULT CURRENT_TIMESTAMP\r\n" +
				");\r\n" +
				"CREATE TABLE IF NOT EXISTS `favourites` (\r\n" +
				"`userID` int(11) NOT NULL,\r\n"+
				"`favouriteID` int(11) NOT NULL\r\n" +
				");";
		//for debugging
		//System.out.println(sql);
		query(sql);
	}
	
	/**
	 * This method is used for executing a query on the database.
	 * @param query SQL query to be executed on database
	 */
	public static void query(String query) {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * This method exectues a query on the database and returns the ResultSet of the query
	 * @return ArrayList of objects from the select query
	 */
	public static ResultSet select(String sql) {
	     ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			ex.getMessage();
		}
	    return rs;
	}

	public void close() {
		try {
			if (conn != null) {
				//System.out.println("Closing the connection");
				conn.close();
			}
		} catch( Exception ex ) {
			System.out.println(( "Exception during connection.close()"));
		}
	}
}
