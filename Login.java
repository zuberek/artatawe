import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * This the main class, first  point of contact for the user.
 * It is used to open u
 * @author  Joshua Blackman
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
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    
        Scene scene = new Scene(root);

        stage.setTitle("Artatawe | Login");
        stage.setScene(scene);
        stage.show();		
	}

}
