package view;

import java.io.IOException;

import javafx.application.Application;

public class Battleship {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
    	if (args[0].equals("-text")) {
    		BattleShipTextView textGame = new BattleShipTextView();
    		textGame.run();
    	}
//    	} else if (args[0].equals("-gui")) {
//    		Application.launch(BattleShipGUIView.class, args);
//    	} else {
//    		Application.launch(BattleShipGUIView.class, args);
//    	}
	}

}
