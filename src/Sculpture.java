package src;

/**
 * @author Joshua Blackman
 *
 */
public class Sculpture extends Artwork{
    private double depth;
    private String material;

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
    
    public void saveSculpture() {
    	DB.query("INSERT INTO `artwork` (`userID`, `type`, `artist`, `title`, `material`, `width`, `height`, `depth`, `dateCreated`) VALUES ('"+ this.getUserID() + "',  'sculpture', '" + this.getArtist() + "', '" + this.getMaterial() + "', '" + this.getDimensions()[1] + "', '" + this.getDimensions()[0] + "', '" + this.getDimensions()[2] + "', '" + this.getDateCreated() +"');");
    }

    /**
     *
     * @return a list of the full 3D sculpture dimensions
     */
    public double[] getDimensions() {
        return new double[] {height, width, depth};
    }

    /**
     *
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
     *
     * @param material the sculpture material to be set
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}
