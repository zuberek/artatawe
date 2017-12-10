package src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for storing a global list of Auctions and retrieving auctions for
 * specific users or lists of users.
 *
 * @author Borislav Koynin
 * @author Jan Dabrowski
 * @author Joshua Blackman
 */
public class AuctionList {
	private static ArrayList<Auction> auctionList;

	/**
	 * Initialise the connection
	 */
	public AuctionList(){

	}

	public static int getNewestArtworkID(){
		String query = "SELECT `artworkID` from `artworks` WHERE `artworkID` = " +
		"(SELECT MAX(artworkID) from `artworks`)";
		ResultSet rs = DB.select(query);
		int artworkID = 0;
		try {
			if (rs.isBeforeFirst()){
					artworkID = rs.getInt("artworkID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artworkID;
	}

	/**
	 * Returns an ArrayList of Auction objects that then can be used to populate a listview.
	 *
	 * @param userId the user who created the auctions
	 * @return an ArrayList of all auctions specified user has created
	 */
	public static ArrayList<Auction> getUserSellingAuctionList(int userId) {
		auctionList = new ArrayList<>();

		String query = "SELECT * from `auctions` WHERE `sellerID` = '" + userId + "' AND `active` = '1'";
		populateArray(query, auctionList);

		return auctionList;
	}
	
	public static ArrayList<Auction> getUserWonAuctionList(int userId) {
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM auctions INNER JOIN bids ON auctions.lastBidID = bids.bidID WHERE auctions.active = 0 AND bids.bidderID = " + userId +"";
		populateArray(query, auctionList);

		return auctionList;
	}


	public static ArrayList<Auction> getAuctions() {
		auctionList = new ArrayList<>();

		String query = "SELECT * from `auctions` WHERE active = '1'";
		populateArray(query, auctionList);

		return auctionList;
	}
	
	public static ArrayList<Auction> getPaintingAuctions(){
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM artworks INNER JOIN auctions ON artworks.artworkID = " +
				"auctions.artworkID where artworks.type = 'painting' AND active = '1'\n";
		populateArray(query, auctionList);

		return auctionList;
	}

	public static ArrayList<Auction> getSculptureAuctions(){
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM artworks INNER JOIN auctions ON artworks.artworkID = " +
				"auctions.artworkID where artworks.type = 'sculpture' AND active = '1'\n";
		populateArray(query, auctionList);

		return auctionList;
	}

	public static ArrayList<Auction> getContemporaryAuctions(){
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM artworks INNER JOIN auctions ON artworks.artworkID = " +
				"auctions.artworkID where artworks.dateCreated >= 1980 AND" +
				"artworks.dateCreated <=" + CONSTANTS.MAX_YEAR +" AND active = '1'\n";
		populateArray(query, auctionList);

		return auctionList;
	}

	public static ArrayList<Auction> getModernAuctions(){
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM artworks INNER JOIN auctions ON artworks.artworkID = " +
				"auctions.artworkID where artworks.dateCreated >= 1900 AND artworks.dateCreated < 1980 AND active = '1'\n";
		populateArray(query, auctionList);

		return auctionList;
	}

	public static ArrayList<Auction> getAntiqueAuctions(){
		auctionList = new ArrayList<>();

		String query = "SELECT * FROM artworks INNER JOIN auctions ON artworks.artworkID = " +
		"auctions.artworkID where artworks.dateCreated < 1900 AND active = '1'\n";
		populateArray(query, auctionList);

		return auctionList;
	}

	public static ArrayList<Auction> getBidsToFinishAuction(int bidsToFinish){
		auctionList = new ArrayList<>();
		String query;

		if (bidsToFinish >= 0){
			//return all the artworks that have that many bids to finish but haven't been bid on yet
			query = "SELECT * FROM auctions where auctions.maxBids < " + bidsToFinish +
					" and auctions.lastBidID = -1\n";
			populateArray(query,auctionList);
			query = "SELECT * FROM bids INNER JOIN auctions ON bids.auctionID = auctions.auctionID GROUP BY auctions.auctionID HAVING (auctions.maxBids - COUNT(bids.bidID) < " + bidsToFinish + ")\n";
			ArrayList<Auction> addAuctionList = new ArrayList<>();
			populateArray(query,addAuctionList);
			auctionList.addAll(addAuctionList);
		}
		return auctionList;
	}

	public static ArrayList<Auction> getPriceRangeAuctions(int rangeMin, int rangeMax){
		auctionList = new ArrayList<>();
		String query;
		if (rangeMax >= 0 && rangeMin >= 0){
			//this part returns all auctions that haven't been bid at yet but
			// have a reserved price within the range
			query = "SELECT * FROM auctions where auctions.reservePrice > " + rangeMin +
					" and auctions.reservePrice < " + rangeMax +
					" and auctions.lastBidID = -1\n";
			populateArray(query,auctionList);
			//this part returns the auctions that have been bid at and have
			// the last bid amount within the range
			query = "SELECT * FROM bids INNER JOIN auctions ON bids.bidID = " +
					"auctions.lastBidID AND bids.auctionID = auctions.auctionID " +
					"where bids.amount > " + rangeMin + " and bids.amount < " + rangeMax + "\n";
			ArrayList<Auction> addAuctionList = new ArrayList<>();
			populateArray(query,addAuctionList);
			auctionList.addAll(addAuctionList);
		} else if (rangeMax > 0 && rangeMin < 0){
			query = "SELECT * FROM auctions where auctions.reservePrice < " + rangeMax +
					" and auctions.lastBidID = -1\n";
			populateArray(query,auctionList);
			query = "SELECT * FROM bids INNER JOIN auctions ON bids.bidID = " +
					"auctions.lastBidID AND bids.auctionID = auctions.auctionID " +
					"where bids.amount < " + rangeMax + "\n";
			ArrayList<Auction> addAuctionList = new ArrayList<>();
			populateArray(query,addAuctionList);
			auctionList.addAll(addAuctionList);
		} else if (rangeMax < 0 && rangeMin >= 0){
			query = "SELECT * FROM auctions where auctions.reservePrice > " + rangeMin +
					" and auctions.lastBidID = -1\n";
			populateArray(query,auctionList);
			query = "SELECT * FROM bids INNER JOIN auctions ON bids.bidID = " +
					"auctions.lastBidID AND bids.auctionID = auctions.auctionID " +
					"where bids.amount > " + rangeMin + "\n";
			ArrayList<Auction> addAuctionList = new ArrayList<>();
			populateArray(query,addAuctionList);
			auctionList.addAll(addAuctionList);
		}

		return auctionList;
	}


	/**
	 * Returns an ArrayList of Auction objects that then can be used to populate a listview.
	 *
	 * @param userId the user who is searched
	 * @return an ArrayList of all auctions that specified user is involved in
	 */
	public static ArrayList<Auction> getUserBuyingAuctionList(int userId) {
		auctionList = new ArrayList<>();

		String query = "SELECT * from `bids` WHERE `bidderID` = '" + userId + "'" +
				"GROUP BY `auctionID` HAVING MAX(`amount`) ORDER BY (`amount`) AND active = '1'";
		populateArray(query, auctionList);

		return auctionList;
	}

	/**
	 * Retrieve all auctions of a user
	 * @param userList list of all users in database
	 * @return list of auctions created by user in lists
	 */
	public static ArrayList<Auction> getUsersAuctions(ArrayList<User> userList){
		auctionList = new ArrayList<>();
		if (!userList.isEmpty()) {
			String whereClause = "";
			for(int i = 0; i < userList.size();i++) {
				whereClause += " `sellerID` = '" + userList.get(i).getUserID() + "'";
				if (i == 0 && userList.size() > 1) {
					whereClause += " OR ";
				} else if (i < userList.size() - 1) {
					whereClause += " OR ";
				}
			}

			String query = "SELECT * from `auctions` WHERE" + whereClause + " AND active = '1' ORDER BY auctionID ASC";
			populateArray(query, auctionList);
		}

		return auctionList;
	}

	private static void populateArray(String query, ArrayList<Auction> auctionList) {
		try {
			ResultSet rs = DB.select(query);
				while (rs.next()) {
					Auction auction = new Auction(rs.getInt("auctionID"));
					auctionList.add(auction);
				}
		} catch (SQLException ex) {
			ex.getMessage();
		}
	}
}