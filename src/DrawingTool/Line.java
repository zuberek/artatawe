package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates a src.Line object
 * @author Hefin Johnson
 * @author Petar Radovanovic (88633)
 * @version 1.0
 */

public class Line extends LinearElement{
    private final double LINE_WIDTH_OFFSET = 3; // Used to erase the line using a slightly larger white line
    private double x2; // End x coordinate of the line
    private double y2; // End y coordinate of the line
    private double width;
    private double startingWidth;
    /**
     * Constructor for the line
     * @param x1 start x coordinate of the line
     * @param y1 start y coordinate of the line
     * @param x2 end x coordinate of the line
     * @param y2 end y coordinate of line
     * @param width of the line
     * @param lineColor is the colour of the line
     * @param fillColor is the colour of the fill
     * @param gc GraphicsContext of the canvas
     */
    public Line(double x1, double y1, double x2, double y2, double width, Color lineColor, Color fillColor, GraphicsContext gc){
        super(x1, y1, lineColor, fillColor);
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        draw(gc);
    }

    /**
     * Draws the line on the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(getColor());
        startingWidth = gc.getLineWidth();
        gc.setLineWidth(width);
        gc.strokeLine(getX(), getY(), x2, y2);
    }

    /**
     * Gets the x coordinate of the line end
     * @return x coordinate
     */
    public double getX2() {
        return x2;
    }

    /**
     * sets the x coordinate of the line end
     * @param x2 the x coordinate of the line end
     */
    public void setX2(double x2) {
        this.x2 = x2;
    }

    /**
     * gets the y coordinate of the line end
     * @return y coordinate of the line end
     */
    public double getY2() {
        return y2;
    }

    /**
     * sets the y coordinate of the line end
     * @param y2 the y coordinate of the line end
     */
    public void setY2(double y2) {
        this.y2 = y2;
    }

    /**
     *  Used to finish off the constructed line, it fills in the end points of the line
     * @param x2 the x coordinate of the line-end
     * @param y2 the y coordinate of the line-end
     * @param gc the GraphicsContext of the canvas
     */
    public void setEndPoint (double x2, double y2, GraphicsContext gc){
        this.x2 = x2;
        this.y2 = y2;
        gc.strokeLine(getX(), getY(), x2, y2);
        gc.setLineWidth(startingWidth);
    }

    /**
     * Used to erase the line from the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void erase(GraphicsContext gc){
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(width+LINE_WIDTH_OFFSET);
        gc.strokeLine(getX(), getY(), getX2(), getY2());
        gc.setLineWidth(startingWidth);

    }
}