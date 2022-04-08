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

	
	private model model;
	private controller controller;
	private int checkStatus;
	private StackPane[][] guessLabels = new StackPane[10][];

	
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
	
//	/**
//	 *This method is part of our observable which gets updated when there
//	 *is a change in the model. Therefore, notified when there is a new guess.
//	 */
//	@SuppressWarnings("deprecation")
//	public void update(Observable observable, Object message) {
//		Model theGame = (WordleModel) observable;
//		theGame.getProgress();
//		guessed = theGame.getGuessedCharacters();
//	}

    
    /**
     * This method creates an animation of Nyan cat walking on the ground of our window. The method uses
     * Timeline class and KeyFrames to move the image. We then use our subclass nyanAnimation to crop our
     * image and rotate between our sprites.
     * @return Our imageView which will be animated.
     */

    
    private VBox createGuessSlots(VBox vbox) {
		GridPane firstGrid = new GridPane();
		int counterX = 0;
		int counterY = 0;
		for (int y = 0; y < 10; y++ ) {
			counterX = 0;
			StackPane[] tempLabelList = new StackPane[10];
		    for (int x = 0; x < 10; x++) {
				Rectangle rect = new Rectangle(30, 30, LETTER_SQUARE_SIZE, LETTER_SQUARE_SIZE);
				rect.setFill(Color.WHITE);
			    rect.setStroke(Color.LIGHTGREY);
			    //rect.setStrokeWidth(8);
			    
			    StackPane stp = new StackPane(); //Allows us to add text in rect
			    Text text = new Text("");
			    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, LETTER_FONT_SIZE));
			    text.setFill(Color.BLACK);
			    
			    stp.getChildren().addAll(rect, text);
			    tempLabelList[x] = stp;
			    
		    	firstGrid.add(stp, counterX, counterY);
		    	counterX += 1;
		    }
		    guessLabels[y] = tempLabelList;
		    counterY += 1;
		}
	    firstGrid.setHgap(0);
	    firstGrid.setVgap(0);
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
        
		vbox = createGuessSlots(vbox);
		scene.setFill(Color.BEIGE);
		stage.setScene(scene);
		stage.show();
	}
}
