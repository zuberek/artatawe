package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 916434
 *
 */
public class User {
	
	private DB db;
	private String userName;	
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String userAddress;
	private int userID;
	private int lastLogin;
	private Avatar profilePicture;
	private ArrayList<User>  favouriteUser;
	
	/**
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param phoneNo
	 * @param userAddress
	 * @param lastLogin
	 */
	public User(String userName, String firstName, String lastName, String phoneNo, String userAddress, int lastLogin) {
		db = new DB();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.userAddress = userAddress;
		this.lastLogin = lastLogin;
		//this.profilePicture = default pircture???????????
		saveUser();
	}
	
	/**
	 * @param userName
	 */
	public User(String userName){
		db = new DB();
		try{
			ResultSet rs = db.select("SELECT * FROM `users` WHERE userName = '" + userName + "'");
			while (rs.next()) {
				this.setUserID(rs.getInt("userID"));
				this.setUserName(rs.getString("userName"));
				this.setFirstName(rs.getString("firstName"));
				this.setLastName(rs.getString("lastName"));
				this.setPhoneNo(rs.getString("phoneNo"));
				this.setUserAddress(rs.getString("userAddress"));
				this.setLastLogin(rs.getInt("timePlaced"));

	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
	}
	
	/**
	 * Saving the new user to the database
	 */
	private void saveUser(){
		// Insert user into database
		db.query("INSERT INTO `users` (`userName`, `firstName`, `lastName`, `phoneNo`, `userAddress`, `lastLogin`) VALUES ('" + this.getUserName() + "', '" + this.getFirstName() + "', '" + this.getLastName() + "', '" + this.getPhoneNo() +"', '" + this.getUserAddress() + "', '" + this.getLastLogin() + "'); ");
	}

	/**
	 * Updating the edited user to the database
	 * TODO: resolve the problem with locked database
	 */
	public void saveEditedUser(){
		// Update edited user into database
		String query = "UPDATE `users` SET `userName` = '" + this.getUserName() + "', `firstName` = '" + this.firstName + "' WHERE `userID` = " + this.getUserID();
		//System.out.println(query);
		db.query(query);
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
		return this.userID;
	}

	/**
	 * @param userID the userID to set
	 */
	private void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the profilePicture
	 */
	public Avatar getProfilePicture() {
		return profilePicture;
	}

	/**
	 * @param profilePicture the profilePicture to set
	 */
	public void setProfilePicture(Avatar profilePicture) {
		this.profilePicture = profilePicture;
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
	public int getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(int lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	@Override
	public String toString(){
		String result = "";
		result += "User name: " + this.getFirstName() + " " + this.getLastName();
		return result;
	}
}