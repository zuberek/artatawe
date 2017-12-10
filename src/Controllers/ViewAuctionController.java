package src.Controllers;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Auction;
import src.Bid;
import src.BidList;
import src.CONSTANTS;
import src.User;

/**
 * @author Borislav Koynin
 * @author Joshua Blackman
 *
 */
public class ViewAuctionController {
	

	@FXML VBox rootPane;
	@FXML ImageView auctionImage;
	@FXML Label auctionNameLabel;
	@FXML TextArea auctionDescriptionTextBox;
	@FXML Label currentBidLabel;
	@FXML Label reservePrice;
	@FXML Label bidsPlacedLabel;
	@FXML TextField bidAmount;
	@FXML Button viewSellerProfileButton;
	
	// make functionality for them:
	@FXML Label authorLabel;
	@FXML Label yearLabel;
	@FXML TextField heightTextField;
	@FXML TextField widthTextField;
	@FXML Label depthLabel;
	@FXML TextField depthTextField;
	@FXML Button submitBidButton;
	
	public void viewImages() {
		
	}
	
	
	Auction auction;
	User currentUser;
	
	/**
	 * Set the user that is being edited.
	 * When this window is closed, the changes will be set in this user object.
	 * @param currentUser The user to be edited.
	 * @param auction The auction to be initialised
	 */
	public void initialize(User currentUser, Auction auction) {
		this.currentUser = currentUser;
		this.auction = new Auction(auction.getAuctionID());
		
		if(this.currentUser.getUserID() == this.auction.getSellerID()) {
			viewSellerProfileButton.setText("View bid history");
			bidAmount.setVisible(false);
			submitBidButton.setVisible(false);
		} else {
			viewSellerProfileButton.setText("View seller profile");
		}
		
		
		
		double[] dimensions = auction.getArtwork().getDimensions();
		//System.out.println(dimensions[0]);
		//System.out.println(dimensions[1]);
		heightTextField.setText(String.valueOf(dimensions[0]));
		widthTextField.setText(String.valueOf(dimensions[1]));
		if(dimensions[2] != 0) {
			depthTextField.setVisible(true);
			depthTextField.setText(String.valueOf(dimensions[2]));
			depthLabel.setVisible(true);
		} else {
			depthTextField.setVisible(false);
			depthLabel.setVisible(false);
		}
		
		authorLabel.setText(auction.getArtwork().getArtist());
		yearLabel.setText(auction.getArtwork().getDateCreated());
		auctionNameLabel.setText(this.auction.getArtwork().getTitle());
		auctionDescriptionTextBox.setText(this.auction.getArtwork().getDescription());
		reservePrice.setText('£'+String.valueOf(this.auction.getReservePrice()));
		bidsPlacedLabel.setText(this.auction.getCurrentBids(this.auction.getAuctionID()) + "/" + this.auction.getMaxBids());
		InputStream stream = getClass().getResourceAsStream(this.auction.getArtwork().getPhotographPath());
		Image newImage = new Image(stream);
		auctionImage.setImage(newImage);
		
		
		Bid currentBid = new Bid(this.auction.getLastBidID());
		currentBidLabel.setText('£'+String.valueOf(currentBid.getAmount()));
	}
	

	public void viewSellerProfile() {
		if(this.currentUser.getUserID() == this.auction.getSellerID()) {
			ArrayList<Bid> bidList = new ArrayList<>();
			BidList bl = new BidList();
			bidList = bl.getAuctionBidList(this.auction.getAuctionID());
			
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewBidHistory.fxml"));
				Parent editRoot = (Parent) fxmlLoader.load();
		
				ViewBidHistoryController ctrl = fxmlLoader.getController();
				ctrl.initialize(bidList);
		
				Scene newScene = new Scene(editRoot);
	            Stage stage = new Stage();
	            stage.setScene(newScene);
	            stage.setTitle("Artatawe |  Your Bid History");

	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
	        	
	        	User userToView = new User(auction.getSellerID());
	        	
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewProfile.fxml"));
	            Parent editRoot = (Parent) fxmlLoader.load();

	            ViewProfileController vcp = fxmlLoader.getController();
	            vcp.initialize(userToView, currentUser);

	            Scene newScene = new Scene(editRoot);
	            Stage editStagee = new Stage();
	            editStagee.setScene(newScene);
	            editStagee.setTitle("Artatawe | View Profile");

	            editStagee.initModality(Modality.APPLICATION_MODAL);

	            editStagee.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Quit the program (with an error code)
	            System.exit(-1);
	        }
		}
        

	}

	public void submitBid() {
		float bid;

		if((!CONSTANTS.isNumeric(bidAmount.getText()) || bidAmount.getText().isEmpty())) {
			CONSTANTS.makeAlertWindow("warning", "Please enter a numerical value");
		} else {
			bid = Float.parseFloat(bidAmount.getText());
			if(auction.getMaxBids() >= auction.getCurrentBids(auction.getAuctionID())) {
				if(bid < auction.getReservePrice()) {
					CONSTANTS.makeAlertWindow("warning", "Please enter a bid above the reserve price");
				} else if(bid <= new Bid(auction.getLastBidID()).getAmount()) {
					CONSTANTS.makeAlertWindow("warning", "Please enter a bid higher than the previous bid");
				} else if(currentUser.getUserName().equals(new User(auction.getSellerID()).getUserName())){
					CONSTANTS.makeAlertWindow("warning", "You can not bid on your own auction.");		
					// this works, dont touch it, or youll get shot up
				} else if(currentUser.getUserName().equals(new User(new Bid(auction.getLastBidID()).getBidderID()).getUserName())) {
					CONSTANTS.makeAlertWindow("warning", "You are already the highest bidder!");
				} else {
					Bid newBid = new Bid(currentUser.getUserID(), auction.getAuctionID(), bid);
					auction.setLastBidID(newBid.getBidID());
					
					auction = new Auction(auction.getAuctionID());
					auctionNameLabel.setText(auction.getArtwork().getTitle());
					auctionDescriptionTextBox.setText(auction.getArtwork().getDescription());
					bidsPlacedLabel.setText(auction.getCurrentBids(auction.getAuctionID()) + "/" + auction.getMaxBids());
					reservePrice.setText('£'+ String.valueOf(auction.getReservePrice()));
					InputStream stream = getClass().getResourceAsStream(auction.getArtwork().getPhotographPath());
					Image newImage = new Image(stream);
					auctionImage.setImage(newImage);
					
					
					
					Bid currentBid = new Bid(auction.getLastBidID());
					currentBidLabel.setText('£'+String.valueOf(currentBid.getAmount()));
					if(auction.getMaxBids() == auction.getCurrentBids(auction.getAuctionID())){
						auction.finishAuction();
						CONSTANTS.makeAlertWindow("success", "You have won the auction");
					} else {
						CONSTANTS.makeAlertWindow("success", "You have placed a bid.");
					}
				}
			} else {
				CONSTANTS.makeAlertWindow("warning", "Sorry! Someone else has won this auction.");
			}
		}
		
	}
	
	public void close() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}

}
