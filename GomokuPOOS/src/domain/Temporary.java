package domain;

import java.awt.Color;

public class Temporary extends Ficha{
	private boolean state;
	private int cont;
	
	
	public Temporary(Color color) {
		super(color);
		state = true;
		this.type = 't';
		cont = 0;
	}

	
	public boolean getState() {
		return state;
	}
	
	public void changeState() {
		state = false;
	}
	
	public void changeCont() {
		cont++;
	}
	
	
	
}
