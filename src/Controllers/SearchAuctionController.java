/**
 * 
 */
package src.Controllers;

import java.io.InputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import src.Artwork;
import src.Auction;
import src.AuctionList;

/**
 * @author Borislav Koynin
 * @author Jan Dabrowski
 *
 */
public class SearchAuctionController {

	private ArrayList<Auction> auctionsToDisplay  = new ArrayList<>();
	private int count;

	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("All","Painting", "Sculpture");

	@FXML ComboBox<String> artworkTypeComboBox;
	@FXML Label navigationLabel;

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

	public void initialize() {
		auctionsToDisplay = AuctionList.getAuctions();
		int size = auctionsToDisplay.size();

		int auctionsOnScreen = 0;
		if(size > 6){
			auctionsOnScreen = 6;
		} else {
			auctionsOnScreen = size;
		}

		navigationLabel.setText("1" + " - " + auctionsOnScreen + " of " + size);
		count = 0;

		this.clearDisplayedAuctions();
		if(!auctionsToDisplay.isEmpty()){
			this.updateDisplayedAuctions();
		}
		artworkTypeComboBox.setValue("All");
		artworkTypeComboBox.setItems(artworkChoiceList);
	}

	private void updateDisplayedAuctions(){
		String[] input = this.getInfo(count);
		artworkTitleLabel1.setText(input[0]);
		lastBidAmountLabel1.setText(input[1]);
		descriptionTextField1.setText(input[2]);
		artworkPhoto1.setImage(this.getImage(count));
		HBox1.setVisible(true);

		count ++;
		if (checkIfMore(count)){
			input = this.getInfo(count);
			artworkTitleLabel2.setText(input[0]);
			lastBidAmountLabel2.setText(input[1]);
			descriptionTextField2.setText(input[2]);
			artworkPhoto2.setImage(this.getImage(0));
			HBox2.setVisible(true);

			count ++;
			if (checkIfMore(count)){
				input = this.getInfo(count);
				artworkTitleLabel3.setText(input[0]);
				lastBidAmountLabel3.setText(input[1]);
				descriptionTextField3.setText(input[2]);
				artworkPhoto3.setImage(this.getImage(count));
				HBox3.setVisible(true);

				count ++;
				if (checkIfMore(count)){
					input = this.getInfo(count);
					artworkTitleLabel4.setText(input[0]);
					lastBidAmountLabel4.setText(input[1]);
					descriptionTextField4.setText(input[2]);
					artworkPhoto4.setImage(this.getImage(count));
					HBox4.setVisible(true);

					count ++;
					if (checkIfMore(count)){
						input = this.getInfo(count);
						artworkTitleLabel5.setText(input[0]);
						lastBidAmountLabel5.setText(input[1]);
						descriptionTextField5.setText(input[2]);
						artworkPhoto5.setImage(this.getImage(count));
						HBox5.setVisible(true);

						count ++;
						if (checkIfMore(count)){
							input = this.getInfo(count);
							artworkTitleLabel6.setText(input[0]);
							lastBidAmountLabel6.setText(input[1]);
							descriptionTextField6.setText(input[2]);
							artworkPhoto6.setImage(this.getImage(count));
							HBox6.setVisible(true);
							count++;
						}}}}}}


	private boolean checkIfMore(int count){
		boolean result = false;
		if(count<auctionsToDisplay.size()){
			result = true;
		}
		return result;
	}

	public void nextPageButtonClicked(){
		if(checkIfMore(count)){
			this.clearDisplayedAuctions();
			this.updateNavigationLabel();
			this.updateDisplayedAuctions();
		}
	}

	public void backPageButtonClicked(){
		count = count - 7;
		this.clearDisplayedAuctions();
		this.updateNavigationLabel();
		this.updateDisplayedAuctions();
	}

	private void refreshDisplayedAuctions(){
		count = count - 5;
		this.clearDisplayedAuctions();
		this.updateDisplayedAuctions();
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
		result[1] = "£" + String.valueOf(displayedAuction.getAuctionLastBidAmount());
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
		
		int auctionsOnScreen = count + 6;
		if(size > auctionsOnScreen){
			auctionsOnScreen = 6;
		} else {
			auctionsOnScreen = size;
		}

		navigationLabel.setText(count + " - " + auctionsOnScreen + " of " + size);
	}

	@FXML
	private void artworkTypeComboBox() {
		if(artworkTypeComboBox.getValue().equals("Painting")) {			
			auctionsToDisplay = AuctionList.getPaintingAuctions();

			refreshDisplayedAuctions();
		} else  if(artworkTypeComboBox.getValue().equals("Painting")){
			//			depthLabel.setVisible(false);
			//			depthTextField.setVisible(false);
			//			depthTextField.clear();
			//			typeSpecificLabel.setText("Painting Type");
		}

	}




}