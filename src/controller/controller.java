package controller;

import model.model;
import utilities.gridBoard;

public class controller {
	
	private model model;

	private boolean guessed;
	private int totalShips;
	
	public controller (model model) {
		this.model = model;
		guessed = false;
		totalShips = 1; //How many ships in the game
	} 
	
	public int gameStatus() {
		if (model.totalShips() != totalShips) {
			return 0; //pregame (placing ships)
		} else if (model.getFloatingShips() == 0) {
			return 2;// game over
		} else {
			return 1; //shooting phase
		}
	}
	
	public void placeShip(int x, int y, int size, String direction) {
		//!TODO adds support for exception if placement not possible
		model.addShip(x, y, size, direction);
	}
	
	public int shootPoint(int x, int y) {
		int shotResult = model.processShot(x,y);
		return shotResult;
	}
	
	public gridBoard getGrid() { //Consider sending all panes instead??? 
		return model.getGrid();
	}

}
