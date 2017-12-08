package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.Auction;
import src.AuctionList;
import src.User;

public class ViewSoldHistoryControllers {

	@FXML AnchorPane rootPane;
	@FXML ListView soldListView;
	
	User currentUser;
	
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		AuctionList al = new AuctionList();
		ArrayList<Auction> auctions = al.getUserSellingAuctionList(currentUser.getUserID());
		for(Auction a:auctions){
			soldListView.getItems().add(a.toString());
		}
	}
	
	public void closeButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
