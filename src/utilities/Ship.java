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
	//Checks if any of the points have been sunk, and changes them to sunkPoints list if so.
	public boolean shootShip(Point point){
		for (int x = 0; x < floatingPoints.size(); x++) {
			if (floatingPoints.get(x).getIsShot()) {
				sunkPoints.add(floatingPoints.get(x));
				floatingPoints.remove(x);
			}
		}
		System.out.println("TOTAL POINTS LEFT: " + floatingPoints.size());
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
	
	public void addFloatingPoint(Point point) {
		floatingPoints.add(point);
	}
	
	public int getSize() {
		return this.size;
	}
	
	//Returns if the ship has been destroyed(all points hit).
	public boolean isSunk() {
		if (floatingPoints.size() == 0) {
			return true;
		}
		return false;
	}
	
}
