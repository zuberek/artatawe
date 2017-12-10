package src.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Auction;
import src.AuctionList;
import src.Bid;
import src.BidList;
import src.Login;
import src.User;
import src.UserList;

public class DashboardController {
	// TODO: Javadoc
	
	@FXML Button editProfileButton;
	@FXML Button sellArtworkButton;
	@FXML Button bidHistoryButton;
	@FXML Button auctionHistoryButton;
	@FXML Button logoutButton;
	@FXML Label welcomeLabel;
	@FXML ListView<String> browseAuctions;
	
	@FXML Label pageNumberLabel;
	
	@FXML Label auctionLabel1;
	@FXML Label auctionLabel2;
	@FXML Label auctionLabel3;
	@FXML Label auctionLabel4;
	
	@FXML ImageView auctionImage1;
	@FXML ImageView auctionImage2;
	@FXML ImageView auctionImage3;
	@FXML ImageView auctionImage4;
	
	@FXML TextArea auctionDesc1;
	@FXML TextArea auctionDesc2;
	@FXML TextArea auctionDesc3;
	@FXML TextArea auctionDesc4;
	
	@FXML Button auctionButton1;
	@FXML Button auctionButton2;
	@FXML Button auctionButton3;
	@FXML Button auctionButton4;
	
	@FXML GridPane gridPane;
	
	User currentUser;
	ArrayList<Auction> auctions;
	ArrayList<User> favourites;
	int counter = 0;
	
	public void initialize(User currentUser){
		this.currentUser = currentUser;
		welcomeLabel.setText("Welcome " + currentUser.getFirstName());
		
		
		favourites = UserList.getFavouriteUsers(currentUser.getUserID());
		
		auctions = AuctionList.getUsersAuctions(favourites);
		
		this.dynamicFavoritesGridPane(gridPane, favourites);
		
		if((int)Math.ceil(auctions.size()/4) == 0){
			pageNumberLabel.setText("1/1");
		} else   {
			pageNumberLabel.setText("1/" + String.valueOf((int)Math.ceil(auctions.size()/4)));
		}
		
		populateAuctions();
	}
	
