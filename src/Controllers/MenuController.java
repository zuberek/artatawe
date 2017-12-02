package src.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Login;
import src.User;

import java.io.IOException;


public class MenuController {

    @FXML TextField currentUserTextField;

    User currentUser;

    /**
     * Set the current user
     * @param The user in the current session
     */
    public void setCurrentUser(User userToSet) {
        // Keep a reference to the user that we are editing.
        this.currentUser = userToSet;

        // Update the GUI to show the existing data.
        currentUserTextField.setText(currentUser.getUserName());
    }

    public void editProfileButonClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Profile.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            ProfileController profileController = fxmlLoader.getController();
            profileController.setUserForEditing(currentUser);

            Scene newScene = new Scene(editRoot, Login.MAIN_WINDOW_WIDTH, Login.MAIN_WINDOW_HEIGHT);
            Stage editStagee = new Stage();
            editStagee.setScene(newScene);
            editStagee.setTitle("Artatawe | Edit User");

            editStagee.initModality(Modality.APPLICATION_MODAL);

            editStagee.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }
}
