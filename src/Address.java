package src;

/**
 * @author Joshua Blackman
 *
 */
public class Address {
	private String addressLine;
	private String town;
	private String county;
	private String postCode;
	
	public Address(String addressLine, String town, String county, String postCode) {
		setAddressLine(addressLine);
		setTown(town);
		setCounty(county);
		setPostCode(postCode);
	}
	
	/**
	 * @return the addressLine
	 */
	public String getAddressLine() {
		return addressLine;
	}
	/**
	 * @param addressLine the addressLine to set
	 */
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
