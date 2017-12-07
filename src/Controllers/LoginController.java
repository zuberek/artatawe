package src.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import src.CONSTANTS;
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
 * @author Joshua Blackman 
 * @author Jan Dabrowski
 *
 */
public class LoginController {
	
	@FXML TextField loginUserName;
    @FXML private Button loginButton;
	
	/**
	 * Event handler for when user clicks the login button
	 */
	public void loginButtonClicked() {
		if(loginUserName.getText().isEmpty()) {
			 CONSTANTS.makeAlertWindow("warning", "Please fill in the username field.");
		} else {
			try {
				User user = new User();
				if(user.userExists(loginUserName.getText())) {
					User currentUser = new User(loginUserName.getText());
	
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Dashboard.fxml"));
					Parent editRoot = (Parent) fxmlLoader.load();
	
					DashboardController dc = fxmlLoader.getController();
					dc.initialize(currentUser);
	
					Scene newScene = new Scene(editRoot, CONSTANTS.BIG_WINDOW_WIDTH,CONSTANTS.BIG_WINDOW_HEIGHT);
					Stage stage = (Stage) loginButton.getScene().getWindow();
	
					stage.setScene(newScene);
				} else {
		            CONSTANTS.makeAlertWindow("warning", "That user does not exist.");
				}
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
			BorderPane editRoot = (BorderPane) fxmlLoader.load();


			Scene newScene = new Scene(editRoot, CONSTANTS.MEDIUM_WINDOW_WIDTH, CONSTANTS.MEDIUM_WINDOW_HEIGHT);
			Stage editStagee = new Stage();
			
			RegisterController registerController = fxmlLoader.getController();
			registerController.initialize(new User());
			
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
