package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Borislav Koynin and Jan Dabrowski
 *
 */
public class Auction {
	private Artwork artwork;
	private int auctionID;
	private int sellerID;
	private int maxBids;
	private double reservePrice;
	private int timeAdded;
	private int lastBidID;

	/**
	 * This constructor is used for when you want to create a new bid
	 */
	public Auction(int sellerID, int maxBids,
			double reservePrice, int timeAdded, int lastBidID){
		setSellerID(sellerID);
		setMaxBids(maxBids);
		setReservePrice(reservePrice);
		setTimeAdded(timeAdded);
		setLastBidID(lastBidID);
		saveAuction();
	}
	/**
	 * This constructor is used when you want to retrieve the information about a auction.
	 * @param auctionID the bidID of the bid you want to retrieve.
	 */
	public Auction(int auctionID) {
		try {
			ResultSet rs = DB.select("SELECT * FROM `auctions` WHERE auctionID = '" + auctionID + "'");
			while (rs.next()) {
				setAuctionID(rs.getInt("auctionID"));
				setSellerID(rs.getInt("sellerID"));
				setMaxBids(rs.getInt("maxBids"));
				setReservePrice(rs.getDouble("reservePrice"));
				setTimeAdded(rs.getInt("timeAdded"));
				setLastBidID(rs.getInt("lastBidID"));
			}
		} catch(SQLException ex) {
			ex.getMessage();
		}
	}

	
	private void saveAuction(){
		// Insert user into database
		DB.query("INSERT INTO `auctions` (`sellerID`, `maxBids`, `reservePrice`, `timeAdded`, `lastBidID`) VALUES (" + this.getSellerID() + ", " + this.getMaxBids() + ", " + this.getReservePrice() + ", " + this.getTimeAdded() + ", " + this.getLastBidID() +  "); ");
	}

	private void saveAuctionAfterBidding(){
		// Insert user into database
		String query = "UPDATE `auctions` SET `lastBidID` = '" + this.getLastBidID() + "' WHERE `auctionID` = " + this.getAuctionID();
		//System.out.println(query);
		DB.query(query);
	}

	public float getAuctionLastBidAmount(){
		Bid lastAuctionBid = new Bid(this.getLastBidID());
		return lastAuctionBid.getAmount();
	}

	/**
	 * Get a short description of the auction that is suitable for use in a ListView.
	 * @return A short description of the auction.
	 */
	public String getDescriptionForList() {
		return auctionID + " lastBid: " + lastBidID + " - " + new Bid(lastBidID).getAmount();
	}

	/**
	 * @return the auction ID of the current auction object
	 */
	public int getAuctionID() {
		return this.auctionID;
	}
	
	/**
	 * @param auctionID the auctionID to set
	 */
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * @return the sellerID
	 */
	public int getSellerID() {
		return sellerID;
	}

	/**
	 * @param sellerID the sellerID to set
	 */
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	/**
	 * @return the maxBids
	 */
	public int getMaxBids() {
		return maxBids;
	}

	/**
	 * @param maxBids the maxBids to set
	 */
	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	/**
	 * @return the reservePrice
	 */
	public double getReservePrice() {
		return reservePrice;
	}

	/**
	 * @param reservePrice the reservePrice to set
	 */
	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	/**
	 * @return the timeAdded
	 */
	public int getTimeAdded() {
		return timeAdded;
	}

	/**
	 * @param timeAdded the timeAdded to set
	 */
	public void setTimeAdded(int timeAdded) {
		this.timeAdded = timeAdded;
	}

	/**
	 * @return the lastBidID
	 */
	public int getLastBidID() {
		return lastBidID;
	}

	/**
	 * Sets a new ID for the last bid saves it to the database
	 * @param lastBidID the lastBidID to set
	 */
	public void setLastBidID(int lastBidID) {
		this.lastBidID = lastBidID;
		saveAuctionAfterBidding();
	}	

}
