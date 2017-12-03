package src.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.User;

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

    @FXML Pane rootPane;

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
            //System.out.println(user.getFirstName());
        }

        closeWindow();
    }

    public void backLoginButtonClicked(){
        //this part brings back the log in scene
        closeWindow();
    }

    public void picEditButtonClicked(){

    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
