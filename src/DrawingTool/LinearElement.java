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
     * @param lineColor is the colour of the shapes lines
     * @param fillColor is the colour of the shapes fill
     */
    public LinearElement(double x, double y, Color lineColor, Color fillColor) {
        super(x, y, lineColor, fillColor);
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

