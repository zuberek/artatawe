package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent a sculpture
 * @author Bryn Waterhouse
 * @author Joshua Blackman
 */
public class Sculpture extends Artwork {
    private double depth;
    private String material;

    /**
     * Set a default picture as a placeholder
     */
    public Sculpture() {
        super.setPhotographPath("../Pictures/Painting.jpg");
    }

    /**
     * Constructor for instantiation of the sculpture object
     * @param userID User ID of the seller
     * @param title Title of the sculpture
     * @param artist The sculptor's name
     * @param description A short description of the sculpture
     * @param photographPath the filepath for a picture of the sculpture
     * @param dateCreated the year the sculpture was created
     * @param height The height of the sculpture
     * @param width The width of the sculpture
     * @param depth The depth of the sculpture
     * @param material The material the sculpture is made from (marble, stone, etc.)
     */
    public Sculpture(int userID, String title, String artist, String description, String photographPath, String dateCreated, double height, double width, double depth, String material) {
        setUserID(userID);
    	setTitle(title);
        setArtist(artist);
        setDescription(description);
        setPhotographPath(photographPath);
        setDateCreated(dateCreated);
        setDimensions(height, width, depth);
        setMaterial(material);
        saveSculpture();
    }

    /**
     * Creates an Sculpture object from the database
     * @param artworkID The artwork ID of the sculpture
     */
    public Sculpture(int artworkID) {
    	try{
			ResultSet rs = DB.select("SELECT * FROM `artworks` WHERE artworkID = '" + artworkID + "'");
			while (rs.next()) {
				this.setArtworkID(rs.getInt("artworkID"));
				this.setUserID(rs.getInt("userID"));
				this.setArtist(rs.getString("artist"));
				this.setTitle(rs.getString("title"));
				this.setDescription(rs.getString("description"));
				this.setDimensions(rs.getDouble("height"), rs.getDouble("width"), rs.getDouble("depth"));
				this.setMaterial((rs.getString("material")));
				this.setPhotographPath(rs.getString("photographPath"));
				this.setDateCreated(rs.getString("dateCreated"));
	        }
		} catch(SQLException ex){
			ex.getMessage();
		}
    }
    
    private void saveSculpture() {
    	DB.query("INSERT INTO `artworks` (`userID`, `type`, `artist`, `title`, `material`, `width`, `height`, `depth`, `photographPath`, `dateCreated`, `description`) VALUES ('"+ this.getUserID() + "',  'sculpture', '" + this.getArtist() + "', '"  + this.getTitle() + "', '" + this.getMaterial() + "', '" + this.getDimensions()[1] + "', '" + this.getDimensions()[0] + "', '" + this.getDimensions()[2] + "', '" + this.getPhotographPath() + "', '" + this.getDateCreated() + "', '" + this.getDescription() + "');");
    }

    /**
     * Returns an array of all the sculpture dimensions (x,y,z)
     * @return a list of the full 3D sculpture dimensions
     */
    public double[] getDimensions() {
        return new double[] {height, width, depth};
    }

    /**
     * Sets the dimensions of the sculpture
     * @param height height of the sculpture
     * @param width width of the sculpture
     * @param depth depth of the sculpture
     */
    public void setDimensions(double height, double width, double depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    /**
     * Return the material the sculpture is crafted from
     * @return the sculpture material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material of the sculpture
     * @param material the sculpture material to be set
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}
