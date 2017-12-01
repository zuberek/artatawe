package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class takes input from the user and creates new profile.
 * Also allows to pick default avatar or go to drawing tool
 * @author Jan Dabrowski
 *
 */

public class Register extends Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/Register.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Artatawe | Register");
        stage.setScene(scene);
        stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.out.println("You broke here");
            System.exit(-1);
        }
    }
}
