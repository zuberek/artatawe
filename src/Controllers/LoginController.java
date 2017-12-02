package src.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import src.Login;
import src.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * This class is the controller for the Login screen
 * It will handle all inputs and validation
 * 
 * @author Joshua Blackman and Jan Dabrowski
 *
 */
public class LoginController {
	
	@FXML TextField loginUserName;
    @FXML private Button registerButton;
	
	/**
	 * Event handler for when user clicks the login button
	 */
	public void loginButtonClicked() {
		if(loginUserName.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setContentText("Please fill in the username field.");
			alert.showAndWait();
		} else {
			try {
				User currentUser = new User(loginUserName.getText());

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Profile.fxml"));
				BorderPane editRoot = (BorderPane) fxmlLoader.load();

				ProfileController profileController = fxmlLoader.getController();
				profileController.setUserForEditing(currentUser);

				Scene newScene = new Scene(editRoot, Login.MAIN_WINDOW_WIDTH, Login.MAIN_WINDOW_HEIGHT);
				Stage editStagee = new Stage();
				editStagee.setScene(newScene);
				editStagee.setTitle("Artatawe | Edit User");

				editStagee.initModality(Modality.APPLICATION_MODAL);

				editStagee.showAndWait();
			} catch (IOException e) {
					e.printStackTrace();
					// Quit the program (with an error code)
					System.exit(-1);
				}
		}
		
	}

	/**
	 * Event handler for when user clicks the register button
	 */
	public void registerButtonClicked() {
	    try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Register.fxml"));
			AnchorPane editRoot = (AnchorPane) fxmlLoader.load();


			Scene newScene = new Scene(editRoot, Login.MAIN_WINDOW_WIDTH, Login.MAIN_WINDOW_HEIGHT);
			Stage editStagee = new Stage();
			editStagee.setScene(newScene);
			editStagee.setTitle("Artatawe | Register");

			editStagee.initModality(Modality.APPLICATION_MODAL);

			editStagee.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }
}
