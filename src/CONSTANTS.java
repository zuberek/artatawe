package src;

import javafx.scene.control.Alert;

public class CONSTANTS {
    public static int BID_INCREASE = 5;
    //login
    public static final int SMALL_WINDOW_WIDTH = 500;
    public static final int SMALL_WINDOW_HEIGHT = 300;
    //register
	public static final int MEDIUM_WINDOW_WIDTH = 600;
	public static final int MEDIUM_WINDOW_HEIGHT = 400;
	//menu
	public static final int BIG_WINDOW_WIDTH = 800;
	public static final int BIG_WINDOW_HEIGHT = 600;
    
    public static void makeAlertWindow(String type, String content){
    	Alert alert;
    	switch(type) {
    	case "warning":
	    	alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Error");
	        alert.setContentText(content);
	        alert.showAndWait();
	        break;
    	case "success":
	    	alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Success");
	        alert.setContentText(content);
	        alert.showAndWait();
	        break;
    	}
    }
    
<<<<<<< HEAD
    public static boolean isNumerical(String str) {
    	return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
=======
    public static boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
>>>>>>> branch 'master' of https://github.com/Jasiudab/software-assignment-.git
}
