package model;

import java.util.ArrayList;
import java.util.List;

import utilities.Guess;
import utilities.Pane;
import utilities.Point;
import utilities.Ship;
import utilities.gridBoard;

public class model {
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	private ArrayList<Ship> sunkShips = new ArrayList<Ship>();
	private ArrayList<Ship> floatingShips = new ArrayList<Ship>();


	
	/* 
	 * Maintains an array of INDEX_RESULTs for the guessed characters. There
	 * should be 26 indices in this array, one for each character in the English
	 * alphabet. Before a character has been guessed, its position in the array
	 * should hold the value 'null'.
	 */
	private gridBoard firstPlayer;
	
	public model() {
		firstPlayer = new gridBoard(10, 10);
	}
	
	public int totalShips() {
		return ships.size();
	}
	
	public int getFloatingShips() {
		return floatingShips.size();
	}
	
	public int getsunkShips() {
		return sunkShips.size();
	}

	public void addShip(int x, int y, int size, String direction) {
		//String direction = "EAST";
		Ship tempShip = new Ship(size, "SHIP-ONE");
		Point shipCoords = new Point(x,y);
		//tempShip.setCoords(shipCoords, direction); //!TODO add support for other directions.
		
		if (direction.equals("WEST")) {
			for (int left = 0; left < tempShip.getSize(); left++) {
				Pane currCoord = firstPlayer.getPane(x-left, y);
				tempShip.addFloatingPoint(currCoord.getPoint());
				currCoord.setShip(tempShip);

			}
		} else if (direction.equals("EAST")) {
			for (int right = 0; right < tempShip.getSize(); right++) {
				Pane currCoord = firstPlayer.getPane(x+right, y);
				tempShip.addFloatingPoint(currCoord.getPoint());
				currCoord.setShip(tempShip);
			}
		} else if (direction.equals("SOUTH")) {
			for (int down = 0; down < tempShip.getSize(); down++) {
				Pane currCoord = firstPlayer.getPane(x, y+down);
				tempShip.addFloatingPoint(currCoord.getPoint());
				currCoord.setShip(tempShip);
			}
		} else if (direction.equals("NORTH")) {
			for (int up = 0; up < tempShip.getSize(); up++) {
				Pane currCoord = firstPlayer.getPane(x, y-up);
				tempShip.addFloatingPoint(currCoord.getPoint());
				currCoord.setShip(tempShip);
			}
		}
		
		ships.add(tempShip);
		floatingShips.add(tempShip);
		
	}
	
	public int processShot(int x, int y) {
		int shotResult = firstPlayer.shoot(x, y);
		return shotResult;
		//!TODO could improve on this with observer. Ship observers point on change with shot.
		
		
	}
	
	public gridBoard getGrid() {
		return firstPlayer;
	}
	
}
