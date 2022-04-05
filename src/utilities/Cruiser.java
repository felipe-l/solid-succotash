package utilities;

public class Cruiser extends Ship{
	public Cruiser(int size, String name) {
		super(size, name);
		// TODO Auto-generated constructor stub
	}
	private int size = 4;
	private String name = "Cruiser";
	private Point[] coords = new Point[4];
}
