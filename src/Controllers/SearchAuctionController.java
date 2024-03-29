package src.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.*;

/**
 * Controller used to search and filter-search all auctions.
 * @author Borislav Koynin
 * @author Jan Dabrowski
 */
public class SearchAuctionController {

	private User currentUser;
	private ArrayList<Auction> auctionsToDisplay  = new ArrayList<>();
	private int count;
	private final int AUCTIONS_PER_WINDOW = 6;

	private final ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("All","Painting", "Sculpture");

	private final ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
	private final ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

	private final IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

	private final int maxNumOfCheckBoxes = 1;

	@FXML Pane rootPane;

	@FXML ComboBox<String> artworkTypeComboBox;
	@FXML Label navigationLabel;

	@FXML private CheckBox contemporaryCheckBox;
	@FXML private CheckBox modernCheckBox;
	@FXML private CheckBox antiqueCheckBox;
	@FXML private Button submitButton;

	@FXML private TextField minTextField;
	@FXML private TextField maxTextField;

	@FXML private TextField bidsToFinishTextField;

	@FXML private Label auctionSearchLabel;

	@FXML Label artworkTitleLabel1;
	@FXML Label lastBidAmountLabel1;
	@FXML TextArea descriptionTextField1;
	@FXML ImageView artworkPhoto1;

	@FXML Label artworkTitleLabel2;
	@FXML Label lastBidAmountLabel2;
	@FXML TextArea descriptionTextField2;
	@FXML ImageView artworkPhoto2;

	@FXML Label artworkTitleLabel3;
	@FXML Label lastBidAmountLabel3;
	@FXML TextArea descriptionTextField3;
	@FXML ImageView artworkPhoto3;

	@FXML Label artworkTitleLabel4;
	@FXML Label lastBidAmountLabel4;
	@FXML TextArea descriptionTextField4;
	@FXML ImageView artworkPhoto4;

	@FXML Label artworkTitleLabel5;
	@FXML Label lastBidAmountLabel5;
	@FXML TextArea descriptionTextField5;
	@FXML ImageView artworkPhoto5;

	@FXML Label artworkTitleLabel6;
	@FXML Label lastBidAmountLabel6;
	@FXML TextArea descriptionTextField6;
	@FXML ImageView artworkPhoto6;

	@FXML HBox HBox1;
	@FXML HBox HBox2;
	@FXML HBox HBox3;
	@FXML HBox HBox4;
	@FXML HBox HBox5;
	@FXML HBox HBox6;

