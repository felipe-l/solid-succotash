package utilities;

public class gridBoard {
	public Pane[][] gameBoard;
	public gridBoard(int height, int width) {
		gameBoard = new Pane[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				gameBoard[y][x] = new Pane(x, y);
			}
		}
	}
}
