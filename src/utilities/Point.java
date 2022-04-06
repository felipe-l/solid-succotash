package utilities;

public class Point {
    private int x;
    private int y;
    private boolean isShot;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean getIsShot() {
    	return isShot;
    }
    
    public void setIsShot(boolean shot) {
    	this.isShot = shot;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.isShot = false;
    }
    
    public boolean coordEquals(Point point) { //Consider re implementation.
    	if (point.getX() == this.x && point.getY() == this.y) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public Point() {
    }
}
