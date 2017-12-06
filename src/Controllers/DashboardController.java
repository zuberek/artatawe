package src.Controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import src.Auction;
import src.AuctionList;
import src.User;

public class DashboardController {
	
	@FXML Button editProfileButton;
	@FXML Button sellArtworkButton;
	@FXML Button bidHistoryButton;
	@FXML Button auctionHistoryButton;
	@FXML Button logoutButton;
	@FXML Label welcomeLabel;
	@FXML ListView<String> browseAuctions;
	
	User currentUser = new User();
	ArrayList<Auction> auctions;
	
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		welcomeLabel.setText("Welcome " + currentUser.getFirstName());
		
		for(Auction a : AuctionList.getAuctions()) {
			browseAuctions.getItems().add(String.valueOf(a.getAuctionID()));
		}
		
	}

	@FXML public void browseAuctionClicked(MouseEvent arg0) {
	    System.out.println("clicked on " + browseAuctions.getSelectionModel().getSelectedItem());
	}

}
