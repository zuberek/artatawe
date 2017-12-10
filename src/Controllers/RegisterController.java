package src.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Address;
import src.CONSTANTS;
import src.DrawingTool.DrawingCanvas;
import src.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class is the controller for the Register screen
 * It will handle all inputs and validation
 *
 * @author Jan Dabrowski
 * @author Joshua Blackman
 *
 */
public class RegisterController {


    @FXML TextField registerUserName;
    @FXML TextField registerFirstName;
    @FXML TextField registerLastName;
    @FXML TextField registerPhoneNo;
    @FXML TextField registerUserAddressLine;
    @FXML TextField registerUserTown;
    @FXML TextField registerUserPostcode;
    @FXML ImageView profileImage;

    @FXML Pane rootPane;

    @FXML Button drawButton;

    //User to be added to the database
    private User userToCreate;

    public void initialize(User newUser){
    	userToCreate = newUser;
        InputStream stream = getClass().getResourceAsStream(newUser.getDefaultAvatar());

        Image newImage = new Image(stream);
        profileImage.setImage(newImage);
    }

    public void drawButtonClicked(){

        Stage newStage = new Stage();

        DrawingCanvas newCanvas = new DrawingCanvas();
        Scene newScene = newCanvas.initialise(userToCreate);

        newStage.setScene(newScene);
        newStage.showAndWait();
        
        this.initialize(userToCreate);
    }

    /**
     * Event handler for when user clicks the register button
     */
    public void registerButtonClicked() {
    	
    	
		if(registerUserName.getText().isEmpty() || registerFirstName.getText().isEmpty() || registerPhoneNo.getText().isEmpty() || registerUserAddressLine.getText().isEmpty() || registerUserTown.getText().isEmpty() || registerUserPostcode.getText().isEmpty() || registerLastName.getText().isEmpty()) {
            CONSTANTS.makeAlertWindow("warning", "Please fill in all fields.");
        } else if(!isNumeric(registerPhoneNo.getText()) || registerPhoneNo.getLength() != 11) {
            CONSTANTS.makeAlertWindow("warning", "Please input a valid phone number.");
        } else if(userToCreate.userExists(registerUserName.getText())) {      
            CONSTANTS.makeAlertWindow("warning", "That username is already taken.");
    	} else if(!CONSTANTS.isAlpha(registerFirstName.getText()) || !CONSTANTS.isAlpha(registerLastName.getText())){
		    CONSTANTS.makeAlertWindow("warning","Please input valid personal details (no numbers)");
        } else if(!Address.validatePostCode(registerUserPostcode.getText())){ 
        	 CONSTANTS.makeAlertWindow("warning","Please input a valid UK postcode. (Must have a space)");
        } else {
        	String address = registerUserAddressLine.getText() + ", " + registerUserTown.getText() + ", " + registerUserPostcode.getText();
    		User createdUser = new User(registerUserName.getText(), registerFirstName.getText(), registerLastName.getText(), registerPhoneNo.getText(), address, userToCreate.getDefaultAvatar());
    		CONSTANTS.makeAlertWindow("success", "Your account has been registered.");
    		closeWindow();
        } 
    }

    public void backLoginButtonClicked(){
        closeWindow();
    }

    public void picEditButtonClicked(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/DefaultAvatar.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            defautAvatarController controller = fxmlLoader.getController();
            controller.initialize(userToCreate);

            Scene newScene = new Scene(editRoot);
            Stage editStagee = new Stage();
            editStagee.setScene(newScene);
            editStagee.setTitle("Artatawe |Default Profile Images");

            editStagee.initModality(Modality.APPLICATION_MODAL);

            editStagee.showAndWait();
            
            initialize(userToCreate);
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
