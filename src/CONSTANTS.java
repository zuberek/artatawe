package src;

import javafx.scene.control.Alert;

/**
 * @author Joshua Blackman
 * @author Jan Dabrowski
 * @author Borislav Koynin
 */
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

	//for auction validation
	// You cannot add an artwork that was created after it
	public static final int MAX_YEAR = 2017;
	//you cannot add an artwork that was created before it
	public static final int SMALLEST_YEAR = 1500;
	//you cannot add an artwork with size parameter above it
	public static final int MAX_SIZE = 2000;
	//you cannot add an artwork with size parameter under it
	public static final int MIN_SIZE = 0;
	//you cannot add an auction with maxBid above it
	public static final int MAX_BID = 50;
	//you cannot add an auction with maxBid under it
	public static final int MIN_BID = 1;
	//you cannot add an auction with reserved price above it
	public static final int MAX_PRICE = 1000;
	//you cannot add an auction with reserved price under it
	public static final int MIN_PRICE = 1;

	/**
	 * Creates a popup alert window
	 * @param type The type of alert window to be displayed
	 * @param content The text written in the alert window
	 */
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

	/**
	 * Checks to see if the given string is entirely numeric
	 * @param str Given string to check
	 * @return boolean of if the string is numeric
	 */
	public static boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

	/**
	 * Checks to see if the given string is entirely letters
	 * @param name Given string to check
	 * @return boolean of if the string is solely letters
	 */
	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}

}
