package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent a painting
 * @author Bryn Waterhouse
 * @author Joshua Blackman
 */
public class Painting extends Artwork {
    private String paintingType;

    /**
     * Constructor for instantiation of the painting object
     * @param userID User ID of the seller
     * @param title Title of the painting
     * @param artist The painting's artist
     * @param description A short description of the painting
     * @param photographPath the filepath for a picture of the painting
     * @param dateCreated the year the painting was created
     * @param height The height of the painting
     * @param width The width of the painting
     * @param paintingType The type of painting
     */
    public Painting(int userID, String title, String artist, String description, String photographPath, String dateCreated, double height, double width, String paintingType) {
        setUserID(userID);
    	setTitle(title);
        setArtist(artist);
        setDescription(description);
        setPhotographPath(photographPath);
        setDateCreated(dateCreated);
        setDimensions(height, width);
        setPaintingType(paintingType);
        savePainting();
    }

    /**
     * Creates an Painting object from the database
     * @param artworkID The artwork ID of the painting
     */
    public Painting(int artworkID) {
    	try{
			ResultSet rs = DB.select("SELECT * FROM `artworks` WHERE artworkID = '" + artworkID + "'");
			while (rs.next()) {
				super.setArtworkID(rs.getInt("artworkID"));
				this.setUserID(rs.getInt("userID"));
				this.setArtist(rs.getString("artist"));
				this.setTitle(rs.getString("title"));
				this.setDescription(rs.getString("description"));
				this.setDimensions(rs.getDouble("height"), rs.getDouble("width"));
				this.setPaintingType(rs.getString("paintingType"));
				this.setPhotographPath(rs.getString("photographPath"));
				this.setDateCreated(rs.getString("dateCreated"));
	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
    }

    /**
     * Saves the painting to the database
     */
    public void savePainting() {
    	DB.query("INSERT INTO `artworks` (`userID`, `type`, `artist`, `title`, `width`, `height`, `paintingType`, `photographPath`, `dateCreated`, `description`) VALUES ('"+ this.getUserID() + "',  'painting', '" + this.getArtist() + "', '" + this.getTitle() + "', '" + this.getDimensions()[1] + "', '" + this.getDimensions()[0] + "', '" + this.getPaintingType() + "', '" + this.getPhotographPath() + "', '" + this.getDateCreated() + "', '" + this.getDescription() + "');");
    }

    /**
     * Returns the type of painting, e.g. oil on canvas, pastel, etc.
     * @return the painting type
     */
    public String getPaintingType() {
        return paintingType;
    }

    /**
     * Sets the type of painting being auctioned
     * @param paintingType the type of painting to be set
     */
    public void setPaintingType(String paintingType) {
        this.paintingType = paintingType;
    }
}
