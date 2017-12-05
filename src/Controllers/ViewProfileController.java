package src.Controllers;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.User;

public class ViewProfileController{

	@FXML Label userLabel;
	@FXML ImageView userImage;
	@FXML Button favouriteButton;
	
	private boolean favourited;
	
	User currentUser;
	User userToView;
	
	public ViewProfileController() {
		
	}
	
	public void initialize(User userToView, User currentUser) {
		this.currentUser = currentUser;
		this.userToView = userToView;
		userLabel.setText(userToView.getUserName() + "'s Profile");
		if(currentUser.isFavourite(userToView.getUserID())) {
			favouriteButton.setText("Unfavourite");
			favourited = true;
		} else  {
			favourited  = false;
		}
		InputStream stream = getClass().getResourceAsStream(userToView.getDefaultAvatar());
		Image newImage = new Image(stream);
		userImage.setImage(newImage);
	}
	

	public void favouriteUser() {
		if(favourited) {
			currentUser.unfavouriteUser(userToView.getUserID());
			favouriteButton.setText("Favourite");
		}  else {
			currentUser.favouriteUser(userToView.getUserID());
			favouriteButton.setText("Unfavourite");
		}
		
		
	}
}