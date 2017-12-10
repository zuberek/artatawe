package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a specific shape made of with lines
 * @author Petar Radovanovic (888633)
 * @version 1.0
 */
public class ShapeElement extends LinearElement {
	private boolean isFilled;
	private double width;
	private double height;
	private double outLineThickness;

	/**
	 * Constructor for the shape
	 * @param x coordinate of the shape
	 * @param y coordinate of the shape
	 * @param width of the shape
	 * @param height of the shape
	 * @param fillColor is the colour of the fill
	 * @param lineColor is the colour of the lines
	 * @param isFilled Determines if the shape is filled or not
	 */
	public ShapeElement(double x, double y, double width, double height,
						Color lineColor, Color fillColor, boolean isFilled, double outLineThickness) {
		super(x, y, lineColor, fillColor);
		this.isFilled = isFilled;
		this.width = width;
		this.height = height;
		this.outLineThickness = outLineThickness;
	}

	/**
	 * Draws the shape
	 * @param gc GraphicsContext of the canvas
	 */
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
	}

	/**
	 * Returns whether the shape is filled or not
	 * @return isFilled
	 */
	public boolean isFilled() {
		return isFilled;
	}

	/**
	 * Sets the shapes filled boolean
	 * @param filled Determines if the shape is filled or not
	 */
	public void setFilled(boolean filled) {
		isFilled = filled;
	}

	/**
	 * Returns the width of the shape
	 * @return width of the shape
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width of the shape
	 * @param width of the shape
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	public double getOutLineThickness() {
		return outLineThickness;
	}

	public void setOutLineThickness(double outLineThickness) {
		this.outLineThickness = outLineThickness;
	}

	/**

	 * Gets the height of the shape
	 * @return the height of the shape
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets the height of the shape
	 * @param height height of the shape
	 */
	public void setHeight(double height) {
		this.height = height;
	}

}

