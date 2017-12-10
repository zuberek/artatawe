package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * Represents an ellipse shape
 * @author David
 * @author Petar Radovanovic (888633)
 * @version 1.0
 */
public class Ellipse extends ShapeElement{

    private static double ERASER_POSITION_OFFSET = 5; // Used to fully erase the drawn ellipse
    private static double ERASER_SIZE_OFFSET = 3; // Used to fully erase the drawn ellipse

    /**
     * Constructor for the Ellipse
     * @param x coordinate of the top-left of the Ellipse
     * @param y coordinate of the top-left of the Ellipse
     * @param slider used to determine Ellipse size
     * @param lineColor Outline color of the Ellipse
     * @param fillColor Fill color of the Ellipse
     * @param isFilled Determines if the Ellipse is filled or not
     * @param gc GraphicsContext of the canvas
     */
    public Ellipse(double x, double y, Slider slider,
                   Color lineColor, Color fillColor, boolean isFilled,
                   double outLineThickness, GraphicsContext gc) {
        super(x-(slider.getValue()/2), y-(slider.getValue()/2), slider.getValue(),
                slider.getValue(), lineColor, fillColor, isFilled, outLineThickness);
        draw(gc);
    }

    /**
     * Draws the shape onto the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void draw(GraphicsContext gc){

        gc.setStroke(getLineColor());
        gc.setLineWidth(getOutLineThickness());
        gc.setFill(getFillColor());

        if (isFilled()){
            gc.fillOval(getX(), getY(), getWidth(), getHeight());
            gc.strokeOval(getX(), getY(), getWidth(), getHeight());
        } else {
            gc.strokeOval(getX(), getY(), getWidth(), getHeight());
        }

    }

    /**
     * Erases the shape from the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void erase(GraphicsContext gc){
        gc.setLineWidth(getOutLineThickness()+1);
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillOval(getX(), getY(), getWidth(), getHeight());
        gc.strokeOval(getX(), getY(), getWidth(), getHeight());
    }
}