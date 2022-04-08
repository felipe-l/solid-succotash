package controller;

import model.model;
import utilities.Point;
import utilities.gridBoard;
import utilities.outOfGridException;

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
	
	public Point checkCoordinateValidity(int x, int y, int size, String direction) throws outOfGridException {
		int tempX = x;
		int tempY = y;
		if (direction == "SOUTH") {
			tempY += size;
		} else if (direction == "NORTH") {
			tempY -= size;
		} else if (direction == "WEST") {
			tempX -= size;
		} else if (direction == "EAST") {
			tempX += size;
		}
		
		if (-1 > tempX || tempX > 10 || -1 > tempY || tempY > 10) { //WEIRD MATH, the -1 makes the gui be able to print at 0 coords
			throw new outOfGridException("Out of Grid Coords " + tempX + tempY);
		} else {
			Point endPoint = new Point(tempX, tempY);
			return endPoint;
		}
	}
	
	public void placeShip(int x, int y, int size, String direction) throws outOfGridException {
		//!TODO adds support for exception if placement not possible
		checkCoordinateValidity(x, y, size, direction);
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