	/**
	 * Initialise and build the searchAuctions for the current user
	 * @param user Set current user in controller
	 */
	public void initialize(User user) {
		this.currentUser = user;

		//this part configures the search auction search tools
		artworkTypeComboBox.setValue("All");
		artworkTypeComboBox.setItems(artworkChoiceList);

		configureCheckBox(contemporaryCheckBox);
		configureCheckBox(modernCheckBox);
		configureCheckBox(antiqueCheckBox);

		submitButton.setDisable(true);

		numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
			if (newSelectedCount.intValue() >= maxNumOfCheckBoxes) {
				unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
				submitButton.setDisable(false);
			} else {
				unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
				submitButton.setDisable(true);
			}
		});

		//this part brings all the auctions to the screen
		auctionsToDisplay = AuctionList.getAuctions();
		auctionSearchLabel.setText("All artworks");
		this.updateNavigationLabel();
		count = 0;

		this.clearDisplayedAuctions();
		if(!auctionsToDisplay.isEmpty()){
			this.updateDisplayedAuctions();
		}

	}

	/**
	 * Checkbox handler for searching by creation year and art era
	 * @param checkBox Checkbox object from JavaFX
	 */
	private void configureCheckBox(CheckBox checkBox) {

		if (checkBox.isSelected()) {
			selectedCheckBoxes.add(checkBox);
		} else {
			unselectedCheckBoxes.add(checkBox);
		}

		checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
			if (isNowSelected) {
				unselectedCheckBoxes.remove(checkBox);
				selectedCheckBoxes.add(checkBox);
			} else {
				selectedCheckBoxes.remove(checkBox);
				unselectedCheckBoxes.add(checkBox);
			}

		});
	}

	/**
	 * Event handler for searching by how many bids remain on an auction.
	 */
	public void bidsToFinishButtonClicked(){
		int bidsToFinish = -1;

		if (CONSTANTS.isNumeric(bidsToFinishTextField.getText()) && Integer.parseInt(bidsToFinishTextField.getText()) >= 0){
			bidsToFinish = Integer.parseInt(bidsToFinishTextField.getText());
		}

		auctionSearchLabel.setText("Auctions with " + bidsToFinish + " bids to finish");
		auctionsToDisplay = AuctionList.getBidsToFinishAuction(bidsToFinish);
		bidsToFinishTextField.clear();
		this.refresh();
	}

	/**
	 * Event handler for searching by price range
	 */
	public void priceSearchButtonClicked(){
		int minPrice = -1;
		int maxPrice = -1;

		if (CONSTANTS.isNumeric(minTextField.getText()) && Integer.parseInt(minTextField.getText()) >= 0){
			minPrice = Integer.parseInt(minTextField.getText());
		}
		if (CONSTANTS.isNumeric(maxTextField.getText()) && Integer.parseInt(maxTextField.getText()) >= 0){
			maxPrice = Integer.parseInt(maxTextField.getText());
		}

		String minPriceText = minTextField.getText();
		String maxPriceText = maxTextField.getText();

		if (minPrice == -1) {
			minPriceText = "MIN";
		}
		if (maxPrice == -1) {
			maxPriceText = "MAX";
		}
		auctionSearchLabel.setText("Auctions within a range of: " + minPriceText + " to " + maxPriceText);
		minTextField.clear();
		maxTextField.clear();
		auctionsToDisplay = AuctionList.getPriceRangeAuctions(minPrice, maxPrice);
		this.refresh();
	}

	/**
	 * Event handler for submitting a search by art era/creation year
	 */
	public void submitButtonClicked(){
		if(contemporaryCheckBox.isSelected()){
			auctionsToDisplay = AuctionList.getContemporaryAuctions();
			contemporaryCheckBox.setSelected(false);
			auctionSearchLabel.setText("Contemporary artworks");
			this.refresh();
		} else if(modernCheckBox.isSelected()){
			auctionsToDisplay = AuctionList.getModernAuctions();
			modernCheckBox.setSelected(false);
			auctionSearchLabel.setText("Modern artworks");
			this.refresh();
		} else if(antiqueCheckBox.isSelected()){
			auctionsToDisplay = AuctionList.getAntiqueAuctions();
			antiqueCheckBox.setSelected(false);
			auctionSearchLabel.setText("Antique artworks");
			this.refresh();
		}
	}

	@FXML
	/**
	 * Dropdown box for searching by artwork type
	 */
	private void artworkTypeComboBox() {
		if(artworkTypeComboBox.getValue().equals("Painting")) {
			auctionsToDisplay = AuctionList.getPaintingAuctions();
			auctionSearchLabel.setText("All paintings");
			this.refresh();
		} else  if(artworkTypeComboBox.getValue().equals("Sculpture")){
			auctionsToDisplay = AuctionList.getSculptureAuctions();
			auctionSearchLabel.setText("All sculptures");
			this.refresh();
		} else  if(artworkTypeComboBox.getValue().equals("All")){
			auctionsToDisplay = AuctionList.getAuctions();
			auctionSearchLabel.setText("All artworks");
			this.refresh();
		}

	}

	/**
	 * Refresh auction list on screen
	 */
	private void refresh(){
		count = 0;
		this.clearDisplayedAuctions();
		this.updateNavigationLabel();
		if(!auctionsToDisplay.isEmpty()){
			this.updateDisplayedAuctions();
		}
	}

	/**
	 * Update all auctions displayed and check if extra display pages are needed
	 */
	private void updateDisplayedAuctions() {
		String[] input = this.getInfo(count);
		artworkTitleLabel1.setText(input[0]);
		lastBidAmountLabel1.setText(input[1]);
		descriptionTextField1.setText(input[2]);
		artworkPhoto1.setImage(this.getImage(count));
		HBox1.setVisible(true);

		if (checkIfMoreAndIncrease()) {
			input = this.getInfo(count);
			artworkTitleLabel2.setText(input[0]);
			lastBidAmountLabel2.setText(input[1]);
			descriptionTextField2.setText(input[2]);
			artworkPhoto2.setImage(this.getImage(count));
			HBox2.setVisible(true);

			if (checkIfMoreAndIncrease()) {
				input = this.getInfo(count);
				artworkTitleLabel3.setText(input[0]);
				lastBidAmountLabel3.setText(input[1]);
				descriptionTextField3.setText(input[2]);
				artworkPhoto3.setImage(this.getImage(count));
				HBox3.setVisible(true);

				if (checkIfMoreAndIncrease()) {
					input = this.getInfo(count);
					artworkTitleLabel4.setText(input[0]);
					lastBidAmountLabel4.setText(input[1]);
					descriptionTextField4.setText(input[2]);
					artworkPhoto4.setImage(this.getImage(count));
					HBox4.setVisible(true);

					if (checkIfMoreAndIncrease()) {
						input = this.getInfo(count);
						artworkTitleLabel5.setText(input[0]);
						lastBidAmountLabel5.setText(input[1]);
						descriptionTextField5.setText(input[2]);
						artworkPhoto5.setImage(this.getImage(count));
						HBox5.setVisible(true);


						if (checkIfMoreAndIncrease()) {
							input = this.getInfo(count);
							artworkTitleLabel6.setText(input[0]);
							lastBidAmountLabel6.setText(input[1]);
							descriptionTextField6.setText(input[2]);
							artworkPhoto6.setImage(this.getImage(count));
							HBox6.setVisible(true);
						}
					}
				}
			}
		}
	}

	/**
	 * Checks if there are more auctions to display when at the end of the page
	 * @return boolean depending on if there are more pages needed
	 */
	private boolean checkIfMoreAndIncrease(){
		boolean result = false;
		if(count < auctionsToDisplay.size() - 1){
			result = true;
			count++;
		}
		return result;
	}

	/**
	 * Event handler on next page button click
	 */
	public void nextPageButtonClicked(){
		if(checkIfMoreAndIncrease()){
			this.clearDisplayedAuctions();
			this.updateNavigationLabel();
			this.updateDisplayedAuctions();
		}
	}

	/**
	 * Event handler on previous page button click
	 */
	public void backPageButtonClicked(){
		if((count/AUCTIONS_PER_WINDOW-1) * AUCTIONS_PER_WINDOW >= 0){
			count = (count/AUCTIONS_PER_WINDOW - 1) * AUCTIONS_PER_WINDOW;
			this.clearDisplayedAuctions();
			this.updateNavigationLabel();
			this.updateDisplayedAuctions();
		}
	}

	/**
	 * Clear all displayed auctions
	 */
	private void clearDisplayedAuctions(){
		HBox1.setVisible(false);
		HBox2.setVisible(false);
		HBox3.setVisible(false);
		HBox4.setVisible(false);
		HBox5.setVisible(false);
		HBox6.setVisible(false);
	}

	/**
	 * Return basic information about an auction
	 * @param count Index of auction clicked to display
	 * @return Array of basic artwork information
	 */
	private String[] getInfo(int count){
		Auction displayedAuction = auctionsToDisplay.get(count);
		Artwork displayedArtwork = displayedAuction.getArtwork();

		String[] result = new String[3];

		result[0] = displayedArtwork.getTitle();
		result[1] = "\u00A3" + String.valueOf(displayedAuction.getAuctionLastBidAmount());
		result[2] = displayedArtwork.getDescription();

		return result;
	}

	/**
	 * Retrieve image for artwork
	 * @param count Index of displayed auction
	 * @return Image stream of artwork image
	 */
	private Image getImage(int count){
		InputStream stream = getClass().getResourceAsStream(auctionsToDisplay.get(count).getArtwork().getPhotographPath());
		return new Image(stream);
	}

	/**
	 * Updates navigation labels to change auction display
	 */
	private void updateNavigationLabel(){
		int size = auctionsToDisplay.size();

		int auctionsOnScreen = count + AUCTIONS_PER_WINDOW;
		if(size > auctionsOnScreen){
			auctionsOnScreen = AUCTIONS_PER_WINDOW;
		} else {
			auctionsOnScreen = size;
		}

		//fake meaning the real index of those objects is one less
		int fakeCount = count+1;
		navigationLabel.setText(fakeCount + " - " + auctionsOnScreen + " of " + size);
	}

	/**
	 * Event handler to return to dashboard page
	 */
	public void goBackButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	/**
	 * Event handler for clicking auction 1
	 */
	public void mouseClickHBox0Handler(){	
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}
	/**
	 * Event handler for clicking auction 2
	 */
	public void mouseClickHBox1Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index + 1);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}
	/**
	 * Event handler for clicking auction 3
	 */
	public void mouseClickHBox2Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index + 2);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}
	/**
	 * Event handler for clicking auction 4
	 */
	public void mouseClickHBox3Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index + 3);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}
	/**
	 * Event handler for clicking auction 5
	 */
	public void mouseClickHBox4Handler(){	
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index + 4);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}
	/**
	 * Event handler for clicking auction 6
	 */
	public void mouseClickHBox5Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clickedAuction = auctionsToDisplay.get(index + 5);
		this.bringViewAuctionSceneForAuction(clickedAuction);
	}

	/**
	 * Event handler to bring full auction display for a single auction, allowing
	 * interaction such as bidding and checking seller profile.
	 * @param auction auction to be viewed by the method
	 */
	private void bringViewAuctionSceneForAuction(Auction auction){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewAuction.fxml"));
			Parent editRoot = fxmlLoader.load();
	
			ViewAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser, auction);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | View Auction");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}