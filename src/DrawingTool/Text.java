package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Creates a src.Text object
 * @author Hefin Johnson
 * @author Petar Radovanovic (888633)
 * @version 1.0
 */

public class Text extends DrawingElement {

    private String text;
    private double size;
    private boolean isFilled;
    private double outLineThickness;
    /**
     *
     * @param x will set the x position of the src.Text
     * @param y will set the y position of the src.Text
     */

    public Text(double x, double y, String text, Color lineColor, Color fillColor,
                double size, boolean isFilled, double outLineThickness,
                GraphicsContext gc){

        super(x, y, lineColor, fillColor);
        this.text = text;
        this.size = size;
        this.isFilled = isFilled;
        this.outLineThickness = outLineThickness;
        draw(gc);
    }

    public void draw(GraphicsContext gc){

        gc.setLineWidth(outLineThickness);
        gc.setStroke(getLineColor());
        gc.setFill(getFillColor());
        gc.setFont(Font.font("Verdana", size));

        if(isFilled){
            gc.fillText(text, getX(), getY());
            gc.strokeText(text, getX(), getY());
        } else {
            gc.strokeText(text,getX(), getY());
        }
    }
}