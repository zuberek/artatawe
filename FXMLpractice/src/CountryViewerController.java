import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Controller for the main country viewer window.
 * @author Liam O'Reilly
 *
 */
public class CountryViewerController {
	@FXML ListView<String> countryList;
	@FXML Button editButton;
	@FXML Button deleteButton;
	
	// The main list that will store all our countries.
	private ArrayList<Country> countries = new ArrayList<Country>();
	
	/**
	 * Initialize the controller.
	 * This method is called automatically. The following happen in this order:
	 * 1) First an instance of the controller is created (the constructor is called),
	 * 2) Next the @FXML variables are bound to the GUI components.
	 * 3) Finally, this initialize method is called.
	 * This means we cannot bind actions to buttons in the constructor, but we can in this method.
	 */
	public void initialize() {
		// Create some initial countries
		countries.add(new Country("Austria", "Vienna", 8.4));
		countries.add(new Country("Belgium", "Brussels", 11));
		countries.add(new Country("Bulgaria", "Sofia", 7));
		countries.add(new Country("Croatia", "Zagreb", 4.3));
		countries.add(new Country("Cyprus", "Nicosia", 0.8));
		countries.add(new Country("Czech Republic", "Prague", 10));
		countries.add(new Country("Denmark", "Copenhagen", 5.6));
		countries.add(new Country("Estonia", "Tallinn", 1.3));
		countries.add(new Country("Finland", "Helsinki", 5.4));
		countries.add(new Country("France", "Paris", 65));
		countries.add(new Country("Germany", "Berlin", 82.5));
		countries.add(new Country("Greece", "Athens", 11));
		countries.add(new Country("Hungary", "Budapest", 10));
		countries.add(new Country("Iceland", "Reykjavik", 0.32));
		countries.add(new Country("Ireland", "Dublin", 4.6));
		countries.add(new Country("Italy", "Rome", 60.9));
		countries.add(new Country("Latvia", "Riga", 2));
		countries.add(new Country("Lichtenstein", "Vaduz", 0.363));
		countries.add(new Country("Lithuania", "Vilnius", 3));
		countries.add(new Country("Luxembourg", "Luxembourg", 0.524));
		countries.add(new Country("Malta", "Valletta", 0.45));
		countries.add(new Country("Netherlands", "Amsterdam", 16.8));
		countries.add(new Country("Norway", "Oslo", 5));
		countries.add(new Country("Poland", "Warsaw", 38.5));
		countries.add(new Country("Portugal", "Lisbon", 10.5));
		countries.add(new Country("Romania", "Bucharest", 19.1));
		countries.add(new Country("Slovakia", "Bratislava", 5.4));
		countries.add(new Country("Slovenia", "Ljubljana", 2));
		countries.add(new Country("Spain", "Madrid", 47));
		countries.add(new Country("Sweden", "Stockholm", 9.5));
		countries.add(new Country("Switzerland", "Bern", 8));
		countries.add(new Country("United Kingdom", "London", 63.2));
		
		// Setup actions on buttons
		deleteButton.setOnAction(e -> {
			handleDeleteButtonAction();
		});
		
		editButton.setOnAction(e -> {
			handleEditButtonAction();
		});
		
		// Display the countries
		refreshCountryList();
	}
	
	/**
	 * Refresh the displayed countries.
	 */
	private void refreshCountryList() {
		// Clear the displayed list
		countryList.getItems().clear();
		
		// Add each country to the displayed list
		for (Country c : countries) {
			countryList.getItems().add(c.getDescriptionForList());
		}
	}
	
	/**
	 * Handle the behavior of the delete button.
	 * This will delete the selected country and refresh the displayed list.
	 */
	private void handleDeleteButtonAction() {
		// Get the index of the selected item in the displayed list
		int selectedIndex = countryList.getSelectionModel().getSelectedIndex();
		
		// Check if user selected an item
		if (selectedIndex < 0) {
			// Show a message
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error: Cannot delete country");
			alert.setHeaderText(null);
			alert.setContentText("Cannot delete country as no country is selected. Please select a country first.");
			alert.showAndWait();
		} else {
			// Remove the country at the selected index.
			countries.remove(selectedIndex);
		}
		
		// We might have deleted a country - so we must refresh the displayed list
		refreshCountryList();
	}

	/**
	 * Handle the edit button.
	 * This will display a window allowing the user to edit the selected country.
	 * After the edit is complete, the displayed list will be updated.
	 */
	private void handleEditButtonAction() {
		// Get the index of the selected item in the displayed list
		int selectedIndex = countryList.getSelectionModel().getSelectedIndex();
		
		// Check if user selected an item
		if (selectedIndex < 0) {
			// Show a message
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error: Cannot edit country");
			alert.setHeaderText(null);
			alert.setContentText("Cannot edit country as no country is selected. Please select a country first.");
			alert.showAndWait();
			return;
		}
		
		// Can only get to this line if user has selected a country
		Country selectedCountry = countries.get(selectedIndex);

		// We need to use a try-catch block as the loading of the FXML file can fail.
		try {
			// Create a FXML loader for loading the Edit Country FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCountry.fxml"));     

			// Run the loader
			BorderPane editRoot = (BorderPane)fxmlLoader.load();          
			// Access the controller that was created by the FXML loader
			EditCountryController editController = fxmlLoader.<EditCountryController>getController();
			
			//*************
			//* Important *
			//*************
			// Tell the controller which country we are editing.
			// Remember we are passing arrows (i.e., references) around.
			// This means that the edit controller's changes to the country will be reflected here (in our list).
			editController.setCountryForEditing(selectedCountry);
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle(Main.EDIT_WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			editStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editStage.showAndWait();
			
			// The above method only returns when the window is closed.
			
			// Any changes that the edit scene made to the country will have taken place to the country object.
			// This object is part of our country list.
			// So we just need to refresh the JavaFX ListView.
			refreshCountryList();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}
}