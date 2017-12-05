package src.Controllers;



import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import src.Auction;
import src.CONSTANTS;
import src.User;

/**
 * @author Borislav Koynin
 *
 */
public class ViewAuctionController {
	
	@FXML private Button placeBidButton;
	@FXML private TextField bidAmount;
	@FXML private Label artworkNameLabel;
	
	
	Auction auctionViewed;
	User userLoggedIn;
	
	/**
	 * Set the user that is being edited.
	 * When this window is closed, the changes will be set in this user object.
	 * @param The user to be edited.
	 */
	public void initialize(User userLoggedIn, Auction auctionToBeViewed) {
		// Keep a reference to the user that we are editing.
		this.auctionViewed = auctionToBeViewed;

		// Update the GUI to show the existing data.
		artworkNameLabel.setText(Integer.toString(auctionToBeViewed.getAuctionID()));
		
		
//		InputStream stream = getClass().getResourceAsStream(userBeingEdited.getDefaultAvatar());
//		Image newImage = new Image(stream);
//		profileImage.setImage(newImage);
	}
	
	public void placeBidButtonClicked() {
		String amount = bidAmount.getText();
		if(amount.isEmpty()) {
			CONSTANTS.makeAlertWindow("warning", "Please fill field amount");
		} else if(!isNumeric(amount)) {
			CONSTANTS.makeAlertWindow("warning", "Please fill field amount with numbers");
		}
	}
	
	private boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
	

}
