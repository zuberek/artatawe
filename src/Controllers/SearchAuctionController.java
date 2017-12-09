/**
 * 
 */
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
import src.Artwork;
import src.Auction;
import src.AuctionList;
import src.User;

/**
 * @author Borislav Koynin
 * @author Jan Dabrowski
 *
 */
public class SearchAuctionController {

	User currentUser;
	private ArrayList<Auction> auctionsToDisplay  = new ArrayList<>();
	private int count;
	private final int AUCTIONS_PER_WINDOW = 6;

	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("All","Painting", "Sculpture");

	private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
	private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

	private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

	private final int maxNumSelected = 1;

	@FXML Pane rootPane;

	@FXML ComboBox<String> artworkTypeComboBox;
	@FXML Label navigationLabel;

	@FXML private CheckBox yearCheckBox1;
	@FXML private CheckBox yearCheckBox2;
	@FXML private CheckBox yearCheckBox3;
	@FXML private Button submitButton;

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

	public void initialize(User user) {
		this.currentUser = user;
		auctionsToDisplay = AuctionList.getAuctions();

		configureCheckBox(yearCheckBox1);
		configureCheckBox(yearCheckBox2);
		configureCheckBox(yearCheckBox3);

		submitButton.setDisable(true);

		numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
			if (newSelectedCount.intValue() >= maxNumSelected) {
				unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
				submitButton.setDisable(false);
			} else {
				unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
				submitButton.setDisable(true);
			}
		});

		this.updateNavigationLabel();
		count = 0;

		this.clearDisplayedAuctions();
		if(!auctionsToDisplay.isEmpty()){
			this.updateDisplayedAuctions();
		}
		artworkTypeComboBox.setValue("All");
		artworkTypeComboBox.setItems(artworkChoiceList);
	}

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

	private void refresh(){
		count = 0;
		this.clearDisplayedAuctions();
		this.updateNavigationLabel();
		if(!auctionsToDisplay.isEmpty()){
			this.updateDisplayedAuctions();
		}
	}

	private void updateDisplayedAuctions(){
		String[] input = this.getInfo(count);
		artworkTitleLabel1.setText(input[0]);
		lastBidAmountLabel1.setText(input[1]);
		descriptionTextField1.setText(input[2]);
		artworkPhoto1.setImage(this.getImage(count));
		HBox1.setVisible(true);

		if (checkIfMoreAndIncrease()){
			input = this.getInfo(count);
			artworkTitleLabel2.setText(input[0]);
			lastBidAmountLabel2.setText(input[1]);
			descriptionTextField2.setText(input[2]);
			artworkPhoto2.setImage(this.getImage(count));
			HBox2.setVisible(true);

			if (checkIfMoreAndIncrease()){
				input = this.getInfo(count);
				artworkTitleLabel3.setText(input[0]);
				lastBidAmountLabel3.setText(input[1]);
				descriptionTextField3.setText(input[2]);
				artworkPhoto3.setImage(this.getImage(count));
				HBox3.setVisible(true);

				if (checkIfMoreAndIncrease()){
					input = this.getInfo(count);
					artworkTitleLabel4.setText(input[0]);
					lastBidAmountLabel4.setText(input[1]);
					descriptionTextField4.setText(input[2]);
					artworkPhoto4.setImage(this.getImage(count));
					HBox4.setVisible(true);

					if (checkIfMoreAndIncrease()){
						input = this.getInfo(count);
						artworkTitleLabel5.setText(input[0]);
						lastBidAmountLabel5.setText(input[1]);
						descriptionTextField5.setText(input[2]);
						artworkPhoto5.setImage(this.getImage(count));
						HBox5.setVisible(true);


						if (checkIfMoreAndIncrease()){
							input = this.getInfo(count);
							artworkTitleLabel6.setText(input[0]);
							lastBidAmountLabel6.setText(input[1]);
							descriptionTextField6.setText(input[2]);
							artworkPhoto6.setImage(this.getImage(count));
							HBox6.setVisible(true);
						}}}}}}


	private boolean checkIfMoreAndIncrease(){
		boolean result = false;
		if(count<auctionsToDisplay.size()-1){
			result = true;
			count++;
		}
		return result;
	}

	public void nextPageButtonClicked(){
		if(checkIfMoreAndIncrease()){
			this.clearDisplayedAuctions();
			this.updateNavigationLabel();
			this.updateDisplayedAuctions();
		}
	}

	public void backPageButtonClicked(){
		if((count/AUCTIONS_PER_WINDOW-1)*AUCTIONS_PER_WINDOW >= 0){
			count = (count/AUCTIONS_PER_WINDOW-1)*AUCTIONS_PER_WINDOW;
			this.clearDisplayedAuctions();
			this.updateNavigationLabel();
			this.updateDisplayedAuctions();
		}
	}

	private void clearDisplayedAuctions(){
		HBox1.setVisible(false);
		HBox2.setVisible(false);
		HBox3.setVisible(false);
		HBox4.setVisible(false);
		HBox5.setVisible(false);
		HBox6.setVisible(false);
	}

	private String[] getInfo(int count){
		Auction displayedAuction = auctionsToDisplay.get(count);
		Artwork displayedArtwork = displayedAuction.getArtwork();

		String[] result = new String[3];

		result[0] = displayedArtwork.getTitle();
		result[1] = "�" + String.valueOf(displayedAuction.getAuctionLastBidAmount());
		result[2] = displayedArtwork.getDescription();

		return result;
	}

	private Image getImage(int count){
		InputStream stream = getClass().getResourceAsStream(auctionsToDisplay.get(count).getArtwork().getPhotographPath());
		Image newImage = new Image(stream);
		return newImage;
	}

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

	public void goBackButtonClicked(){
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void artworkTypeComboBox() {
		if(artworkTypeComboBox.getValue().equals("Painting")) {			
			auctionsToDisplay = AuctionList.getPaintingAuctions();
			this.refresh();
		} else  if(artworkTypeComboBox.getValue().equals("Sculpture")){
			auctionsToDisplay = AuctionList.getSculptureAuctions();
			this.refresh();
		} else  if(artworkTypeComboBox.getValue().equals("All")){
			auctionsToDisplay = AuctionList.getAuctions();
			this.refresh();
		}

	}

	public void mouseClickHBox0Handler(){	
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}
	
	public void mouseClickHBox1Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index + 1);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}
	
	public void mouseClickHBox2Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index + 2);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}
	
	public void mouseClickHBox3Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index + 3);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}
	
	public void mouseClickHBox4Handler(){	
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index + 4);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}
	
	public void mouseClickHBox5Handler(){		
		int index = ((count)/AUCTIONS_PER_WINDOW)*AUCTIONS_PER_WINDOW;
		Auction clikedAuction = auctionsToDisplay.get(index + 5);
		this.bringViewAuctionSceneForAuction(clikedAuction);		
	}

	private void bringViewAuctionSceneForAuction(Auction auction){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
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