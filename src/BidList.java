package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class generates ArrayLists of Bid objects.
 * @author Joshua Blackman
 *
 */
public class BidList {

	private ArrayList<Bid> bidList;

	
	/**
	 * This constructor just intializes the database object.
	 */
	public BidList() {
		
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param userId id of user used to get all corresponding bids
	 * @return an ArrayList of all bids specified user has made, ordered by timePlaced.
	 */
	public ArrayList<Bid> getUserBidList(int userId) {
		bidList = new ArrayList<>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId +
				"'  ORDER BY (`timePlaced`) DESC";
		populateArray(query, bidList);
		return bidList;
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param auctionID id of auction used to get all relevant bids
	 * @return an ArrayList of all highest bids made on a specified auction, ordered by timePlaced.
	 */
	public ArrayList<Bid> getAuctionBidList(int auctionID){
		bidList = new ArrayList<>();

		String query = "SELECT * from `bids` WHERE `auctionID` = '" + auctionID +
				"'  ORDER BY (`timePlaced`) DESC";
		populateArray(query, bidList);

		return bidList;
	}

	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 *
	 * @param userId id of user used to get all currently-highest bids that user still holds
	 * @return an ArrayList of all bids specified user has made
	 */
	public ArrayList<Bid> getUserHighestBids(int userId) {
		bidList = new ArrayList<>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId + "'" +
				"GROUP BY `bidderID` HAVING MAX(`amount`) ORDER BY (`amount`)";
		populateArray(query, bidList);

		return bidList;
	}

	/**
	 *
	 * @param query SQL db-search in standard format
	 * @param bidList list of bids for query to search through
	 * @return list of bids for other bid-retrieval methods through SQL queries
	 */
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
