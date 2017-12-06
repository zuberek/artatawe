package src.Controllers;



import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import src.Auction;
import src.Bid;
import src.CONSTANTS;
import src.User;

/**
 * @author Borislav Koynin
 *
 */
public class ViewAuctionController {
	
	@FXML Label auctionNameLabel;
	@FXML TextArea auctionDescriptionTextBox;
	@FXML Label currentBidLabel;
	
	
	Auction auction;
	User currentUser;
	
	/**
	 * Set the user that is being edited.
	 * When this window is closed, the changes will be set in this user object.
	 * @param The user to be edited.
	 */
	public void initialize(User currentUser, Auction auction) {
		this.currentUser = currentUser;
		this.auction = auction;
		
		auctionNameLabel.setText(auction.getArtwork().getTitle());
		auctionDescriptionTextBox.setText(auction.getDescriptionForList());
		
		Bid currentBid = new Bid(auction.getLastBidID());
		currentBidLabel.setText(String.valueOf(currentBid.getAmount()));
	}
	
	

}
