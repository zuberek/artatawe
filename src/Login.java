package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * This the main class, first  point of contact for the user.
 * It asks for user log in or links to registration scene.
 * @author  Joshua Blackman and Jan Dabrowski
 *
 */
public class Login extends Application {
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
		Parent root = FXMLLoader.load(getClass().getResource("Scenes/Login.fxml"));

		Scene scene = new Scene(root);

		stage.setTitle("Artatawe | Login");
		stage.setScene(scene);
		stage.show();

		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}

}
