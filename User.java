import java.util.ArrayList;

public class User {
	
	private String userName;	
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String userAddress;
	private int userID;
	private int unixLoginTStamp;
	private int unixLogoutTstamp;
	private Avatar profilePicture;
	private ArrayList<User>  favouriteUser;
	
	
	
	/**
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param phoneNo
	 * @param userAddress
	 */
	public User(String userName, String firstName, String lastName, String phoneNo, String userAddress) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.userAddress = userAddress;
		//this.profilePicture = default pircture???????????
	}

	public User(){
		System.out.println("test");
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
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the unixLoginTStamp
	 */
	public int getUnixLoginTStamp() {
		return unixLoginTStamp;
	}

	/**
	 * @param unixLoginTStamp the unixLoginTStamp to set
	 */
	public void setUnixLoginTStamp(int unixLoginTStamp) {
		this.unixLoginTStamp = unixLoginTStamp;
	}

	/**
	 * @return the unixLogoutTstamp
	 */
	public int getUnixLogoutTstamp() {
		return unixLogoutTstamp;
	}

	/**
	 * @param unixLogoutTstamp the unixLogoutTstamp to set
	 */
	public void setUnixLogoutTstamp(int unixLogoutTstamp) {
		this.unixLogoutTstamp = unixLogoutTstamp;
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
	
	
}
