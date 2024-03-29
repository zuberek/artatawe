package src;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

/**
 * @author Borislav Koynin 
 * @author Jan Dabrowski
 *
 */
public class Auction {
	private Artwork artwork;
	private int auctionID;
	private int sellerID;
	private int maxBids;
	private double reservePrice;
	private Date timeAdded;
	private int lastBidID;
	private String description;


	/**
	 * Constructor is used when you want to create the auction
	 * @param sellerID
	 * @param artworkID
	 * @param maxBids
	 * @param reservePrice
	 */
	public Auction(int sellerID, int artworkID, int maxBids, double reservePrice){
		Artwork artwork = null;
		if(Artwork.checkType(artworkID).equalsIgnoreCase("painting")) {
			artwork = new Painting(artworkID);
		} else  {
			artwork = new Sculpture(artworkID);
		}
		setSellerID(sellerID);
		setMaxBids(maxBids);
		setReservePrice(reservePrice);
		setLastBidID(-1);
		setArtwork(artwork);
		saveAuction();
	}
	/**
	 * This constructor is used when you want to retrieve the information about a auction.
	 * @param auctionID the bidID of the bid you want to retrieve.
	 */
	public Auction(int auctionID) {
		
		try {
			ResultSet rs = DB.select("SELECT * FROM `auctions` WHERE auctionID = '" + auctionID + "'");
			int artworkID = 0;
			while (rs.next()) {
				setAuctionID(rs.getInt("auctionID"));
				setSellerID(rs.getInt("sellerID"));
				setMaxBids(rs.getInt("maxBids"));
				setReservePrice(rs.getDouble("reservePrice"));
				setLastBidID(rs.getInt("lastBidID"));
				//setTimeAdded(rs.getDate(("timeAdded")));	
				artworkID = rs.getInt("artworkID");
				
			}

			Artwork artwork = null;
			if(Artwork.checkType(artworkID).equalsIgnoreCase("painting")) {
				artwork = new Painting(artworkID);
			} else  {
				artwork = new Sculpture(artworkID);
			}
			setArtwork(artwork);
		} catch(SQLException ex) {
			ex.getMessage();
		}
		
	}

	/**
	 * Finish the auction
	 */
	public void finishAuction(){
		DB.query("UPDATE `auctions` SET `active` = '0' WHERE `auctionID` = '" + this.getAuctionID() + "'");
	}
	
	/**
	 * Retrieve count of all bids currently placed
	 * @param auctionID id of the auction to retrieve
	 * @return the amount of bids placed on given auction
	 */
	public int getCurrentBids(int auctionID) {
		int counter = 0;
		try {
			
			ResultSet rs = DB.select("SELECT * FROM `bids` WHERE auctionID = '" + auctionID + "'");
			while (rs.next()) {
				counter++;
			}
		} catch(SQLException ex) {
			ex.getMessage();
		}
		return counter;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * Saves the current bid object into the database
	 */
	private void saveAuction(){
		// Insert user into database
		DB.query("INSERT INTO `auctions` (`sellerID`, `artworkID`, `maxBids`, `reservePrice`, `lastBidID`, `active`) VALUES (" + this.getSellerID() + ", " + this.getArtwork().getArtworkID() + ", " + this.getMaxBids() + ", " + this.getReservePrice() + ", " + this.getLastBidID() + ", 1); ");
	}

	/**
	 * Updates the auction after a bid has been made
	 */
	public void saveAuctionAfterBidding(){
		// Insert user into database
		String query = "UPDATE `auctions` SET `lastBidID` = '" + this.getLastBidID() + "' WHERE `auctionID` = " + this.getAuctionID();
		DB.query(query);
	}

	/**
	 * Retrieve highest bid amount
	 * @return an double of the last bid amount
	 */
	public float getAuctionLastBidAmount(){
		Bid lastAuctionBid = new Bid(this.getLastBidID());
		return lastAuctionBid.getAmount();
	}

	/**
	 * Get a short description of the auction that is suitable for use in a ListView.
	 * @return A short description of the auction.
	 */
	public String getDescriptionForList() {
		return "Title: " + this.getArtwork().getTitle() + " | Description: " + this.getArtwork().getDescription();
	}

	public Artwork getArtwork() {
		return this.artwork;
	}
	
	/**
	 * Return the auction's ID
	 * @return the auction ID
	 */
	public int getAuctionID() {
		return this.auctionID;
	}
	
	/**
	 * Set the auction ID
	 * @param auctionID the auction ID to set
	 */
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * Return the seller's userID
	 * @return the sellerID
	 */
	public int getSellerID() {
		return sellerID;
	}

	/**
	 * Set the seller ID
	 * @param sellerID the sellerID to set
	 */
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	/**
	 * Return the maximum number of bids allowed
	 * @return the maxBids
	 */
	public int getMaxBids() {
		return maxBids;
	}

	/**
	 * Set the maximum bid count
	 * @param maxBids the maxBids to set
	 */
	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	/**
	 * Retrieve the reserve price amount
	 * @return the reservePrice
	 */
	public double getReservePrice() {
		return reservePrice;
	}

	/**
	 * Set the reserve price
	 * @param reservePrice the reservePrice to set
	 */
	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	/**
	 * Retrieve the time the auction started
	 * @return the timeAdded
	 */
	public Date getTimeAdded() {
		return timeAdded;
	}

	/**
	 * Set the added time
	 * @param timeAdded the timeAdded to set
	 */
	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

	/**
	 * Retrieve the ID of the last bid to be placed on the auction
	 * @return the lastBidID
	 */
	public int getLastBidID() {
		return lastBidID;
	}

	/**
	 * Sets the ID for the last bid
	 * @param lastBidID the lastBidID to set
	 */
	public void setLastBidID(int lastBidID) {
		this.lastBidID = lastBidID;
	}	
	
	/**
	 * Set the artwork being sold
	 * @param artwork the artwork to be sold
	 */
	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}


}
