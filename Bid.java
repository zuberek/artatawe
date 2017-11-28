import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Joshua Blackman
 *
 */
public class Bid {
	
	private DB db;
	private int bidID;
	private int bidderID;
	private int auctionID;
	private float amount;
	private long timePlaced;
	
	/**
	 * @param bidID
	 * @param bidderID
	 * @param amount
	 * @param timePlaced
	 */
	public Bid(int bidderID, int auctionID, float amount, long timePlaced){
		db = new DB();
		setBidderID(bidderID);
		setAuctionID(auctionID);
		setAmount(amount);
		setTimePlaced(timePlaced);
		saveBid(this);
	}
	
	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * @param bidID
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
				setTimePlaced(rs.getInt("timePlaced"));

	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
	}
	
	/**
	 * @param bid
	 */
	public void saveBid(Bid bid){
		// Insert bid into database
		db.query("INSERT INTO `bids` (`bidderID`, `auctionID`, `amount`, `timePlaced`) VALUES (" + bid.getBidderID() + ", " + bid.getBidderID() + ", " + bid.getAmount() + ", " + bid.getBidderID() + "); ");
	}
	
	/**
	 * @return
	 */
	public int getBidID() {
		return bidID;
	}
	
	/**
	 * @param bidID
	 */
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}
	
	/**
	 * @return
	 */
	public int getBidderID() {
		return bidderID;
	}
	
	/**
	 * @param bidderID
	 */
	public void setBidderID(int bidderID) {
		this.bidderID = bidderID;
	}
	
	/**
	 * @return
	 */
	public float getAmount() {
		return amount;
	}
	
	/**
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	/**
	 * @return
	 */
	public long getTimePlaced() {
		return timePlaced;
	}
	
	/**
	 * @param timePlaced
	 */
	public void setTimePlaced(long timePlaced) {
		this.timePlaced = timePlaced;
	}
	
}
