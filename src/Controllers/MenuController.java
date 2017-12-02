package src.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.*;

import java.io.IOException;
import java.util.ArrayList;


public class MenuController {

    @FXML TextField currentUserTextField;
    @FXML ListView<String> bidList;
    @FXML ListView<String> auctionList;

    @FXML TextField newBidAmountTextField;

    User currentUser;

    // The main list that will store all auctions for this user.
    private ArrayList<Bid> bids;
    private ArrayList<Auction> auctions;

    public void initialize(User userToSet){
        setCurrentUser(userToSet);
        currentUserTextField.setText(currentUser.getUserName());
        refreshLists();
    }

    /**
     * Set the current user, will do some validity checking here
     * @param userToSet The user in the current session
     */
    public void setCurrentUser(User userToSet) {
        // Keep a reference to the user that we are editing.
        this.currentUser = userToSet;
    }

    /**
     * Refresh the displayed auctions.
     */
    private void refreshLists() {
        // Clear the displayed lists auctions auctionList
        bidList.getItems().clear();
        auctionList.getItems().clear();

        //Get new lists from database
        AuctionList auctionsData = new AuctionList();
        auctions = auctionsData.getUserAuctionList(currentUser.getUserID());
        BidList list = new BidList();
        bids = list.getUserBidList(currentUser.getUserID());

        // Add new elements to the displayed lists
        for (Bid b : bids) {
            bidList.getItems().add(b.getDescriptionForList());
        }
        for (Auction a : auctions) {
            auctionList.getItems().add(a.getDescriptionForList());
        }
    }

    /**
     * TODO: Places all the bids to the auction with ID = 1, implement some choosing in GUI, database locked problem
     */
    public void handleBidButtonAction(){
        int amount = Integer.parseInt(newBidAmountTextField.getText());
        if(amount <= 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Please place a valid bid");
            alert.showAndWait();
        }else {
            Bid newBid = new Bid(currentUser.getUserID(), 1, Integer.parseInt(newBidAmountTextField.getText()), 123);
        }
    }

    public void editProfileButonClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Profile.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            ProfileController profileController = fxmlLoader.getController();
            profileController.setUserForEditing(currentUser);

            Scene newScene = new Scene(editRoot, Login.MAIN_WINDOW_WIDTH, Login.MAIN_WINDOW_HEIGHT);
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
}
