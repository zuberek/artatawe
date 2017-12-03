package src;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

/**
 * This class stores/handles bids.
 * @author Joshua Blackman
 *
 */
public class Bid {
	
	private DB db;
	private int bidID;
	private int bidderID;
	private int auctionID;
	private float amount;
	private Date timePlaced;
	
	/**
	 * This constructor is used for when you want to create a new bid
	 * @param bidderID the userID of who made this bid.
	 * @param auctionID  the auctionID of the auction this bid was made on.
	 * @param amount the amount that was placed on the bid
	 */
	public Bid(int bidderID, int auctionID, float amount){
		db = new DB();
		setBidderID(bidderID);
		setAuctionID(auctionID);
		setAmount(amount);
		saveBid();
	}
	
	/**
	 * This constructor is used when you want to retrieve the information about a bid.
	 * @param bidID the bidID of the bid you want to retrieve.
	 */
	public Bid(int bidID){
		db = new DB();
		try{
			ResultSet rs = db.select("SELECT * FROM `bids` WHERE bidID = '" + bidID + "'");
			while (rs.next()) {
				setBidID(rs.getInt("bidID"));
				setBidderID(rs.getInt("bidderID"));
				setAuctionID(rs.getInt("auctionID"));
				setAmount(rs.getFloat("amount"));
				setTimePlaced((Date)rs.getTimestamp("timePlaced"));
	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
		db.closeQuietly();
	}
	
	/**
	 * This method the current bid into the database
	 */
	private void saveBid(){
		// Insert bid into database
		// TODO: need to make sure these inputs are sanitized to avoid sql injection
		db.query("INSERT INTO `bids` (`bidderID`, `auctionID`, `amount`) VALUES (" + this.getBidderID() + ", " + this.getAuctionID() + ", "  + this.getAmount() + "); ");
		db.closeQuietly();
	}
	
	/**
	 * Gets the current BidID
	 * @return the bid id of current object
	 */
	public int getBidID() {
		return bidID;
	}
	
	/**
	 * Sets the id of the bid
	 * @param bidID set the bidID
	 */
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}
	
	/**
	 * Returns the bidderID of the bid
	 * @return an int id of the bidder who made this bid
	 */
	public int getBidderID() {
		return bidderID;
	}
	
	/**
	 * Sets the bidderID to the id of the user that made the bid
	 * @param bidderID bidderID to set
	 */
	public void setBidderID(int bidderID) {
		this.bidderID = bidderID;
	}
	
	/**
	 * Returns the amount placed on a bid
	 * @return amount
	 */
	public float getAmount() {
		return amount;
	}
	
	/**
	 * Sets amount of money placed on the auction
	 * @param amount 
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the time placed
	 * @return  the unix timestamp of when the bid was made
	 */
	public Date getTimePlaced() {
		return timePlaced;
	}
	
	/**
	 * Sets the unix timestamp of when the bid was placed
	 * @param timePlaced 
	 */
	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}
	
	/**
	 * Returns the auctionID
	 * @return the auctionID as an int
	 */
	public int getAuctionID() {
		return auctionID;
	}

	/**
	 * Sets the auctionID of the bid
	 * @param auctionID
	 */
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * Get a short description of the bid that is suitable for use in a ListView.
	 * @return A short description of the bid.
	 */
	public String getDescriptionForList() {
		return "BidderID " + bidderID + " - AuctionID " + auctionID + " - amount: " + amount;
	}
	
}
