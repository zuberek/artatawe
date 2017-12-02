package src.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewProfile extends Application {
    // Constants for the main window
    private static final int MAIN_WINDOW_WIDTH = 600;
    private static final int MAIN_WINDOW_HEIGHT = 400;
    private static final String WINDOW_TITLE = "\"Artatawe | Profile\"";

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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Scenes/Profile.fxml"));

            Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

            stage.setTitle(WINDOW_TITLE);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }
}
