package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a shape made with lines
 * @author Petar Radovanovic (888633)
 * @version 1.0
 */
public abstract class LinearElement extends DrawingElement {

    /**
     * Constructor for the shape
     * @param x the x coordinate of the shape
     * @param y the y coordinate of the shape
     * @param color the colour of the shape
     */
    public LinearElement(double x, double y, Color color) {
        super(x, y, color);
    }

    /**
     * Draws the shape
     * @param gc GraphicsContext of the canvas
     */
    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}