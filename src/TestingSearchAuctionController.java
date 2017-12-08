package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Controllers.ProfileController;
import src.Controllers.SearchAuctionController;
import src.Controllers.ViewAuctionController;

import java.io.IOException;

/**
 * This the main class, first  point of contact for the user.
 * It asks for user log in or links to registration scene.
 * @author  Joshua Blackman 
 * @author Jan Dabrowski
 *
 */
public class TestingSearchAuctionController extends Application {

	// Constants for the main window
	public static final int MAIN_WINDOW_WIDTH = 700;
	public static final int MAIN_WINDOW_HEIGHT = 400;
	private static final String WINDOW_TITLE = "Artatawe | Login";

	public static void main(String[] args) {
		DB db = new DB();
        launch(args);
    }

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/SearchAuction.fxml"));
           BorderPane editRoot = (BorderPane) fxmlLoader.load();
           
           SearchAuctionController searchAuctionController = fxmlLoader.getController();
           searchAuctionController.initialize();
           
       
		
		Scene newScene = new Scene(editRoot);
		Stage editStagee = new Stage();
		editStagee.setScene(newScene);
		editStagee.setTitle("Artatawe |Default Profile Images");

		editStagee.initModality(Modality.APPLICATION_MODAL);

		editStagee.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}

}
