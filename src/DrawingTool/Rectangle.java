package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * creates a src.Rectangle object
 * @author David
 */
public class Rectangle extends ShapeElement{


    private static double ERASER_POSITION_OFFSET = 10; // Used to fully erase the drawn rectangle
    private static double ERASER_SIZE_OFFSET = 5; // Used to fully erase the drawn rectangle

    /**
     * Constructor for the Rectangle
     * @param x coordinate of the top-left of the Rectangle
     * @param y coordinate of the top-left of the Rectangle
     * @param slider used to determine Rectangle size
     * @param lineColor Outline color of the Rectangle
     * @param fillColor Fill color of the Rectangle
     * @param isFilled Determines if the Rectangle is filled or not
     * @param gc GraphicsContext of the canvas
     */
    public Rectangle(double x, double y, Slider slider,
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

        gc.setLineWidth(getOutLineThickness());
        gc.setStroke(getLineColor());
        gc.setFill(getFillColor());
        if (isFilled()){
            gc.fillRect(getX(), getY(), getWidth(), getHeight());
            gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        } else {
            gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
     * Erases the shape from the canvas
     * @param gc GraphicsContext of the canvas
     */
    public void erase(GraphicsContext gc){
        gc.setLineWidth(getOutLineThickness()+ERASER_POSITION_OFFSET);
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        gc.fillRect(getX()-ERASER_POSITION_OFFSET/2, getY()-ERASER_POSITION_OFFSET/2,
                getWidth()+ERASER_POSITION_OFFSET, getHeight()+ERASER_POSITION_OFFSET);
        gc.strokeRect(getX()-ERASER_POSITION_OFFSET/2, getY()-ERASER_POSITION_OFFSET/2,
                getWidth()+ERASER_POSITION_OFFSET, getHeight()+ERASER_POSITION_OFFSET);
    }
}