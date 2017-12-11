package src.Controllers;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.CONSTANTS;
import src.User;

/**
 * @author Joshua Blackman
 * Profile viewing controller, allowing favouriting and unfavouriting
 * users so that their auctions are also displayed on your dashboard
 */
public class ViewProfileController{

	
	@FXML BorderPane rootPane;
	@FXML Label userLabel;
	@FXML ImageView profileImage;
	@FXML Button favouriteButton;
	
	private boolean favourited;
	
	private User currentUser;
	private User userToView;

	/**
	 * Construction for instantiation
	 */
	public ViewProfileController() {
		
	}

	/**
	 * Initialise display for viewing/favouriting a specific user,
	 * retrieving user profile image and details
	 * @param userToView User to be viewed
	 * @param currentUser current logged-in user
	 */
	public void initialize(User userToView, User currentUser) {
		this.currentUser = currentUser;
		this.userToView = userToView;
		userLabel.setText(userToView.getUserName() + "'s Profile");
		if(currentUser.isFavourite(userToView.getUserID())) {
			favouriteButton.setText("Unfavourite");
			favourited = true;
		} else  {
			favouriteButton.setText("Favourite");
			favourited  = false;
		}
		InputStream stream = getClass().getResourceAsStream(userToView.getAvatarPath());
		Image newImage = new Image(stream);
		profileImage.setImage(newImage);
	}

	/**
	 * Event handler for favouriting and unfavouriting user
	 */
	public void favouriteUser() {
		if(favourited) {
			currentUser.unfavouriteUser(userToView.getUserID());
			favouriteButton.setText("Favourite");
			favourited = false;
		}  else if(currentUser.getUserID() != userToView.getUserID()){
			currentUser.favouriteUser(userToView.getUserID());
			favouriteButton.setText("Unfavourite");
			favourited = true;
		} else {
			CONSTANTS.makeAlertWindow("warning","You can not favorite yourself!");
		}
	}

	/**
	 * Close the profile view window
	 */
	public void close() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
	}
}
