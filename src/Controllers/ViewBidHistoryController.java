package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Bid;

public class ViewBidHistoryController {
	@FXML AnchorPane rootPane;
	@FXML ListView bidHistoryList;
	
	public void initialize(ArrayList<Bid> bids){
		for(Bid bid: bids){
			bidHistoryList.getItems().add(bid.toString());
		}
	}
	
	public void closeButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
