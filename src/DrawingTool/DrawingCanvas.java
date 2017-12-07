package src.DrawingTool;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Stack;

/**
 * A canvas used to draw a custom user image
 * @author Petar Radovanovic (888633)
 * @version 1.0
 */
public class DrawingCanvas extends Application{
    // The dimensions of the window
    private static final int WINDOW_WIDTH = 610;
    private static final int WINDOW_HEIGHT = 410;

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;

    // Dimensions of the VBox
    private static final int VBOX_WIDTH = 200;

    //Parameters for the slider
    private static final int SLIDER_MIN = 1;
    private static final int SLIDER_MAX = 100;
    private static final int SLIDER_DEFAULT = 10;
    private static final int SLIDER_MAJORTICK = 49;
    private static final int SLIDER_MINORTICK = 10;
    private static final int SLIDER_INCREMENT = 1;

    private Canvas canvas;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = buildGUI();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../Styles/drawing-canvas.css").toExternalForm());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Creates a complete GUI to be put on the scene
     * @return the built GUI
     */
    private Pane buildGUI() {
        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane holder = new StackPane(); // Stackpane used to serve as a backdrop for the canvas
        holder.getChildren().add(canvas); // Adds the canvas to the holder Stackpane
        holder.getStyleClass().add("canvas"); // Sets the style class for the holder
        root.setCenter(holder);

        /*
          Setting the style of the GUI and its components
         */
        root.setStyle("-fx-background: #ffffff;");
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10,10,10,10));
        sidebar.setMinWidth(VBOX_WIDTH);
        root.setLeft(sidebar);

        Stack<DrawingElement> shapeStack = new Stack<>(); // Stack which stores DrawingElement, used in the undo feature.

        /*
          Choicebox used for tool selection
         */
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Circle", "Square", "Text", "Line", "Freedraw");
        choiceBox.getSelectionModel().selectFirst();
        sidebar.getChildren().add(choiceBox);

        /*
          Basic colour picker used in the shapes.
         */
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        sidebar.getChildren().add(colorPicker);

        /*
          Checkbox used to indicate whether or not a shape should be filled
         */
        CheckBox shapeFill = new CheckBox();
        Label fillLabel = new Label("Fill");
        fillLabel.setGraphic(shapeFill);
        fillLabel.setContentDisplay(ContentDisplay.RIGHT);
        sidebar.getChildren().addAll(shapeFill, fillLabel);

        /*
          Slider and its associated options
         */
        Slider slider = new Slider();
        slider.setMin(SLIDER_MIN);
        slider.setMax(SLIDER_MAX);
        slider.setValue(SLIDER_DEFAULT);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(SLIDER_MAJORTICK);
        slider.setMinorTickCount(SLIDER_MINORTICK);
        slider.setBlockIncrement(SLIDER_INCREMENT);
        sidebar.getChildren().add(slider);

        HBox clearUndo = new HBox(); // HBox used to store two buttons used to clear and undo

        /*
          The buttons associated with the undo and the clear functions
         */
        Button clearButton = new Button("Clear Canvas");
        Button undoButton = new Button("Undo Shape");
        HBox.setHgrow(clearButton, Priority.ALWAYS); // Used to fill up the space in the HBox
        HBox.setHgrow(undoButton, Priority.ALWAYS);
        clearUndo.getChildren().addAll(undoButton, clearButton); // Add the buttons to the HBox
        sidebar.getChildren().add(clearUndo); // Add the HBox to the VBox

        /*
          Sets the maximum width scaling for the elements of the VBox
         */
        choiceBox.setMaxWidth(VBOX_WIDTH);
        colorPicker.setMaxWidth(VBOX_WIDTH);
        slider.setMaxWidth(VBOX_WIDTH);
        undoButton.setMaxWidth(VBOX_WIDTH/2);
        clearButton.setMaxWidth(VBOX_WIDTH/2);

        /*
          Behaviour of the canvas for a users mouse click (Press & Release)
         */
        canvas.setOnMouseClicked(e ->{
            gc.setFill(colorPicker.getValue()); // Gets the colour value from colorPicker for filling
            gc.setStroke(colorPicker.getValue()); // Gets the colour value from colorPicker for the outline
            /*
              Switch case used to determine which tool is being used, checks the choiceBox for tool selection.
             */
            switch(choiceBox.getSelectionModel().getSelectedItem().toString()) {
                case "Circle":
                    // Constructs the circle and pushes it to the shape stack, sets the circle center position to the cursor position
                    shapeStack.push(new Ellipse(e.getX()-(slider.getValue()/2), e.getY()-(slider.getValue()/2),
                            slider.getValue(), slider.getValue(), colorPicker.getValue(),shapeFill.isSelected(), gc));
                    break;
                case "Square":
                    // Constructs the Square and pushes it to the shape stack, sets the square center position to the cursor position
                    shapeStack.push(new Rectangle(e.getX()-(slider.getValue()/2), e.getY()-(slider.getValue()/2),
                            slider.getValue(), slider.getValue(), colorPicker.getValue(),shapeFill.isSelected(), gc));
                    break;
                default:
                    break;
            }

        });

        /*
          Behaviour of the canvas for a users mouse press (Press down)
         */
        canvas.setOnMousePressed(e ->{
            gc.setFill(colorPicker.getValue());
            gc.setStroke(colorPicker.getValue());

            switch(choiceBox.getSelectionModel().getSelectedItem().toString()){
                case "Freedraw": // Draws ellipses centered on the users mouse
                    Ellipse asde = new Ellipse(e.getX()-(slider.getValue()/2), e.getY()-(slider.getValue()/2),
                            slider.getValue(), slider.getValue(), colorPicker.getValue(),true, gc);
                    break;
                case "Line": // Creates a transparent line on the pressed location as a placeholder.
                    shapeStack.push(new Line(e.getX(), e.getY(), e.getX(), e.getY(), slider.getValue(), new Color(0,0,0,0), gc));
                default:
                    break;
            }
        });

        /*
          Behaviour of the canvas for a users mouse when dragged (Pressed down & moved)
         */
        canvas.setOnMouseDragged(e ->{
            gc.setFill(colorPicker.getValue());
            gc.setStroke(colorPicker.getValue());
            switch(choiceBox.getSelectionModel().getSelectedItem().toString()){
                case "Freedraw": // Draws ellipses centered on the users mouse
                    new Ellipse(e.getX()-(slider.getValue()/2), e.getY()-(slider.getValue()/2),
                            slider.getValue(), slider.getValue(), colorPicker.getValue(),true, gc);
                    break;
                default:
                    break;
            }
        });

        /*
          Behaviour of the canvas for a users mouse release (Release from press)
         */
        canvas.setOnMouseReleased(e ->{
            switch(choiceBox.getSelectionModel().getSelectedItem().toString()){
                case "Line": // Takes the previously constructed line and adds an end point for the line.
                    Line tempLine = (Line) shapeStack.peek();
                    tempLine.setEndPoint(e.getX(), e.getY(), gc);
                default:
                    break;
            }
        });

        /*
          Behaviour of the undoButton, if the stack is not empty it pops the stack and calls the erase method of the popped shape.
         */
        undoButton.setOnAction(e ->{
            if(!shapeStack.isEmpty()){
                shapeStack.pop().erase(gc);
            }
        });

        /*
          Behaviour of the clearButton, it clears a rectangle shape in the size of the entire canvas.
         */
        clearButton.setOnAction(e -> gc.clearRect(0,0,CANVAS_WIDTH, CANVAS_HEIGHT));

        return root;
    }
}
