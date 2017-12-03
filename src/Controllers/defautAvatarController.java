package src.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class defautAvatarController {

    @FXML Pane rootPane;

    public void profileImageClicked(){

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
