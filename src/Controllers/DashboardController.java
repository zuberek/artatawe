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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Artwork;
import src.Auction;
import src.AuctionList;
import src.Bid;
import src.BidList;
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
		
	}
	
	public void soldArtworkHistory(){
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewSoldHistory.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewSoldHistoryController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  Your Sold History");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void browseAuctionsClicked(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/SearchAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
			
			SearchAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser);
		
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | Auction Search");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public void bidHistoryClicked(){
		ArrayList<Bid> bidList = new ArrayList<>();
		BidList bl = new BidList();
		bidList = bl.getUserBidList(currentUser.getUserID());
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewBidHistory.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewBidHistoryController ctrl = fxmlLoader.getController();
			ctrl.initialize(bidList);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  Your Bid History");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void editProfileClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Profile.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            ProfileController profileController = fxmlLoader.getController();
            profileController.initialize(currentUser);

            Scene newScene = new Scene(editRoot);
            Stage editStagee = new Stage();
            editStagee.setScene(newScene);
            editStagee.setTitle("Artatawe | Edit User");

            editStagee.initModality(Modality.APPLICATION_MODAL);

            editStagee.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
	}
	
	public void logoutClicked() {
        try {
        	currentUser.saveUserLogout();
            Stage stage = (Stage)editProfileButton.getScene().getWindow();

            Login login = new Login();
            login.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
