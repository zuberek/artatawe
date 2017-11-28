import java.util.ArrayList;

/**
 * This class generates ArrayLists of Bid objects.
 * @author Joshua Blackman
 *
 */
public class BidList {

	ArrayList<Bid> bidList;
	
	public BidList() {
		
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param userId
	 * @return an ArrayList of all bids specified user has made
	 */
	public ArrayList<Bid> getUserBidList(int userId) {
		return bidList;
	}
	
	/**
	 * Returns an ArrayList of Bid objects that then can be used to populate a listview.
	 * 
	 * @param auctionId
	 * @return an ArrayList of all bids made on a specified auction.
	 */
	public ArrayList<Bid> getAuctionBidList(int auctionId){
		return bidList;
	}
}

