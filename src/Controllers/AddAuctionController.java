package src.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.CONSTANTS;
import src.User;

public class AddAuctionController {
	
	ObservableList<String> artworkChoiceList = FXCollections.observableArrayList("Painting", "Sculpture");
	
	@FXML ChoiceBox artworkTypeChoiceBox;
	@FXML TextField titleTextField;
	@FXML TextField artistTextField;
	@FXML TextField yearTextField;
	@FXML TextArea descriptionTextField;
	@FXML TextField reservePriceTextField;
	@FXML TextField maxBidsTextField;

	User currentUser;
	
	public void initialize(User currentUser) {
		// TODO Auto-generated method stub
		this.currentUser = currentUser;
		artworkTypeChoiceBox.setValue("Painting");
		artworkTypeChoiceBox.setItems(artworkChoiceList);
	}

	public void addAuctionButonClicked(){
		if(titleTextField.getText().isEmpty() || artistTextField.getText().isEmpty() || yearTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || reservePriceTextField.getText().isEmpty() || maxBidsTextField.getText().isEmpty()) {
            CONSTANTS.makeAlertWindow("warning", "Please fill in all fields.");
        } else if(CONSTANTS.isNumeric(yearTextField.getText()) || CONSTANTS.isNumeric(reservePriceTextField.getText()) || CONSTANTS.isNumeric(maxBidsTextField.getText())) {
            CONSTANTS.makeAlertWindow("warning", "Please input a valid number.");
        } else {
        	//here will be creation of artwork one the drop down menu is done

    		closeWindow();
            //System.out.println(user.getFirstName());
        } 
	}
	
    private void closeWindow() {
        Stage stage = (Stage)titleTextField.getScene().getWindow();
        stage.close();
    }

}
