package src;

import src.Bid;
import src.DB;

import java.util.ArrayList;

/**
 * This class is used to generate ArrayList's of src.User objects
 * @author Joshua Blackman
 *
 */
public class UserList {
	
	ArrayList<Bid> userList;
	
	public UserList() {
	}
	
//	/**
//	 * Returns an ArrayList of src.User objects that then can be used to populate a listview.
//	 * 
//	 * @param userId
//	 * @return an ArrayList of all bids specified user has made
//	 */
//	public ArrayList<src.Bid> getUserBidList(int userId) {
//		bidList = new ArrayList<src.Bid>();
//		try {
//			ResultSet rs = db.select("SELECT * from `bids` WHERE `bidderID` = '" + userId + "'");
//			while (rs.next()) {
//				src.Bid bid = new src.Bid(rs.getInt("bidID"));
//				bidList.add(bid);
//	        }
//		} catch (SQLException ex) {
//			ex.getMessage();
//		}
//		return bidList;
//	}
	

}
