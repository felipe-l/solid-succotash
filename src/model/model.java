package model;

import java.util.ArrayList;
import java.util.List;

import utilities.Guess;
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

	public void addShip(int x, int y) {
		Ship tempShip = new Ship(3, "three");
		Point shipCoords = new Point(x,y);
		tempShip.setCoords(shipCoords, "EAST"); //!TODO add support for other directions.
		ships.add(tempShip);
		floatingShips.add(tempShip);
		firstPlayer.getPane(x,y).setShip(tempShip);
		
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
