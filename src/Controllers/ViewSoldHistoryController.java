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
 * CLass for viewing past sold artwork history
 * @author Joshua Blackman
 */
public class ViewSoldHistoryController {

	@FXML AnchorPane rootPane;
	@FXML ListView<String> soldListView;

	private User currentUser;

	/**
	 * Initialise list of sold artworks by auction list
	 * @param currentUser Current logged-in user
	 */
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		ArrayList<Auction> auctions = AuctionList.getUserSellingAuctionList(currentUser.getUserID());
		for(Auction a:auctions){
			soldListView.getItems().add(a.getDescriptionForList());
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
