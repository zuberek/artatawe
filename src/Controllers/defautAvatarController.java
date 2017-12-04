package src.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class defautAvatarController {

    @FXML Pane rootPane;
    RegisterController controller;

    public void initialize(RegisterController controller){
        this.controller = controller;
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

    private void finishPicking(String imagePath){
        controller.setCurrentProfileImagePath(imagePath);
        closeWindow();
    }

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
