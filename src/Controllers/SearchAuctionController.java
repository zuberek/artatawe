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
import src.Sculpture;
import src.User;
import src.Artwork;
import src.Auction;
import src.AuctionList;

/**
 * @author Borislav Koynin
 * @author Jan Dabrowski
 *
 */
public class SearchAuctionController {
	
	ArrayList<Auction> auctionsToDisplay  = new ArrayList<>();
	
	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("All","Painting", "Sculpture");

	@FXML ComboBox<String> artworkTypeComboBox;
	@FXML Label artworkTitleLabel;
	@FXML Label lastBidAmountLabel;
	@FXML TextArea descriptionTextField;
	@FXML ImageView artworkPhoto;
	

	public void initialize() {
		auctionsToDisplay = AuctionList.getAuctions();
		
		Auction displayedAuction = auctionsToDisplay.get(0);
		Artwork displayedArtwork = displayedAuction.getArtwork();
		artworkTypeComboBox.setValue("All");
		artworkTypeComboBox.setItems(artworkChoiceList);

		this.artworkTitleLabel.setText(displayedArtwork.getTitle());
		this.lastBidAmountLabel.setText(String.valueOf(displayedAuction.getAuctionLastBidAmount()));
		this.descriptionTextField.setText(displayedArtwork.getDescription());
		
		InputStream stream = getClass().getResourceAsStream(displayedArtwork.getPhotographPath());
		Image newImage = new Image(stream);
		artworkPhoto.setImage(newImage);
	}
	

	@FXML
	private void artworkTypeComboBox() {
		if(artworkTypeComboBox.getValue().equals("Painting")) {
			//			 TO DO WHAT TO DO WHEN A CHOICE IS MADE:


			//			depthLabel.setVisible(true);
			//			depthTextField.setVisible(true);
			//			typeSpecificLabel.setText("Material");
		} else  if(artworkTypeComboBox.getValue().equals("Painting")){
			//			depthLabel.setVisible(false);
			//			depthTextField.setVisible(false);
			//			depthTextField.clear();
			//			typeSpecificLabel.setText("Painting Type");
		}

	}
	
	
	
	
}