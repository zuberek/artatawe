package src.Controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.CONSTANTS;
import src.User;
import src.DrawingTool.DrawingCanvas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
 * @author Joshua Blackman
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
	 * @param userToEdit The user to be edited.
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
	 * Event handler for draw profile picture button
	 */
	public void drawButtonClicked(){
        Stage newStage = new Stage();
        // Set up drawing canvas
        DrawingCanvas newCanvas = new DrawingCanvas();
        Scene newScene = newCanvas.initialise(userBeingEdited);

        newStage.setScene(newScene);
        newStage.showAndWait();

        //Reinitialise user profile
        this.initialize(userBeingEdited);
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
		
		if (userNameTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty() ||
				lastNameTextField.getText().isEmpty() || phoneNoTextField.getText().isEmpty() ||
				addressTextField.getText().isEmpty()) {
			CONSTANTS.makeAlertWindow("warning", "Please fill in all fields.");
		} 	//First checks if the username was changed, then check if the new user name is already used
		else if (!userBeingEdited.getUserName().equals(userNameTextField.getText()) &&
				userBeingEdited.userExists(userNameTextField.getText())){
			CONSTANTS.makeAlertWindow("warning", "This username is already in use!");
			//Revert to previous username
			userNameTextField.setText(userBeingEdited.getUserName());
		} else if (!CONSTANTS.isNumeric(phoneNoTextField.getText()) ||
				phoneNoTextField.getLength() != 11) {
			CONSTANTS.makeAlertWindow("warning", "Please input a valid phone number.");
			phoneNoTextField.setText(userBeingEdited.getPhoneNo());
		} else {
			userBeingEdited.setUserName((userNameTextField.getText()));
			userBeingEdited.setFirstName(firstNameTextField.getText());
			userBeingEdited.setLastName(lastNameTextField.getText());
			userBeingEdited.setPhoneNo(phoneNoTextField.getText());
			userBeingEdited.setUserAddress(addressTextField.getText());
			userBeingEdited.saveEditedUser();
			closeWindow();
		}
	}

	/**
	 * Event handler for changing profile image.
	 */
	public void editProfileImageButtonClicked(){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/DefaultAvatar.fxml"));
			BorderPane editRoot = (BorderPane) fxmlLoader.load();

			DefaultAvatarController controller = fxmlLoader.getController();
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

	/**
	 * Close the window.
	 */
	private void closeWindow() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}
}
