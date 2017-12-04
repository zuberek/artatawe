package src.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.User;

import java.io.IOException;
import java.io.InputStream;

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
    @FXML ImageView profileImage;

    @FXML Pane rootPane;

    public String profileImagePath = "../Pictures/avatar1.png";

    //User to be added to the database
    User userToCreate;

    public void setCurrentProfileImagePath(String filePath){
        this.profileImagePath = filePath;
    }

    public void initialize(){
        InputStream stream = getClass().getResourceAsStream(profileImagePath);

        Image newImage = new Image(stream);
        profileImage.setImage(newImage);
    }

    /**
     * Event handler for when user clicks the register button
     */
    public void registerButtonClicked() {
    	User user = new User();
        if(registerUserName.getText().isEmpty() || registerFirstName.getText().isEmpty() || registerLastName.getText().isEmpty() || registerPhoneNo.getText().isEmpty() || isNumeric(registerPhoneNo.getText()) || registerUserAddress.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        } else if(user.userExists(registerUserName.getText())) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("That username is already taken.");
            alert.showAndWait();
    	} else {
            User createdUser = new User(registerUserName.getText(), registerFirstName.getText(), registerLastName.getText(), registerPhoneNo.getText(), registerUserAddress.getText(), profileImagePath,123123);
            //System.out.println(user.getFirstName());
        }

        closeWindow();
    }

    public void backLoginButtonClicked(){
        //this part brings back the log in scene
        closeWindow();
    }

    public void picEditButtonClicked(){
        User userToCreate = new User();
        userToCreate.setUserName(registerUserName.getText());
        userToCreate.setFirstName(registerFirstName.getText());
        userToCreate.setLastName(registerLastName.getText());
        userToCreate.setPhoneNo(registerPhoneNo.getText());
        userToCreate.setUserAddress(registerUserAddress.getText());
        userToCreate.setDefaultAvatar(profileImagePath);

        registerUserName.setText(registerUserName.getText());
        registerUserName.setText(registerFirstName.getText());
        registerUserName.setText(registerLastName.getText());
        registerUserName.setText(registerPhoneNo.getText());
        registerUserName.setText(registerUserAddress.getText());

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/DefaultAvatar.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            defautAvatarController controller = fxmlLoader.getController();
            controller.initialize(this);

            Scene newScene = new Scene(editRoot);
            Stage editStagee = new Stage();
            editStagee.setScene(newScene);
            editStagee.setTitle("Artatawe |Default Profile Images");

            editStagee.initModality(Modality.APPLICATION_MODAL);

            editStagee.showAndWait();
            System.out.println(profileImagePath);
            initialize();
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
