package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Auction;
import src.AuctionList;
import src.User;

/**
 * Class for viewing all won auctions
 * @author Joshua Blackman
 */
public class ViewWonHistoryController {
	@FXML AnchorPane rootPane;
	@FXML ListView<String> wonListView;
	
	private User currentUser;

	/**
	 * Initialise list of auctions won
	 * @param currentUser
	 */
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		ArrayList<Auction> auctions = AuctionList.getUserWonAuctionList(currentUser.getUserID());
		for(Auction a:auctions){
			wonListView.getItems().add(a.getDescriptionForList());
		}
	}

	/**
	 * Close the window
	 */
	public void closeButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
