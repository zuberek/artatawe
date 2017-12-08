package src;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Painting extends Artwork {
    private String paintingType;

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
    
    public Painting(int artworkID) {
    	try{
			ResultSet rs = DB.select("SELECT * FROM `artworks` WHERE artworkID = '" + artworkID + "'");
			while (rs.next()) {
				super.setArtworkID(rs.getInt("artworkID"));
				this.setUserID(rs.getInt("userID"));
				this.setArtist(rs.getString("artist"));
				this.setTitle(rs.getString("title"));
				this.setDimensions(rs.getDouble("height"), rs.getDouble("width"));
				this.setPaintingType(rs.getString("paintingType"));
				this.setPhotographPath(rs.getString("photographPath"));
				this.setDateCreated(rs.getString("dateCreated"));
	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
    }
    
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
