package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used to generate ArrayLists of User objects
 * @author Joshua Blackman
 *
 */
public class UserList {
	
	/**
	 * Gets the ID of the newest user to be created
	 * @return the ID of the newest user
	 */
	public static int getNewestUserID(){
		String query = "SELECT `userID` from `users` WHERE `userID` = (SELECT MAX(userID) from `users`)";
		ResultSet rs = DB.select(query);
		int userID = 0;
		try {
			if(rs.isBeforeFirst()){
				userID = rs.getInt("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}

	/**
	 * Creates the UserList object
	 */
	public UserList() {
		
	}
	
	/**
	 * Retrieves a list of favourited users
	 * @param userID ID of user for whom their favourites are being searched
	 * @return  ArrayList of user objects that the given user has favourited
	 */
	public static ArrayList<User> getFavouriteUsers(int userID){
		ArrayList<User> userList = new ArrayList<>();
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
}
