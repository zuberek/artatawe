package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Borislav Koynin and Jan Dabrowski
 *
 */
public class AuctionList {
	//private Artwork artwork = new Artwork;
	private int maxBids;
	private double reservePrice;
	private int auctionID;
	private int sellerID;
	private int unixTimeAdded;

	ArrayList<Auction> auctionList;

	/**
	 * Initialise the connection
	 */
	public AuctionList(){
		
	}

	/**
	 * Returns an ArrayList of Auction objects that then can be used to populate a listview.
	 *
	 * @param userId the user who created the auctions
	 * @return an ArrayList of all auctions specified user has created
	 */
	public ArrayList<Auction> getUserSellingAuctionList(int userId) {
		auctionList = new ArrayList<Auction>();

		String query = "SELECT * from `auctions` WHERE `sellerID` = '" + userId + "'";
		populateArray(query, auctionList);

		return auctionList;
	}

	/**
	 * Returns an ArrayList of Auction objects that then can be used to populate a listview.
	 *
	 * @param userId the user who is searched
	 * @return an ArrayList of all auctions that specified user is involved in
	 */
	public ArrayList<Auction> getUserBuyingAuctionList(int userId) {
		auctionList = new ArrayList<Auction>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId + "'" +
				"GROUP BY `auctionID` HAVING MAX(`amount`) ORDER BY (`amount`)";
		populateArray(query, auctionList);

		return auctionList;
	}

	private ArrayList<Auction> populateArray(String query, ArrayList<Auction> auctionList) {
		try {
			ResultSet rs = DB.select(query);
			while (rs.next()) {
				Auction auction = new Auction(rs.getInt("auctionID"));
				auctionList.add(auction);
			}
		} catch (SQLException ex) {
			ex.getMessage();
		}
		return auctionList;
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
	 * @return the unixTimeAdded
	 */
	public int getUnixTimeAdded() {
		return unixTimeAdded;
	}
	/**
	 * @param unixTimeAdded the unixTimeAdded to set
	 */
	public void setUnixTimeAdded(int unixTimeAdded) {
		this.unixTimeAdded = unixTimeAdded;
	}
	/**
	 * @return the lastBidId
	 */
	public int getLastBidId() {
		return lastBidId;
	}
	/**
	 * @param lastBidId the lastBidId to set
	 */
	public void setLastBidId(int lastBidId) {
		this.lastBidId = lastBidId;
	}
	private int lastBidId;

}