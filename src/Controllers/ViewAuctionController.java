package src.Controllers;



import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Auction;
import src.Bid;
import src.CONSTANTS;
import src.Login;
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
		auctionDescriptionTextBox.setText(auction.getDescription());
		
		Bid currentBid = new Bid(auction.getLastBidID());
		currentBidLabel.setText(String.valueOf(currentBid.getAmount()));
	}
	
	public void viewSellerProfile() {
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
