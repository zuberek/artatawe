package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class representing a user
 * @author Jan Dabrowski 916434
 * @author Joshua Blackman
 */
public class User {

	private String userName;	
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String userAddress;
	private int userID;
	private Date lastLogin;
	private String avatarPath;
	private ArrayList<User>  favouriteUser;

	/**
	 * Default constructor to initialise the object
	 */
	public User() {
		this.setDefaultAvatar("../Pictures/avatar1.png");
	}


	/**
	 * This constructor creates a new user and stores in database
	 * @param userName The user's username
	 * @param firstName The forename of the user
	 * @param lastName The surname of the user
	 * @param phoneNo The user's phone number
	 * @param userAddress The user's address
	 */
	public User(String userName, String firstName, String lastName, String phoneNo, String userAddress, String avatarPath) {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNo(phoneNo);
		setUserAddress(userAddress);
		setAvatarPath(avatarPath);
		saveUser();
	}
	
	/**
	 * This constructor uses username to get all user data
	 * @param userName username stored in database for data to be retrieved from
	 */
	public User(String userName){
		try{
			ResultSet rs = DB.select("SELECT * FROM `users` WHERE userName = '" + userName + "'");
			while (rs.next()) {
				this.setUserID(rs.getInt("userID"));
				this.setUserName(rs.getString("userName"));
				this.setFirstName(rs.getString("firstName"));
				this.setLastName(rs.getString("lastName"));
				this.setPhoneNo(rs.getString("phoneNo"));
				this.setUserAddress(rs.getString("userAddress"));
				this.setDefaultAvatar(rs.getString("avatarPath"));
				this.setLastLogin(rs.getTimestamp("lastLogin"));
	        }
			this.saveUserLogout();
		} catch(SQLException ex){
			ex.getMessage();
		}
	}
	

	/**
	 * This constructor uses the id to retrieve all user information
	 * @param userID user ID in database for data to be retrieved from
	 */
	public User(int userID){
		try{
			ResultSet rs = DB.select("SELECT * FROM `users` WHERE userID = '" + userID + "'");
			while (rs.next()) {
				this.setUserID(rs.getInt("userID"));
				this.setUserName(rs.getString("userName"));
				this.setFirstName(rs.getString("firstName"));
				this.setLastName(rs.getString("lastName"));
				this.setPhoneNo(rs.getString("phoneNo"));
				this.setUserAddress(rs.getString("userAddress"));
				this.setDefaultAvatar(rs.getString("avatarPath"));
				this.setLastLogin(rs.getTimestamp("lastLogin"));
	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
	}
	
	/**
	 * Check if the user specified by the username exists
	 * @param userName username of the user being checked
	 * @return boolean value if the user exists, true if exists, false if not
	 */
	public boolean userExists(String userName) {
		ResultSet rs = DB.select("SELECT * FROM `users` WHERE userName = '" + userName + "'");
		try {
			if (rs.next()){    
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Saving the new user to the database
	 */
	private void saveUser(){
		// Insert user into database
		DB.query("INSERT INTO `users` (`userName`, `firstName`, `lastName`, `phoneNo`, `userAddress`,`avatarPath`) VALUES ('" + this.getUserName() + "', '" + this.getFirstName() + "', '" + this.getLastName() + "', '" + this.getPhoneNo() +"', '" + this.getUserAddress() + "', '" +this.getDefaultAvatar() + "'); ");
	}

	/**
	 * Updating the edited user to the database
	 */
	public void saveEditedUser(){
		// Update edited user into database
		String query = "UPDATE `users` SET `userName` = '" + this.getUserName() + "', `firstName` = '" + this.firstName + "', `lastName` = '" + this.lastName + "', `phoneNo` = '" + this.phoneNo + "', `userAddress` = '" + this.userAddress + "', `avatarPath` = '" + this.avatarPath + "' WHERE `userID` = " + this.getUserID();
		//System.out.println(query);
		DB.query(query);
	}

	/**
	 * Updating the edited user's last logout time to the database
	 */
	public void saveUserLogout(){
		// Update last logout of the user
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String query = "UPDATE `users` SET `lastLogin` = '" + timestamp.getTime()/1000 + "' WHERE `userID` = " + this.getUserID();
		DB.query(query);
	}
	
	/**
	 * Marks a user as a favourite
	 * @param favouriteID the id of the user to favourite
	 */
	public void favouriteUser(int favouriteID) {
		DB.query("INSERT INTO `favourites` (`userID`, `favouriteID`) VALUES ('" + this.getUserID() + "', '" + favouriteID + "'); ");
	}

	/**
	 * Check if user has favourited another user
	 * @param favouriteID the id of the user to be checked
	 * @return boolean showing if user has favourited user
	 */
	public boolean isFavourite(int favouriteID) {
		ResultSet rs = DB.select("SELECT * FROM `favourites` WHERE `userID` = '" + this.getUserID() + "' AND `favouriteID` = '" + favouriteID + "'");
		try {
			if (rs.next()){    
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Deletes a user from their list of favourites
	 * @param favouriteID ID of the user to unfavourite
	 */
	public void unfavouriteUser(int favouriteID) {
		DB.query("DELETE FROM `favourites` WHERE `userID` = '" + this.getUserID() + "' AND `favouriteID` = '" + favouriteID + "'");
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		ResultSet rs = DB.select("SELECT userID FROM `users` WHERE `userName` = '" + this.getUserName() + "'");
		try {
			if(rs.next()) {
				setUserID(rs.getInt("userID"));
				return this.userID;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return this.userID;
	}

	/**
	 * @param userID the userID to set
	 */
	private void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the favouriteUser
	 */
	public ArrayList<User> getFavouriteUser() {
		return favouriteUser;
	}

	/**
	 * @param favouriteUser the favouriteUser to set
	 */
	public void setFavouriteUser(ArrayList<User> favouriteUser) {
		this.favouriteUser = favouriteUser;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return
	 */
	public String getDefaultAvatar() {
		return avatarPath;
	}

	/**
	 * @param defaultAvatar
	 */
	public void setDefaultAvatar(String defaultAvatar) {
		this.avatarPath = defaultAvatar;
	}

	/**
	 * @return path to the user's avatar image
	 */
	public String getAvatarPath() {
		return avatarPath;
	}

	/**
	 * @param avatarPath path to the user's avatar or new avatar
	 */
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	/**
	 * Returns a simple string description of the user
	 * @return the string of basic user information
	 */
	@Override
	public String toString(){
		String result = "";
		result += "User name: " + this.getFirstName() + " " + this.getLastName();
		return result;
	}
}
