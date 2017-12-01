package src.Controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterTest extends Application {
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
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/Register.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Artatawe | src.RegisterTest");
        stage.setScene(scene);
        stage.show();
    }

}


