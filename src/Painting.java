package src;

public class Painting extends Artwork {
    private String paintingType;

    public Painting(int userID, String title, String artist, String description, String photographPath, String dateCreated, double height, double width) {
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
    
    public void savePainting() {
    	DB.query("INSERT INTO `artwork` (`userID`, `artist`, `title`, `width`, `height`, `paintingType`, `dateCreated`) VALUES ('"+ this.getUserID() + "',  '" + this.getArtist() + ", '" + this.getDimensions()[1] + "', '" + this.getDimensions()[0] + "', '" + this.getPaintingType() + "', '" + this.getDateCreated() +"');");
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
