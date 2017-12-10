package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent and abstractly implement basic details shared by all artworks
 * @author Bryn Waterhouse
 * @author Joshua Blackman
 */

public abstract class Artwork {
	private int artworkID;
    private String title;
    private String artist;
    private String description;
    private String photographPath;
    private String dateCreated;
    private int UserID;

	protected double height;
    protected double width;

    /**
     *
     * @param artworkID The ID of the artwork
     * @return Empty string if no artworks with the given ID exist, otherwise the artwork type
     */
	static String checkType(int artworkID) {
		ResultSet rs = DB.select("SELECT * FROM `artworks` WHERE artworkID = '" + artworkID + "'");
		try {
			if(rs.next()) {
				return rs.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }

    /**
     * Get the artwork ID
	 * @return the artwork ID
	 */
	public int getArtworkID() {
		return artworkID;
	}

	/**
     * Set the artwork ID
	 * @param artworkID the artwork ID to set
	 */
	public void setArtworkID(int artworkID) {
		this.artworkID = artworkID;
	}

	/**
     * Get the user ID
	 * @return the user ID
	 */
	public int getUserID() {
		return UserID;
	}

	/**
     * Set the user ID
	 * @param userID the user ID to set
	 */
	public void setUserID(int userID) {
		UserID = userID;
	}
    
    /**
     * Get the artwork's title
     * @return the title of the artwork
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the artwork's title
     * @param title the title of the artwork to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the artist's name
     * @return the artist's name
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the artist's name
     * @param artist the artist's name to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Get the artwork description
     * @return the artwork description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the artwork description
     * @param description the artwork description to get
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the artwork photograph filepath
     * @return the filepath of a photograph of the artwork
     */
    public String getPhotographPath() {
        return photographPath;
    }

    /**
     * Set the filepath for the photograph
     * @param photographPath the filepath to be set of a photograph of the artwork
     */
    public void setPhotographPath(String photographPath) {
        this.photographPath = photographPath;
    }

    /**
     * Get the date of creation for the artwork
     * @return the creation date of the artwork
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the date of creation for the artwork
     * @param dateCreated the creation date to be set for the artwork
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Get the artwork's dimensions
     * @return an array of the artwork's dimensions
     */
    public double[] getDimensions() {
        return new double[] {height, width};
    }

    /**
     * Set dimensions of the artwork
     * @param height the height of the artwork
     * @param width the width of the artwork
     */
    public void setDimensions(double height, double width) {
        this.height = height;
        this.width = width;
    }
}
