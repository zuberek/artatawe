
/**
 * @author Joshua Blackman
 *
 */
public class Bid {
	private int bidID;
	private int bidderID;
	private double amount;
	private long timePlaced;
	
	/**
	 * @param bidID
	 * @param bidderID
	 * @param amount
	 * @param timePlaced
	 */
	public Bid(int bidID, int bidderID, double amount, long timePlaced){
		setBidID(bidID);
		setBidderID(bidderID);
		setAmount(amount);
		setTimePlaced(timePlaced);
	}
	
	/**
	 * @param bidID
	 */
	public Bid(int bidID){
		
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
	public double getAmount() {
		return amount;
	}
	
	/**
	 * @param amount
	 */
	public void setAmount(double amount) {
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
