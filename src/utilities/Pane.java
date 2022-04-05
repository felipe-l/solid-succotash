package utilities;

public class Pane {
	private Point point;
	private Ship ship;
	
	public Pane(int x, int y) {
		this.point = new Point(x,y);
		this.ship = null;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
