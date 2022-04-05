package utilities;

import java.util.ArrayList;

public class Ship {
	private int size;
	private String name;
	private ArrayList<Point> sunkPoints = new ArrayList<Point>();
	private ArrayList<Point> floatingPoints = new ArrayList<Point>();
	
	public Ship(int size, String name) {
		this.size = size;
		this.name = name;
	}
	
	public void setCoords(Point start, String direction) {
		if (direction.equals("WEST")) {
			for (int x = 0; x < size; x++) {
				Point nextCoord = new Point(start.getX()-x, start.getY());
				floatingPoints.add(nextCoord);
			}
		} else if (direction.equals("EAST")) {
			for (int x = 0; x < size; x++) {
				Point nextCoord = new Point(start.getX()+x, start.getY());
				floatingPoints.add(nextCoord);
			}
		} else if (direction.equals("SOUTH")) {
			for (int y = 0; y < size; y++) {
				Point nextCoord = new Point(start.getX(), start.getY()+y);
				floatingPoints.add(nextCoord);
			}
		} else if (direction.equals("NORTH")) {
			for (int y = 0; y < size; y++) {
				Point nextCoord = new Point(start.getX(), start.getY()-y);
				floatingPoints.add(nextCoord);
			}
		}
	}
	
	//changes sunkPoints and floatingPoints on point hit
	public boolean shootShip(Point point){
		for (int x = 0; x < sunkPoints.size(); x++) {
			if (floatingPoints.get(x).equals(point)) {
				sunkPoints.add(point);
				floatingPoints.remove(x);
			}
		}
		return isSunk();
	}
	
	//returns the total points sunk
	public int sunkTotal() {
		return sunkPoints.size();
	}
	
	public int floatingTotal() {
		return floatingPoints.size();
	}
	
	public ArrayList<Point> getFloating() {
		return floatingPoints;
	}
	
	public ArrayList<Point> getSunk() {
		return sunkPoints;
	}
	
	//Returns if the ship has been destroyed(all points hit).
	public boolean isSunk() {
		if (floatingPoints.size() == 0) {
			return true;
		}
		return false;
	}
	
}
