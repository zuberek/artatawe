package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Bid;

/**
 * Button for viewing history of bids, displayed in simple list
 */
public class ViewBidHistoryController {
	@FXML AnchorPane rootPane;
	@FXML ListView<String> bidHistoryList;

	/**
	 * Initialise a list of bids
	 * @param bids List of bids made by user
	 */
	public void initialize(ArrayList<Bid> bids){
		for(Bid bid: bids){
			bidHistoryList.getItems().add(bid.getDescriptionForList());
		}
	}

	/**
	 * Close window and return to dashboard
	 */
	public void closeButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
