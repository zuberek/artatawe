package src.Controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    public void setUserForEditing(User userToEdit) {
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
        // We just need to close the window.
        closeWindow();
    }

    /**
     * Handle the confirm button.
     * Save the changes and close the window.
     */
    public void handleConfirmButtonAction() {
        userBeingEdited.setUserName((userNameTextField.getText()));
        userBeingEdited.setFirstName(firstNameTextField.getText());
        userBeingEdited.setLastName(lastNameTextField.getText());
        userBeingEdited.setPhoneNo(phoneNoTextField.getText());
        userBeingEdited.setUserAddress(addressTextField.getText());
        userBeingEdited.saveEditedUser();
        closeWindow();
    }

    public void editProfileImageButtonClicked(){

    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
