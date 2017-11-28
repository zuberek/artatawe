/**
 * creates a Rectangle object
 * @author David 
 */
public class Rectangle extends ShapeElement{
    private int width;
    private int height;
    
    /**
     *
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param isFilled whether the shape will be filled or solid 
     */
    public Rectangle(int width, int height, boolean isFilled){   
        super(isFilled);        //will depend on the parameters constructor of the superclass
        this.width = width;
        this.height = height;        
    }
    
    public void draw(){
        //depends on the implimentation of the canvas
    }
}
