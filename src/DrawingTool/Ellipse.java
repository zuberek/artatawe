package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents an ellipse shape
 * @author
 * @version 1.0
 */
public class Ellipse extends ShapeElement{

    private static double ERASER_POSITION_OFFSET = 3; // Used to fully erase the drawn ellipse
    private static double ERASER_SIZE_OFFSET = 5; // Used to fully erase the drawn ellipse

    /**
     * Constructor for the ellipse
     * @param x coordinate of the top-left bounding-box of the ellipse
     * @param y coordinate of the top-left bounding-box of the ellipse
     * @param width of the ellipse
     * @param height of the ellipse
     * @param color of the ellipse
     * @param isFilled Determines if the ellipse will be filled or just an outline
     * @param gc GraphicsContext needed to draw the shape
     */
    public Ellipse(double x, double y, double width, double height,
                   Color color, boolean isFilled, GraphicsContext gc) {
        super(x, y, width, height, color, isFilled);
        draw(gc);
    }

    /**
     * Draws the shape onto the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void draw(GraphicsContext gc){
        if (isFilled()){
            gc.fillOval(getX(), getY(), getWidth(), getHeight());
        } else {
            gc.strokeOval(getX(), getY(), getWidth(), getHeight());
        }

    }

    /**
     * Erases the shape from the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void erase(GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.fillOval(getX()-ERASER_POSITION_OFFSET, getY()-ERASER_POSITION_OFFSET,
                getWidth()+ERASER_SIZE_OFFSET, getHeight()+ERASER_SIZE_OFFSET);
    }
}
