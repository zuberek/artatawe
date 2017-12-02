package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Borislav Koynin
 *
 */
public class Auction {
	private DB db;
	//private Artwork artwork = new Artwork;
	private int auctionID;
	private int sellerID;
	private int maxBids;
	private double reservePrice;
	private int timeAdded;
	private int lastBidID;
	
	public Auction(int sellerID, int maxBids,
			double reservePrice, int timeAdded, int lastBidID){
		db = new DB();
		setSellerID(sellerID);
		setMaxBids(maxBids);
		setReservePrice(reservePrice);
		setTimeAdded(timeAdded);
		setLastBidID(lastBidID);
		saveAuction();
	}
	
	public Auction(int auctionID) {
		db = new DB();
		try {
			ResultSet rs = db.select("SELECT * FROM `auctions` WHERE auctionID = '" + auctionID + "'");
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
		String query = "INSERT INTO `auctions` (`sellerID`, `maxBids`, `reservePrice`, `timeAdded`, `lastBidID`) VALUES (" + this.getSellerID() + ", " + this.getMaxBids() + ", " + this.getReservePrice() + ", " + this.getTimeAdded() + ", " + this.getLastBidID() +  "); ";
		System.out.println(query);
		db.query(query);
	}

	/**
	 * Get a short description of the auction that is suitable for use in a ListView.
	 * @return A short description of the auction.
	 */
	public String getDescriptionForList() {
		return auctionID + " - " + sellerID + " - " + lastBidID + " million";
	}
	
	
	/**
	 * @return the auctionID
	 */
	public int getAuctionID() {
		return auctionID;
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
	 * @param lastBidID the lastBidID to set
	 */
	public void setLastBidID(int lastBidID) {
		this.lastBidID = lastBidID;
	}	

}
