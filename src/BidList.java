package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class generates ArrayLists of src.Bid objects.
 * @author Joshua Blackman
 *
 */
public class BidList {

	private ArrayList<Bid> bidList;
	private DB db;
	
	/**
	 * This constructor just intializes the database object.
	 */
	public BidList() {
		db = new DB();
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param userId the unique userID being used to search for specific bids made by the corresponding user
	 * @return an ArrayList of all bids specified user has made
	 */
	public ArrayList<Bid> getUserBidList(int userId) {
		bidList = new ArrayList<>();
		try {
			ResultSet rs = db.select("SELECT * from `bids` WHERE `bidderID` = '" + userId + "'");
			while (rs.next()) {
				Bid bid = new Bid(rs.getInt("bidID"));
				bidList.add(bid);
	        }
		} catch (SQLException ex) {
			ex.getMessage();
		}
		db.closeQuietly();
		return bidList;
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param auctionID the unique id of the auction, used to search through the corresponding auction for certain bids
	 * @return an ArrayList of all bids made on a specified auction.
	 */
	public ArrayList<Bid> getAuctionBidList(int auctionID){
		bidList = new ArrayList<>();
		try {
			ResultSet rs = db.select("SELECT * from `bids` WHERE `auctionID` = '" + auctionID + "'");
			while (rs.next()) {
				Bid bid = new Bid(rs.getInt("bidID"));
				bidList.add(bid);
	        }
		} catch (SQLException ex) {
			ex.getMessage();
		}
		db.closeQuietly();
		return bidList;
	}
}

