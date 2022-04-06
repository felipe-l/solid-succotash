package view;

import java.io.IOException;
import java.util.Scanner;

import controller.controller;
import model.model;
import utilities.gridBoard;

public class BattleShipTextView {
	private int checkStatus;
	public void run() throws IOException {
		System.out.println("GAME STARTED!");
		
		model model = new model();
		controller controller = new controller(model);
		
		checkStatus = controller.gameStatus();

		Scanner myObj = new Scanner(System.in);
		while (checkStatus == 0) {
			System.out.print("Enter x coord ");
			String xCoord = myObj.nextLine();  // Read user input
			int xNum = Integer.parseInt(xCoord);
			System.out.print("Enter y coord ");
			String yCoord = myObj.nextLine();  // Read user input
			int yNum = Integer.parseInt(yCoord);
			
			System.out.print("Enter size");
			String shipLength = myObj.nextLine();  // Read user input
			int size = Integer.parseInt(shipLength);
			
			controller.placeShip(xNum, yNum, size, "EAST");
			checkStatus = controller.gameStatus();
		}
		System.out.println("Start Shooting");
		while (checkStatus == 1) {
			System.out.print("Enter x coord ");
			String xCoord = myObj.nextLine();  // Read user input
			int xNum = Integer.parseInt(xCoord);
			System.out.print("Enter y coord ");
			String yCoord = myObj.nextLine();  // Read user input
			int yNum = Integer.parseInt(yCoord);
			
			int shotResult = controller.shootPoint(xNum,yNum);
			System.out.println("Returned " + shotResult);
			if (shotResult == 2) {
				System.out.println("YOU SUNK A SHIP");
			}
			printBoard(controller.getGrid());
			System.out.println();
		}
		
	}
	
	public void printBoard(gridBoard gameBoard) {
		for (int y = 0; y < gameBoard.getHeight(); y++) {
			for (int x = 0; x < gameBoard.getWidth(); x++) {
				if (gameBoard.getPane(x, y).getPoint().getIsShot()) {
					if (gameBoard.getPane(x,y).getShip() == null) {
						System.out.print("M");
					} else {
						System.out.print("H");
					}
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}
}
