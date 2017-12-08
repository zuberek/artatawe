/**
 * 
 */
package src.Controllers;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import src.Sculpture;
import src.User;
import src.Auction;

/**
 * @author Borislav Koynin
 * @author Jan Dabrowski
 *
 */
public class SearchAuctionController {

	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("All","Painting", "Sculpture");

	@FXML ComboBox artworkTypeComboBox;
	ArrayList<Auction> auctionsToDisplay  = new ArrayList<>();

	public void initialize() {
		// TODO Auto-generated method stub
		//		this.currentUser = currentUser;
		//		this.artworkToCreate = new Sculpture();
		//this.artworkToCreate = new Artwork();
		artworkTypeComboBox.setValue("All");
		artworkTypeComboBox.setItems(artworkChoiceList);

		//		this.setArtworkImage();
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