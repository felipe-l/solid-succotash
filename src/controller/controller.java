package controller;

import javafx.scene.paint.Color;
import model.model;
import utilities.Point;
import utilities.gridBoard;
import utilities.outOfGridException;
import utilities.shipInterceptionException;

public class controller {
	
	private model model;

	private boolean guessed;
	private int totalShips;
	
	public controller (model model) {
		this.model = model;
		guessed = false;
		totalShips = 2; //How many ships in the game
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
			throw new outOfGridException("Out of Grid Coords " + tempX + " " + tempY);
		} else {
			Point endPoint = new Point(tempX, tempY);
			return endPoint;
		}
	}
	
	public boolean checkShipInterception(int rectX, int rectY, int size, String direction) throws outOfGridException {
		Point endPoint = checkCoordinateValidity(rectX, rectY, size, direction);
		if (endPoint.getX() == rectX) {
			if (rectY < endPoint.getY()) {
        		for (int y = rectY; y < endPoint.getY(); y++) {
        			if (model.containsShip(rectX, y)){
        				return true;
        			}
        		}
			} else {
        		for (int y = rectY; y > endPoint.getY(); y--) {
        			if (model.containsShip(rectX, y)){
        				return true;
        			}
        		}
			}
		} else {
			if (rectX < endPoint.getX()) {
        		for (int x = rectX; x < endPoint.getX(); x++) {
        			if (model.containsShip(x, rectY)){
        				return true;
        			}
        		}
			} else {
        		for (int x = rectX; x > endPoint.getX(); x--) {
        			if (model.containsShip(x, rectY)){
        				return true;
        			}
        		}
			}
		}
		return false;
		
	}
	
	public void placeShip(int x, int y, int size, String direction) throws outOfGridException, shipInterceptionException {
		//!TODO adds support for exception if placement not possible
		checkCoordinateValidity(x, y, size, direction);
		if (checkShipInterception(x, y, size, direction)) {
			throw new shipInterceptionException("Ship cannot be placed here");
		}
		model.addShip(x, y, size, direction);
	}
	
	public boolean containsShip(int x, int y) {
		return model.containsShip(x, y);
	}
	
	public int shootPoint(int x, int y) {
		int shotResult = model.processShot(x,y);
		return shotResult;
	}
	
	public gridBoard getGrid() { //Consider sending all panes instead??? 
		return model.getGrid();
	}

}
