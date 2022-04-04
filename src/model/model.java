package model;

import java.util.List;

import utilities.Guess;
import utilities.gridBoard;

public class model {
	private String answer;
	
	/* 
	 * Maintains an array of INDEX_RESULTs for the guessed characters. There
	 * should be 26 indices in this array, one for each character in the English
	 * alphabet. Before a character has been guessed, its position in the array
	 * should hold the value 'null'.
	 */
	private gridBoard[] guessedCharacters;
	private List<String> Dictionary;
	
	private Guess[] progress;
}
