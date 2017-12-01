package src.Controllers;
import src.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * This class is the controller for the src.Login screen
 * It will handle all inputs and validation
 * 
 * @author Joshua Blackman and Jan Dabrowski
 *
 */
public class LoginController {
	
	@FXML TextField loginUserName;
	
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
			User user = new User(loginUserName.getText());
			System.out.println("Hello " + user.getFirstName());
		}
		
	}
}
