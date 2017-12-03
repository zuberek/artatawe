package src;

public class Painting extends Artwork {
    private String paintingType;

    public Painting(String title, String artist, String description, String photographPath, String dateCreated, double height, double width) {
        setTitle(title);
        setArtist(artist);
        setDescription(description);
        setPhotographPath(photographPath);
        setDateCreated(dateCreated);
        setDimensions(height, width);
        setPaintingType(paintingType);
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
