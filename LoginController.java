import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML TextField loginUserName;
	
	public void loginButtonClicked() {
		if(loginUserName.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setContentText("Please fill in the username field.");
			alert.showAndWait();
		} else {
			System.out.println(loginUserName.getText());
			//User user  = new User();
		}
		
	}
}
