package src;

/**
 * Creates a src.Line object
 * @author Hefin Johnson
 */

public class Line extends LinearElement{
    private int width;
    private int height;

    /**
     *
     * @param Width will set the width of the src.Line
     * @param Height will set the height of the src.Line
     */
    
    public Line(int width, int height){
        this.width = width;
        this.height = height;
        
    }

  
    public void draw(){
        
        // Dependant on how the canvas is implemented
    
    }
}