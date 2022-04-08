package view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import controller.controller;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.model;
import utilities.Point;
import utilities.outOfGridException;

/**
 * @author Felipe Lopez
 *	This class uses javaFX to create a view for our wordle game.
 *  The class uses GridPanes and VBox to organize our game in the
 *  scene. The class then communicates with the controller to color
 *  code the guesses that have been made. Our class implements Observer
 *  to observe our model class in order to obtain the progress of our
 *  game.
 */
public class BattleShipGUIView extends Application{

	/* Constants for the scene */
	private static final int SCENE_WIDTH = 1000;
	private static final int SCENE_HEIGHT = 800;
	private static final int LETTER_SQUARE_SIZE = 50;
	private static final int LETTER_FONT_SIZE = 20;
	private String direction = "SOUTH";

	private rectangleCoord currRect;
	private model model;
	private controller controller;
	private int checkStatus;
	private StackPane[][] CoordLabels = new StackPane[10][];

	
	private Stage myStage;
	
	
	/**
	 * Our Constructor which starts a new instance of our model, controller and
	 * connects our observer with our observable. 
	 * @throws IOException Incase of an error with the model.
	 */
	public BattleShipGUIView() throws IOException {
		model = new model();
		controller = new controller(model);
		//model.addObserver((Observer) this);
		checkStatus = controller.gameStatus();
		}
	
	
	private void setRectColors(int rectX, int rectY, int size) throws outOfGridException {
		Point endPoint = controller.checkCoordinateValidity(rectX, rectY, 4, direction);
		if (endPoint.getX() == rectX) {
			if (rectY < endPoint.getY()) {
        		for (int y = rectY; y < endPoint.getY(); y++) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[y][rectX].getChildren().get(0);
		        	tempRectangle.setFill(Color.BLACK);
        		}
			} else {
        		for (int y = rectY; y > endPoint.getY(); y--) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[y][rectX].getChildren().get(0);
		        	tempRectangle.setFill(Color.BLACK);
        		}
			}
		} else {
			if (rectX < endPoint.getX()) {
        		for (int x = rectX; x < endPoint.getX(); x++) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[rectY][x].getChildren().get(0);
		        	tempRectangle.setFill(Color.BLACK);
        		}
			} else {
        		for (int x = rectX; x > endPoint.getX(); x--) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[rectY][x].getChildren().get(0);
		        	tempRectangle.setFill(Color.BLACK);
        		}
			}
		}
	}
	
	private void resetColors(int rectX, int rectY, int size) throws outOfGridException {
		Point endPoint = controller.checkCoordinateValidity(rectX, rectY, 4, direction);
		if (endPoint.getX() == rectX) {
			if (rectY < endPoint.getY()) {
        		for (int y = rectY; y < endPoint.getY(); y++) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[y][rectX].getChildren().get(0);
		        	tempRectangle.setFill(Color.WHITE);
        		}
			} else {
        		for (int y = rectY; y > endPoint.getY(); y--) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[y][rectX].getChildren().get(0);
		        	tempRectangle.setFill(Color.WHITE);
        		}
			}
		} else {
			if (rectX < endPoint.getX()) {
        		for (int x = rectX; x < endPoint.getX(); x++) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[rectY][x].getChildren().get(0);
		        	tempRectangle.setFill(Color.WHITE);
        		}
			} else {
        		for (int x = rectX; x > endPoint.getX(); x--) {
	        		rectangleCoord tempRectangle = (rectangleCoord) CoordLabels[rectY][x].getChildren().get(0);
		        	tempRectangle.setFill(Color.WHITE);
        		}
			}
		}
	}
	
	private void setOnClickRect(rectangleCoord rect){
	    
	    rect.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent mouseEvent) {
	        	int status = controller.gameStatus();
	        	int size = 4;
	        	if (status == 0) {
					try {
						controller.placeShip(rect.xCoord, rect.yCoord, 4, direction);
					} catch (outOfGridException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		System.out.println(rect.getXCoord() + " " + rect.getYCoord());
	        	} else if (status == 1) {
	        		System.out.println(rect.getXCoord() + " " + rect.getYCoord());
	        	} else if (status == 2) {
	        		System.out.println(rect.getXCoord() + " " + rect.getYCoord());
	        	}
	        }
	    });
	    
	    rect.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent mouseEvent) {
	        	int status = controller.gameStatus();
	        	try {
					Point endPoint = controller.checkCoordinateValidity(rect.xCoord, rect.yCoord, 4, direction);
					System.out.println("ENDPOINT:X:" + endPoint.getX() + "Y:" + endPoint.getY() + " CURR:X:" + rect.xCoord + "Y:" + rect.yCoord);
		        	if (status == 0) {
		        		setRectColors(rect.getXCoord(), rect.getYCoord(), 4);
		        		currRect = rect;
		        		//System.out.println(rect.getXCoord() + " " + rect.getYCoord());
		        	} else if (status == 1) {
		        		System.out.println(rect.getXCoord() + " " + rect.getYCoord());
		        	} else if (status == 2) {
		        		System.out.println(rect.getXCoord() + " " + rect.getYCoord());
		        	}
				} catch (outOfGridException e) {
					System.out.println("BAD COORDS");
				}
	        	
	        }
	    });
	    
	    rect.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent mouseEvent) {
	        	int status = controller.gameStatus();
	        	try {
	        		resetColors(rect.getXCoord(), rect.getYCoord(), 4);
	        	
				} catch (outOfGridException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });
	}

    

    private VBox createPaneSlots(VBox vbox) {
		GridPane firstGrid = new GridPane();
		int counterX = 0;
		int counterY = 0;
		for (int y = 0; y < 10; y++ ) {
			counterX = 0;
			StackPane[] tempPaneList = new StackPane[10];
		    for (int x = 0; x < 10; x++) {
				rectangleCoord rect = new rectangleCoord(30, 30, LETTER_SQUARE_SIZE, LETTER_SQUARE_SIZE, x, y);
				rect.setFill(Color.WHITE);
			    rect.setStroke(Color.LIGHTGREY);
			    //rect.setStrokeWidth(8);
			    
			    setOnClickRect(rect);
			   
			    
			    StackPane stp = new StackPane(); //Allows us to add text in rect
			    Text text = new Text("");
			    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, LETTER_FONT_SIZE));
			    text.setFill(Color.BLACK);
			    
			    stp.getChildren().addAll(rect, text);
			    tempPaneList[x] = stp;
			    
		    	firstGrid.add(stp, counterX, counterY); //Adds rectangle on correct locations
		    	counterX += 1;
		    }
		    CoordLabels[y] = tempPaneList;
		    counterY += 1;
		}
	    firstGrid.setAlignment(Pos.CENTER);
	    vbox.getChildren().add(firstGrid);
	    return vbox;
    }
   
	/**
	 * The start method which sets up our stage, creates our scene and shows our
	 * scence. This is where all the magic happens. This method also has the EventHandler
	 * that checks when input is provided from the keyboard.
	 */
	@Override
	public void start(Stage stage) {
		myStage = stage;
		stage.setTitle("Wordle");
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            /**
             *Checks for keyboard input and acts on it if it is BACK_SPACE, DELETE, ENTER 
             * or a character.
             */
            @Override
            public void handle(KeyEvent event) {
        		String input = event.getCode().getName().toLowerCase();
        		if (input.equals("r")) {
        			
        			//Clears Board for a rotation
        			try {
						resetColors(currRect.getXCoord(), currRect.getYCoord(), 4);
					} catch (outOfGridException e1) {
						e1.printStackTrace();
					}
        			
        		    if (direction == "EAST") {
        		    	direction = "SOUTH";
        		    } else if (direction == "SOUTH") {
        		    	direction = "WEST";
        		    } else if (direction == "WEST") {
        		    	direction = "NORTH";
        		    } else if (direction == "NORTH") {
        		    	direction = "EAST";
        		    }
        		    
        		    //Clears 
	        		try {
						setRectColors(currRect.getXCoord(), currRect.getYCoord(), 4);
						
					} catch (outOfGridException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		    System.out.println("NEW DIRECTION:" + direction);
        		}
        	}
        });
		
		vbox = createPaneSlots(vbox);
		scene.setFill(Color.BEIGE);
		stage.setScene(scene);
		stage.show();
	}
	
	private class rectangleCoord extends Rectangle{
		private int xCoord;
		private int yCoord;
		private rectangleCoord(int x, int y, int letter_square_height, int letter_square_width, int xCoord, int yCoord) {
			super(x, y, letter_square_height, letter_square_width);
			this.xCoord = xCoord;
			this.yCoord = yCoord;
		}
		
		private int getXCoord() {
			return xCoord;
		}
		
		private int getYCoord() {
			return yCoord;
		}
	}
	
}
