package controller;

import model.model;

public class controller {
	
	private model model;

	private boolean guessed;
	
	public controller (model model) {
		this.model = model;
		guessed = false;
	} 
}
