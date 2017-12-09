package src.Controllers;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.Artwork;
import src.Auction;
import src.AuctionList;
import src.CONSTANTS;
import src.Painting;
import src.Sculpture;
import src.User;

/**
 * @author Jan Dabrowski
 *
 */
public class AddAuctionController {

	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("Painting", "Sculpture");

	@FXML ComboBox artworkTypeComboBox;

	@FXML TextField titleTextField;
	@FXML TextField artistTextField;
	@FXML TextField yearTextField;
	@FXML TextArea descriptionTextField;
	@FXML TextField reservePriceTextField;
	@FXML TextField maxBidsTextField;
	@FXML TextField heightTextField;
	@FXML TextField widthTextField;
	@FXML TextField depthTextField;
	@FXML TextField typeSpecificTextField;
	@FXML ImageView artworkPhoto;

	@FXML Label depthLabel;	
	@FXML Label typeSpecificLabel;

	User currentUser;
	Artwork artworkToCreate;

	public void initialize(User currentUser) {
		// TODO Auto-generated method stub
		this.currentUser = currentUser;
		this.artworkToCreate = new Sculpture();
		//this.artworkToCreate = new Artwork();
		artworkTypeComboBox.setValue("Sculpture");
		artworkTypeComboBox.setItems(artworkChoiceList);

		this.setArtworkImage();
	}
	
	private void setArtworkImage(){
		InputStream stream = getClass().getResourceAsStream(artworkToCreate.getPhotographPath());
		Image newImage = new Image(stream);
		artworkPhoto.setImage(newImage);
	}

	@FXML
	private void artworkTypeComboBox() {
		if(artworkTypeComboBox.getValue().equals("Sculpture")) {
			depthLabel.setVisible(true);
			depthTextField.setVisible(true);
			typeSpecificLabel.setText("Material");
		} else {
			depthLabel.setVisible(false);
			depthTextField.setVisible(false);
			depthTextField.clear();
			typeSpecificLabel.setText("Painting Type");
		}
	}

	public void pictureChangeButtonClicked(){	
		int artworkID = AuctionList.getNewestArtworkID()+1;
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		String nameAndPath = "./src/Resources/ArtworksImages/"+ currentUser.getUserID() + "." + artworkID + ".png";

		File file = fileChooser.showOpenDialog(depthLabel.getScene().getWindow());

		if (file != null) {
			try {
				Image image = new Image(file.toURI().toString());
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(nameAndPath));
				this.artworkToCreate.setPhotographPath("../Resources/ArtworksImages/"+ currentUser.getUserID() + "." + artworkID + ".png");
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		this.setArtworkImage();
	}

	private void configureFileChooser(
			final FileChooser fileChooser) {      
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
				);                 
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
				);
	}

	public void addAuctionButonClicked(){
		if(titleTextField.getText().isEmpty() || artistTextField.getText().isEmpty() || yearTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || reservePriceTextField.getText().isEmpty() || maxBidsTextField.getText().isEmpty() || widthTextField.getText().isEmpty() || heightTextField.getText().isEmpty()) {
			CONSTANTS.makeAlertWindow("warning", "Please fill in all fields.");
		} else if(!CONSTANTS.isNumeric(reservePriceTextField.getText()) || !CONSTANTS.isNumeric(maxBidsTextField.getText())) {
			CONSTANTS.makeAlertWindow("warning", "Please input a valid number.");
		} else if (!CONSTANTS.isNumeric(yearTextField.getText()) || Integer.parseInt(yearTextField.getText()) >= CONSTANTS.MAX_YEAR || Integer.parseInt(yearTextField.getText()) <= CONSTANTS.SMALLEST_YEAR){
			CONSTANTS.makeAlertWindow("warning","Please input a valid year within a range: " + CONSTANTS.SMALLEST_YEAR + " - " + CONSTANTS.MAX_YEAR);
		} else if(!CONSTANTS.isNumeric(widthTextField.getText()) || !CONSTANTS.isNumeric(depthTextField.getText()) || !CONSTANTS.isNumeric(heightTextField.getText()) || Integer.parseInt(widthTextField.getText()) < CONSTANTS.MIN_SIZE ){
			CONSTANTS.makeAlertWindow("warning","Please input a valid size parameter within the range: " + CONSTANTS.MIN_SIZE + " - " + CONSTANTS.MAX_SIZE);
		}
		else {
			switch(artworkTypeComboBox.getSelectionModel().getSelectedItem().toString()) {
			case "Painting":
				Painting painting = new Painting(currentUser.getUserID(), titleTextField.getText(), artistTextField.getText(), descriptionTextField.getText(), artworkToCreate.getPhotographPath(), yearTextField.getText(), Double.parseDouble(heightTextField.getText()), Double.parseDouble(widthTextField.getText()), typeSpecificTextField.getText());
				break;
			case "Sculpture":
				Sculpture sculpture = new Sculpture(currentUser.getUserID(), titleTextField.getText(), artistTextField.getText(), descriptionTextField.getText(), artworkToCreate.getPhotographPath(), yearTextField.getText(), Double.parseDouble(heightTextField.getText()), Double.parseDouble(widthTextField.getText()), Double.parseDouble(depthTextField.getText()), typeSpecificTextField.getText());
				break;
			}
			int artworkID = AuctionList.getNewestArtworkID();
			Auction auction = new Auction(currentUser.getUserID(), artworkID, Integer.parseInt(maxBidsTextField.getText()), Integer.parseInt(reservePriceTextField.getText()));
			this.closeWindow();
		}
	} 

	private boolean validateSize(){
		boolean result = false;
		if(CONSTANTS.isNumeric(widthTextField.getText()) || CONSTANTS.isNumeric(depthTextField.getText()) || CONSTANTS.isNumeric(heightTextField.getText())){
			int max = CONSTANTS.MAX_SIZE;
			int min = CONSTANTS.MIN_SIZE;

			int width = Integer.parseInt(widthTextField.getText());
			int height = Integer.parseInt(heightTextField.getText());
			int depth = Integer.parseInt(depthTextField.getText());

			if(isRight(max,min,width) && isRight(max,min,height) && isRight(max,min,depth)){
				result = true;
			}
		}
		return result;
	}

	private boolean isRight(int max, int min, int par){
		boolean result = false;
		if(par >= min && par <= max){
			result = true;
		}
		return result;
	}

	private void closeWindow() {
		Stage stage = (Stage)titleTextField.getScene().getWindow();
		stage.close();
	}

}