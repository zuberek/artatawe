package src.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML Button editProfileButton;
    @FXML Label bidListLabel;
    @FXML TextField addAuctionTextField;

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
        refreshAuctionList();
        refreshBidList();
    }

    private void refreshBidList(){
        // Clear the displayed lists
        bidList.getItems().clear();

        //Get new lists from database
        BidList list = new BidList();
        bids = list.getUserBidList(currentUser.getUserID());

        // Add new elements to the displayed lists
        for (Bid b : bids) {
            bidList.getItems().add(b.getDescriptionForList());
        }
    }

    private void refreshBidListAfterAuctionSelection(int selectedAuction){
        // Clear the displayed lists
        bidList.getItems().clear();

        //Get new lists from database
        BidList list = new BidList();
        bids = list.getAuctionBidList(selectedAuction);

        // Add new elements to the displayed lists
        for (Bid b : bids) {
            bidList.getItems().add(b.getDescriptionForList());
        }
    }

    private void refreshAuctionList(){
        auctionList.getItems().clear();

        AuctionList auctionsData = new AuctionList();
        auctions = auctionsData.getUserBuyingAuctionList(currentUser.getUserID());

        for (Auction a : auctions) {
            auctionList.getItems().add(a.getDescriptionForList());
        }
    }

    public void viewAuctionButtonClicked(){
        String selectedRow = auctionList.getSelectionModel().getSelectedItems().toString();
        int selectedAuction = Integer.parseInt(selectedRow.substring(1,2));

        refreshBidListAfterAuctionSelection(selectedAuction);
        //Change the label above the list
        bidListLabel.setText("All bids in this auction, No: " + selectedAuction);
    }

    /**
     * Handles the place auction action, using the selected auction and input as an amount
     */
    public void handleBidButtonAction(){
        //TODO: make it more efficient, store amount of the last bid in auction table?
        Auction selected = getSelectedAuctionFromList(auctionList);
        float lastAuctionBidAmount = selected.getAuctionLastBidAmount();

        float amount = Float.valueOf(newBidAmountTextField.getText());

        if(amount <= 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Please place a valid bid");
            alert.showAndWait();
        } else if(amount <= lastAuctionBidAmount ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("You need place a higher bid!");
            alert.showAndWait();
        }else {
            Bid newBid = new Bid(currentUser.getUserID(), selected.getAuctionID(), amount);
            selected.setLastBidID(newBid.getBidID());
            refreshBidListAfterAuctionSelection(selected.getAuctionID());

        }
    }

    public void addAuctionButtonClicked(){
        Auction auction = new Auction(Integer.parseInt(addAuctionTextField.getText()));
        int auctionID = auction.getAuctionID();

        float amountToBid = auction.getAuctionLastBidAmount() + CONSTANTS.BID_INCREASE ;
        if(auctionID <= 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Please place a valid auction");
            alert.showAndWait();
        }else {
            Bid newBid = new Bid(currentUser.getUserID(), auctionID, amountToBid);
            auction.setLastBidID(newBid.getBidID());
            refreshLists();
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

    public void handleLogoutButtonClicked(){
        try {
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

    private Auction getSelectedAuctionFromList(ListView<String> auctionList){
        String selectedRow = auctionList.getSelectionModel().getSelectedItems().toString();
        int selectedAuction = Integer.parseInt(selectedRow.substring(1,2));
        Auction selected = new Auction(selectedAuction);
        return selected;
    }
}
