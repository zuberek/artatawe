package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * This the main class, first point of contact for the user.
 * It asks for user log in and links to registration scene.
 * @author Joshua Blackman
 * @author Jan Dabrowski
 *
 */
public class Main extends Application {

	// Constants for the main window
	private static final String WINDOW_TITLE = "Artatawe | Login";

	/**
	 * Creates a database and launches the application
	 */
	public static void main(String[] args) {
		new DB();
        launch(args);
    }

	//see javafx.application.Application#start(javafx.stage.Stage)
	/**
	 * Starts the entire FXML stage for the login page
	 * @param stage The stage on which the Login page is set
	 * @throws Exception error when FXML fails to load
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("Scenes/Login.fxml"));

		Scene scene = new Scene(root);

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