	private void dynamicFavoritesGridPane(GridPane gridPane, ArrayList<User> favorites) {
		final int IMAGE_COLUMN = 0;
		final int PROFILE_COLUMN = 1;
		final int FAVORITES_PROFILE_IMAGE = 20;
		int row = 0;
		Hyperlink favoriteUser;
		ImageView profileImage;
		gridPane.addRow(favorites.size());
		for (User elem : favorites) {
			profileImage = new ImageView();
			profileImage.setFitHeight(FAVORITES_PROFILE_IMAGE);
			profileImage.setFitWidth(FAVORITES_PROFILE_IMAGE);
			try {
		        InputStream stream = getClass().getResourceAsStream(elem.getDefaultAvatar());
		        Image newImage = new Image(stream);
		        profileImage.setImage(newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			favoriteUser = new Hyperlink();
			favoriteUser.setText(elem.getUserName());
			favoriteUser.setOnAction(event -> {
		        try {
		        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewProfile.fxml"));
		            Parent editRoot = (Parent) fxmlLoader.load();

		            ViewProfileController vcp = fxmlLoader.getController();
		            vcp.initialize(elem, currentUser);

		            Scene newScene = new Scene(editRoot);
		            Stage editStagee = new Stage();
		            editStagee.setScene(newScene);
		            editStagee.setTitle("Artatawe | View Profile");

		            editStagee.initModality(Modality.APPLICATION_MODAL);

		            editStagee.showAndWait();
		        } catch (IOException e) {
		            e.printStackTrace();
		            // Quit the program (with an error code)
		            System.exit(-1);
		        }
			});
			gridPane.add(favoriteUser, PROFILE_COLUMN, row);
			gridPane.add(profileImage, IMAGE_COLUMN, row);
			row++;
		}
	}
	
	public void populateAuctions() {
		if(!auctions.isEmpty()) {
			for(int i = 0; i <= 3;i++) {
				int auctionCounter =  i + counter;
				if(auctionCounter < auctions.size()) {
					switch(i) {
						case 0:
							auctionLabel1.setText(auctions.get(auctionCounter).getArtwork().getTitle());
							auctionDesc1.setText(auctions.get(auctionCounter).getArtwork().getDescription());
							InputStream stream = getClass().getResourceAsStream(auctions.get(auctionCounter).getArtwork().getPhotographPath());
							Image newImage = new Image(stream);
							auctionImage1.setImage(newImage);
							break;
						case 1:
							auctionLabel2.setText(auctions.get(auctionCounter).getArtwork().getTitle());
							auctionDesc2.setText(auctions.get(auctionCounter).getArtwork().getDescription());
							InputStream stream2 = getClass().getResourceAsStream(auctions.get(auctionCounter).getArtwork().getPhotographPath());
							Image newImage2 = new Image(stream2);
							auctionImage2.setImage(newImage2);
							break;
						case  2:
							auctionLabel3.setText(auctions.get(auctionCounter).getArtwork().getTitle());
							auctionDesc3.setText(auctions.get(auctionCounter).getArtwork().getDescription());
							InputStream stream3 = getClass().getResourceAsStream(auctions.get(auctionCounter).getArtwork().getPhotographPath());
							Image newImage3 = new Image(stream3);
							auctionImage3.setImage(newImage3);
							break;
						case 3:
							auctionLabel4.setText(auctions.get(auctionCounter).getArtwork().getTitle());
							auctionDesc4.setText(auctions.get(auctionCounter).getArtwork().getDescription());
							InputStream stream4 = getClass().getResourceAsStream(auctions.get(auctionCounter).getArtwork().getPhotographPath());
							Image newImage4 = new Image(stream4);
							auctionImage4.setImage(newImage4);
							break;
					}
				}  else {
					auctionVisbility(i);
				}
			}
		} else {
			auctionVisbility(-1);
		}
	}
	
	public void auctionButton1Clicked() {
		int auctionIndex = 0 + counter;
		openAuction(auctions.get(auctionIndex));
	}
	
	public void auctionButton2Clicked() {
		int auctionIndex = 1 + counter;
		openAuction(auctions.get(auctionIndex));
	}
	
	public void auctionButton3Clicked() {
		int auctionIndex = 2 + counter;
		openAuction(auctions.get(auctionIndex));
	}
	
	public void auctionButton4Clicked() {
		int auctionIndex = 3 + counter;
		openAuction(auctions.get(auctionIndex));
	}
	
	public void openAuction(Auction auction) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser, auction);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | View Auction");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void auctionVisbility(int i) {
		switch(i) {
			case 0:
				auctionLabel1.setVisible(false);
				auctionDesc1.setVisible(false);
				auctionImage1.setVisible(false);
				auctionButton1.setVisible(false);
				break;
			case 1:
				auctionLabel2.setVisible(false);
				auctionDesc2.setVisible(false);
				auctionImage2.setVisible(false);
				auctionButton2.setVisible(false);
				break;
			case 2:
				auctionLabel3.setVisible(false);
				auctionDesc3.setVisible(false);
				auctionImage3.setVisible(false);
				auctionButton3.setVisible(false);
				break;
			case 3:
				auctionLabel4.setVisible(false);
				auctionDesc4.setVisible(false);
				auctionImage4.setVisible(false);
				auctionButton4.setVisible(false);
				break;
			default:
				auctionLabel1.setVisible(false);
				auctionDesc1.setVisible(false);
				auctionImage1.setVisible(false);
				auctionButton1.setVisible(false);
				auctionLabel2.setVisible(false);
				auctionDesc2.setVisible(false);
				auctionImage2.setVisible(false);
				auctionButton2.setVisible(false);
				auctionLabel3.setVisible(false);
				auctionDesc3.setVisible(false);
				auctionImage3.setVisible(false);
				auctionButton3.setVisible(false);
				auctionLabel4.setVisible(false);
				auctionDesc4.setVisible(false);
				auctionImage4.setVisible(false);
				auctionButton4.setVisible(false);
				break;
			
		}
	}
	
	public void wonArtworks(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewWonHistory.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewWonHistoryController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  Your Won History");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void soldArtworkHistory(){
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewSoldHistory.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewSoldHistoryController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  Your Sold History");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void browseAuctionsClicked(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/SearchAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
			
			SearchAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(currentUser);
		
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | Auction Search");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            this.initialize(currentUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public void bidHistoryClicked(){
		ArrayList<Bid> bidList = new ArrayList<>();
		BidList bl = new BidList();
		bidList = bl.getUserBidList(currentUser.getUserID());
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/ViewBidHistory.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			ViewBidHistoryController ctrl = fxmlLoader.getController();
			ctrl.initialize(bidList);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe |  Your Bid History");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void editProfileClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/Profile.fxml"));
            BorderPane editRoot = (BorderPane) fxmlLoader.load();

            ProfileController profileController = fxmlLoader.getController();
            profileController.initialize(currentUser);

            Scene newScene = new Scene(editRoot);
            Stage editStagee = new Stage();
            editStagee.setScene(newScene);
            editStagee.setTitle("Artatawe | Edit User");

            editStagee.initModality(Modality.APPLICATION_MODAL);

            editStagee.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
	}
	
	public void logoutClicked() {
        try {
        	currentUser.saveUserLogout();
            Stage stage = (Stage)editProfileButton.getScene().getWindow();

            Login login = new Login();
            login.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void sellArtworkClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Scenes/AddAuction.fxml"));
			Parent editRoot = (Parent) fxmlLoader.load();
	
			AddAuctionController ctrl = fxmlLoader.getController();
			ctrl.initialize(this.currentUser);
	
			Scene newScene = new Scene(editRoot);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.setTitle("Artatawe | Add new Auction");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            this.initialize(currentUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
