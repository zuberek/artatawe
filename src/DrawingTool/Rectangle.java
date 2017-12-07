package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * creates a src.Rectangle object
 * @author David
 */
public class Rectangle extends ShapeElement{


    private static double ERASER_POSITION_OFFSET = 3; // Used to fully erase the drawn rectangle
    private static double ERASER_SIZE_OFFSET = 5; // Used to fully erase the drawn rectangle

    /**
     * Constructor for the rectangle
     * @param x coordinate of the rectangle
     * @param y coordinate of the rectangle
     * @param width of the rectangle
     * @param height of the rectangle
     * @param color of the rectangle
     * @param isFilled Determines if the rectangle is filled or not
     * @param gc GraphicsContext of the canvas
     */
    public Rectangle(double x, double y, double width, double height, Color color, boolean isFilled, GraphicsContext gc) {
        super(x, y, width, height, color, isFilled);
        draw(gc);
    }

    /**
     * Draws the shape onto the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void draw(GraphicsContext gc){
        if (isFilled()){
            gc.fillRect(getX(), getY(), getWidth(), getHeight());
        } else {
            gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
     * Erases the shape from the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void erase(GraphicsContext gc){
        gc.clearRect(getX()-ERASER_POSITION_OFFSET, getY()-ERASER_POSITION_OFFSET,
                getWidth()+ERASER_SIZE_OFFSET, getHeight()+ERASER_SIZE_OFFSET);
    }
}