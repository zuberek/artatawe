package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bryn Waterhouse
 * @author Joshua Blackman
 */

public abstract class Artwork {
    private String title;
    private String artist;
    private String description;
    private String photographPath;
    private String dateCreated;
    private int UserID;

	protected double height;
    protected double width;

	public static String checkType(int artworkID) {
		ResultSet rs = DB.select("SELECT * FROM `artworks` WHERE artworkID = '" + artworkID + "'");
		try {
			while(rs.next()) {
				return rs.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    /**
	 * @return the userID
	 */
	public int getUserID() {
		return UserID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		UserID = userID;
	}
    
    /**
     *
     * @return the title of the artwork
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title of the artwork to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the artist's name
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * @param artist the artist's name to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     *
     * @return the artwork description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the artwork description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the filepath of a photograph of the artwork
     */
    public String getPhotographPath() {
        return photographPath;
    }

    /**
     *
     * @param photographPath the filepath to be set of a photograph of the artwork
     */
    public void setPhotographPath(String photographPath) {
        this.photographPath = photographPath;
    }

    /**
     *
     * @return the creation date of the artwork
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     *
     * @param dateCreated the creation date to be set for the artwork
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @return an array of the artwork's dimensions
     */
    public double[] getDimensions() {
        return new double[] {height, width};
    }

    /**
     *
     * @param height the height of the artwork
     * @param width the width of the artwork
     */
    public void setDimensions(double height, double width) {
        this.height = height;
        this.width = width;
    }
}
