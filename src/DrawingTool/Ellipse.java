package src.DrawingTool;

public class Ellipse extends ShapeElement{
    private int width;
    private int height;    
    
     /**
     *
     * @param width the width of the ellipse
     * @param height the height of the ellipse     
     * @param isFilled whether the shape will be filled or solid
     * 
     * *  if width and height are the same then the ellipse is a circle
     */
    public Ellipse(int width, int height, boolean isFilled){
        super(isFilled);        //will depend on the parameters constructor of the superclass
        this.width = width;
        this.height = height;
    }
    
    public void draw(){
        //depends on the implimentation of the canvas
    }
}
