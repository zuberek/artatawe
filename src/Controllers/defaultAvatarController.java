package src.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.User;

/**
 * Controller used to set a default avatar from a predefined list
 */
public class defaultAvatarController {

    @FXML Pane rootPane;
    
    User user;

    public void initialize(User user){
        this.user = user;
    }

    public void profileImage1Clicked(){
        finishPicking("../Pictures/avatar1.png");
    }

    public void profileImage2Clicked(){
        finishPicking("../Pictures/avatar2.png");
    }

    public void profileImage3Clicked(){
        finishPicking("../Pictures/avatar3.png");
    }

    public void profileImage4Clicked(){
        finishPicking("../Pictures/avatar4.png");
    }

    public void profileImage5Clicked(){
        finishPicking("../Pictures/avatar5.png");
    }

    /**
     * Set chosen image as profile image
     * @param imagePath chosen image path
     */
    private void finishPicking(String imagePath){
    	user.setDefaultAvatar(imagePath);
    	
        closeWindow();
    }

    /**
     * Listener for cancel button, with window close.
     */
    public void cancelButtonClicked(){
        closeWindow();
    }

    /**
     * Close the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
