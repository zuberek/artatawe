import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the Edit window.
 * This controller requires the method setCountryForEditing to be called to set the country object that is being edited.
 * @author Liam O'Reilly
 */
public class EditCountryController {
	@FXML Button cancelButton;
	@FXML Button confirmButton;
	@FXML TextField nameTextField;
	@FXML TextField capitalTextField;
	@FXML TextField populationTextField;
	@FXML Pane rootPane;
	
	// The country being edited.
	Country countryBeingEdited;

	/**
	 * Initialize the controller.
	 * This method is called automatically. The following happen in this order:
	 * 1) First an instance of the controller is created (the constructor is called),
	 * 2) Next the @FXML variables are bound to the GUI components.
	 * 3) Finally, this initialize method is called.
	 * This means we cannot bind actions to buttons in the constructor, but we can in this method.
	 */
	public void initialize() {
		// Setup the me
		confirmButton.setOnAction(e -> {
			handleConfirmButtonAction();
		});
		
		cancelButton.setOnAction(e -> {
			handleCancelButtonAction();
		});
	}
	
	/**
	 * Set the country that is being edited.
	 * When this window is closed, the changes will be set in this country object.
	 * @param The country to be edited.
	 */
	public void setCountryForEditing(Country countryToEdit) {
		// Keep a reference to the country that we are editing.
		this.countryBeingEdited = countryToEdit;
		
		// Update the GUI to show the existing data.
		nameTextField.setText(countryBeingEdited.getName());
		capitalTextField.setText(countryBeingEdited.getCapital());
		populationTextField.setText(Double.toString(countryBeingEdited.getPopulation()));
	}
	
	/**
	 * Handle the cancel button.
	 * Cancel the edit and close the window.
	 */
	private void handleCancelButtonAction() {
		// We just need to close the window.
		// As long as we don't edit the country then no changes will have taken place.
		Stage stage = (Stage) rootPane.getScene().getWindow();
	    stage.close();
	}

	/**
	 * Handle the confirm button.
	 * Save the changes and close the window.
	 */
	private void handleConfirmButtonAction() {
		countryBeingEdited.setName(nameTextField.getText());
		countryBeingEdited.setCapital(capitalTextField.getText());
		// Warning, we should really validate the input here.
		// The program will crash if the user does not type a double.
		double pop = Double.parseDouble(populationTextField.getText());
		countryBeingEdited.setPopulation(pop);
		
		// The country has now been updated. All that is left to do is close the window.
		closeWindow();
	}
	
	/**
	 * Close the window.
	 */
	private void closeWindow() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
	    stage.close();
	}
}