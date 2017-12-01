package src.Controllers;

import src.User;
import src.Register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * This class is the controller for the src.Login screen
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
			User user = new User(loginUserName.getText());
			System.out.println("Hello " + user.getFirstName());
		}
		
	}

	/**
	 * Event handler for when user clicks the register button
	 */
	public void registerButtonClicked() {
	    try{

            Stage stage = (Stage) registerButton.getScene().getWindow();
            Register reg = new Register();

            reg.start(stage);

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Sth went wrong with bringing the register view");
            e.printStackTrace();
        }
    }
}
