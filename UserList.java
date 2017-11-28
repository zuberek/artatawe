import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to generate ArrayList's of User objects
 * @author Joshua Blackman
 *
 */
public class UserList {
	
	ArrayList<Bid> userList;
	DB db;
	
	public UserList() {
		db = new DB();
	}
	
//	/**
//	 * Returns an ArrayList of User objects that then can be used to populate a listview.
//	 * 
//	 * @param userId
//	 * @return an ArrayList of all bids specified user has made
//	 */
//	public ArrayList<Bid> getUserBidList(int userId) {
//		bidList = new ArrayList<Bid>();
//		try {
//			ResultSet rs = db.select("SELECT * from `bids` WHERE `bidderID` = '" + userId + "'");
//			while (rs.next()) {
//				Bid bid = new Bid(rs.getInt("bidID"));
//				bidList.add(bid);
//	        }
//		} catch (SQLException ex) {
//			ex.getMessage();
//		}
//		return bidList;
//	}
	

}
