import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	Connection conn = null;
	
	public DB() {
		connect();
	}
	
	
	public void connect() {        
        try {
            String url = "jdbc:sqlite:artatawe.db";
            conn = DriverManager.getConnection(url);
            
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
	
	

    
}
