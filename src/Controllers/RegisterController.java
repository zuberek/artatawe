package src.Controllers;

import src.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.User;

import java.io.IOException;

/**
 * This class is the controller for the Register screen
 * It will handle all inputs and validation
 *
 * @author Jan Dabrowski
 *
 */
public class RegisterController {


    @FXML TextField registerUserName;
    @FXML TextField registerFirstName;
    @FXML TextField registerLastName;
    @FXML TextField registerPhoneNo;
    @FXML TextField registerUserAddress;

    @FXML private Button registerButton;

    /**
     * Event handler for when user clicks the register button
     */
    public void registerButtonClicked() {
        if(registerUserName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Please fill in the username field.");
            alert.showAndWait();
        } else {
            User user = new User(registerUserName.getText(), registerFirstName.getText(), registerLastName.getText(), registerPhoneNo.getText(), registerUserAddress.getText(),123123);
            System.out.println(user.getFirstName());
        }


        //this part brings back the log in scene
        try{

            Stage stage = (Stage) registerButton.getScene().getWindow();
            Login log = new Login();

            log.start(stage);

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Sth went wrong with bringing the log in view");
            e.printStackTrace();
        }

    }

    public void backLoginButtonClicked(){
        //this part brings back the log in scene
        try{

            Stage stage = (Stage) registerButton.getScene().getWindow();
            Login log = new Login();

            log.start(stage);

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Sth went wrong with bringing the log in view");
            e.printStackTrace();
        }
    }

    public void picEditButtonClicked(){

    }
}
