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

	ArrayList<Bid> bidList;

	
	/**
	 * This constructor just intializes the database object.
	 */
	public BidList() {
		
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param userId
	 * @return an ArrayList of all bids specified user has made, ordered by timePlaced.
	 */
	public ArrayList<Bid> getUserBidList(int userId) {
		bidList = new ArrayList<Bid>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId + "'  ORDER BY (`timePlaced`) DESC";
		populateArray(query, bidList);
		return bidList;
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param auctionID
	 * @return an ArrayList of all highest bids made on a specified auction, ordered by timePlaced.
	 */
	public ArrayList<Bid> getAuctionBidList(int auctionID){
		bidList = new ArrayList<Bid>();

		String query = "SELECT * from `bids` WHERE `auctionID` = '" + auctionID + "'  ORDER BY (`timePlaced`) DESC";
		populateArray(query, bidList);

		return bidList;
	}

	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 *
	 * @param userId
	 * @return an ArrayList of all bids specified user has made
	 */
	public ArrayList<Bid> getUserHighestBids(int userId) {
		bidList = new ArrayList<Bid>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId + "'" +
				"GROUP BY `bidderID` HAVING MAX(`amount`) ORDER BY (`amount`)";
		populateArray(query, bidList);

		return bidList;
	}

	private ArrayList<Bid> populateArray(String query, ArrayList<Bid> bidList) {
		try {
			ResultSet rs = DB.select(query);
			while (rs.next()) {
					Bid bid = new Bid(rs.getInt("bidID"));
					bidList.add(bid);
			}
		} catch (SQLException ex) {
				ex.getMessage();
			}
		return bidList;
	}
}
