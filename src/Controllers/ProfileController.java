package src.Controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.CONSTANTS;
import src.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class is the controller for the Profile edition screen
 * It will handle all inputs and validation
 * @author  Jan Dabrowski
 *
 */
public class ProfileController {
	@FXML Button cancelButton;
	@FXML Button confirmButton;
	@FXML TextField userNameTextField;
	@FXML TextField firstNameTextField;
	@FXML TextField lastNameTextField;
	@FXML TextField phoneNoTextField;
	@FXML TextField addressTextField;
	@FXML Pane rootPane;
	@FXML ImageView profileImage;

	// The user being edited.
	User userBeingEdited;

	/**
	 * Set the user that is being edited.
	 * When this window is closed, the changes will be set in this user object.
	 * @param The user to be edited.
	 */
	public void initialize(User userToEdit) {
		// Keep a reference to the user that we are editing.
		this.userBeingEdited = userToEdit;

		// Update the GUI to show the existing data.
		userNameTextField.setText(userBeingEdited.getUserName());
		firstNameTextField.setText(userBeingEdited.getFirstName());
		lastNameTextField.setText(userBeingEdited.getLastName());
		phoneNoTextField.setText(userBeingEdited.getPhoneNo());
		addressTextField.setText(userBeingEdited.getUserAddress());

		InputStream stream = getClass().getResourceAsStream(userBeingEdited.getDefaultAvatar());
		Image newImage = new Image(stream);
		profileImage.setImage(newImage);
	}

	/**
	 * Handle the cancel button.
	 * Cancel the edit and close the window.
	 */
	public void handleCancelButtonAction() {
		closeWindow();
	}

	/**
	 * Handle the confirm button.
	 * Save the changes and close the window.
	 */
	public void handleConfirmButtonAction() {
		String oldUserName = userBeingEdited.getUserName();

		userBeingEdited.setUserName((userNameTextField.getText()));
		userBeingEdited.setFirstName(firstNameTextField.getText());
		userBeingEdited.setLastName(lastNameTextField.getText());
		userBeingEdited.setPhoneNo(phoneNoTextField.getText());
		userBeingEdited.setUserAddress(addressTextField.getText());
		userBeingEdited.saveEditedUser();
		
		if(userBeingEdited.getUserName().isEmpty() || userBeingEdited.getFirstName().isEmpty() || userBeingEdited.getLastName().isEmpty() || userBeingEdited.getPhoneNo().isEmpty() || userBeingEdited.getUserAddress().isEmpty()) {
			CONSTANTS.makeAlertWindow("Please fill in all fields.");
		} else if (!(userNameTextField.getText().equals(oldUserName)) &&  userBeingEdited.userExists(userBeingEdited.getUserName())){
			CONSTANTS.makeAlertWindow("this username is already used!");
		} else if(!isNumeric(userBeingEdited.getPhoneNo())) {			
			CONSTANTS.makeAlertWindow("Please input a valid phone number.");			
		} else {
			userBeingEdited.saveEditedUser();
			closeWindow();
			//System.out.println(user.getFirstName());
		}
	}

	public void editProfileImageButtonClicked(){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/DefaultAvatar.fxml"));
			BorderPane editRoot = (BorderPane) fxmlLoader.load();

			defautAvatarController controller = fxmlLoader.getController();
			controller.initialize(userBeingEdited);

			Scene newScene = new Scene(editRoot);
			Stage editStagee = new Stage();
			editStagee.setScene(newScene);
			editStagee.setTitle("Artatawe |Default Profile Images");

			editStagee.initModality(Modality.APPLICATION_MODAL);

			editStagee.showAndWait();

			initialize(userBeingEdited);
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}

	private boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}


	/**
	 * Close the window.
	 */
	private void closeWindow() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}
}
