package src.DrawingTool;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Creates a src.Text object
 * @author Hefin Johnson
 */

public class Text extends DrawingElement {

    private String text;
    private double size;
    /**
     *
     * @param x will set the x position of the src.Text
     * @param y will set the y position of the src.Text
     */

    public Text(double x, double y, String text, Color color, double size, GraphicsContext gc){
        super(x, y, color);
        this.text = text;
        this.size = size;
        draw(gc);
    }


    public void draw(GraphicsContext gc){
        gc.setStroke(getColor());
        gc.setFont(Font.font("Verdana", size));
        gc.strokeText(text, getX(), getY());
    }
}