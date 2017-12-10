package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *  Represents an abstract shape
 *  @author Petar Radovanovic (888633)
 *  @version 1.0
 */
public abstract class DrawingElement {
    private double x;
    private double y;
    private Color lineColor;

    Color getLineColor() {
        return lineColor;
    }
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    Color getFillColor() {
        return fillColor;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    private Color fillColor;

    /**
     * Constructor for the DrawingElement
     * @param x is the x coordinate of the top-left bounding-box
     * @param y is the y coordinate of the top-left bounding-box
     * @param lineColor is the colour of the shapes lines
     * @param fillColor is the colour of the shapes fill
     */
     DrawingElement(double x, double y, Color lineColor, Color fillColor){
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    /**
     * Draws the shape
     * @param gc GraphicsContext of the canvas
     */
    public void draw(GraphicsContext gc){
        gc.setFill(lineColor);
    }

    /**
     * Gets the x coordinate
     * @return the x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x coordinate
     * @param x is the coordinate being set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y coordinate
     * @param y is the coordinate being set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the colour of the shape
     * @return the colour of the shape
     */
    public Color getColor() {
        return lineColor;
    }

    /**
     * Sets the colour of the shape
     * @param color the colour of the shape
     */
    public void setColor(Color color) {
        this.lineColor = color;
    }
}

