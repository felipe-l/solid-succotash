package utilities;

public class gridBoard {
	public Pane[][] gameBoard;
	private int height;
	private int width; 
	public gridBoard(int height, int width) {
		this.width = width;
		this.height = height;
		gameBoard = new Pane[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				gameBoard[y][x] = new Pane(x, y);
			}
		}
	}
	
	public Pane getPane(int x, int y) {
		return gameBoard[y][x];
		
	}
	
	public boolean isItHit(int x, int y) {
		return gameBoard[y][x].getPoint().getIsShot();
	}
	
	public int shoot(int x, int y) { //!TODO Change this to an enum that returns, sunk, hit or miss. For now I will use a number.
		if (!gameBoard[y][x].getPoint().getIsShot()) {
			gameBoard[y][x].getPoint().setIsShot(true); // set point as shot.
			if (gameBoard[y][x].getShip() != null && !gameBoard[y][x].getShip().isSunk()) {
					boolean didSink = gameBoard[y][x].getShip().shootShip();
					if (didSink) {
						return 2; //Was Sunk
					} else {
						return 1; //Was Hit
					}
					
					
			} else if (gameBoard[y][x].getShip() == null) {
				return 0; //Nothing hit
			}
		}
		return -1;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
}
