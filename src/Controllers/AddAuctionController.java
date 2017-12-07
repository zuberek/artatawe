package src.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.CONSTANTS;
import src.Painting;
import src.Sculpture;
import src.User;

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
	
	@FXML Label depthLabel;	
	@FXML Label typeSpecificLabel;

	User currentUser;
	
	public void initialize(User currentUser) {
		// TODO Auto-generated method stub
		this.currentUser = currentUser;
		artworkTypeComboBox.setValue("Sculpture");
		artworkTypeComboBox.setItems(artworkChoiceList);
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
			typeSpecificLabel.setText("Painting Type");
		}
	}

	public void addAuctionButonClicked(){
		if(titleTextField.getText().isEmpty() || artistTextField.getText().isEmpty() || yearTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || reservePriceTextField.getText().isEmpty() || maxBidsTextField.getText().isEmpty()) {
			CONSTANTS.makeAlertWindow("warning", "Please fill in all fields.");
		} else if(CONSTANTS.isNumeric(yearTextField.getText()) || CONSTANTS.isNumeric(reservePriceTextField.getText()) || CONSTANTS.isNumeric(maxBidsTextField.getText())) {
			CONSTANTS.makeAlertWindow("warning", "Please input a valid number.");
		} else {
			switch(artworkTypeComboBox.getSelectionModel().getSelectedItem().toString()) {
			case "Painting":
				Painting painting = new Painting(currentUser.getUserID(), titleTextField.getText(), artistTextField.getText(), descriptionTextField.getText(), "../Pictures/Painting.png", "132", Double.parseDouble(heightTextField.getText()), Double.parseDouble(widthTextField.getText()), typeSpecificTextField.getText());
				closeWindow();
				break;
            case "Sculpture":
            	Sculpture sculpture = new Sculpture(currentUser.getUserID(), titleTextField.getText(), artistTextField.getText(), descriptionTextField.getText(), "../Pictures/Painting.png", "132", Double.parseDouble(heightTextField.getText()), Double.parseDouble(widthTextField.getText()), Double.parseDouble(depthTextField.getText()), typeSpecificTextField.getText());       
            	closeWindow();
            	break;
			}
		}
		//System.out.println(user.getFirstName());
	} 


	private void closeWindow() {
		Stage stage = (Stage)titleTextField.getScene().getWindow();
		stage.close();
	}

}