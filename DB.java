/*
 * @author Joshua Blackman
 */

import java.sql.*;

public class DB {
	
	Connection conn = null;
	
	public DB() {
		connect();
	}
	
	
	public void connect() {        
        try {
            String connectionUrl = "jdbc:sqlite:artatawe.db";
            conn = DriverManager.getConnection(connectionUrl);
            
            System.out.println("Connected to SQLLite DB.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
	
	public void query(String query) {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

    
}
