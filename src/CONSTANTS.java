package src;

import javafx.scene.control.Alert;

public class CONSTANTS {
    public static int BID_INCREASE = 5;
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    
    public static void makeAlertWindow(String content){
    	Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
