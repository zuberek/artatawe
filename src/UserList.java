package src;

import src.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to generate ArrayList's of User objects
 * @author Joshua Blackman
 *
 */
public class UserList {
	
	ArrayList<User> userList;
	
	public UserList() {
		
	}
	
	/**
	 * @param userID
	 * @return  arraylist of user objects that the given user has favourited
	 */
	public ArrayList<User> getFavouriteUsers(int userID){
		userList = new ArrayList<>();
		try {
			ResultSet rs = DB.select("SELECT * from `favourites` WHERE `userID` = '" + userID + "'");
			while(rs.next()) {
				User user  = new User(rs.getInt("favouriteID"));
				userList.add(user);
			}
		} catch (SQLException ex) {
			ex.getMessage();
		}
		
		return userList;
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
