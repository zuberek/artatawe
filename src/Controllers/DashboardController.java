package src.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Auction;
import src.AuctionList;
import src.Login;
import src.User;

public class DashboardController {
	
	@FXML Button editProfileButton;
	@FXML Button sellArtworkButton;
	@FXML Button bidHistoryButton;
	@FXML Button auctionHistoryButton;
	@FXML Button logoutButton;
	@FXML Label welcomeLabel;
	@FXML ListView<String> browseAuctions;
	
	User currentUser;
	ArrayList<Auction> auctions;
	
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		welcomeLabel.setText("Welcome " + currentUser.getFirstName());
		
		for(Auction a : AuctionList.getAuctions()) {
			browseAuctions.getItems().add(String.valueOf(a.getAuctionID()));
		}
		
	}

	public void browseAuctionClicked(MouseEvent arg0) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewAuctionController ctrl = fxmlLoader.getController();
			Auction auction =  new Auction(Integer.parseInt(browseAuctions.getSelectionModel().getSelectedItem()));
			ctrl.initialize(currentUser, auction);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  View Auction");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("clicked on " + browseAuctions.getSelectionModel().getSelectedItem());
	}
	
	public void sellArtworkClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/AddAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			AddAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(this.currentUser);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | Add new Auction");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
