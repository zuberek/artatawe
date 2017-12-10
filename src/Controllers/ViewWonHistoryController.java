package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Auction;
import src.AuctionList;
import src.User;

public class ViewWonHistoryController {
	@FXML AnchorPane rootPane;
	@FXML ListView<String> wonListView;
	
	User currentUser;
	
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		ArrayList<Auction> auctions = AuctionList.getUserWonAuctionList(currentUser.getUserID());
		for(Auction a:auctions){
			wonListView.getItems().add(a.getDescriptionForList());
		}
	}
	
	public void closeButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
