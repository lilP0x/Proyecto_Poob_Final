package domain;

import java.awt.Color;

public class Temporary extends Ficha{
	private boolean state;
	private int cont;
	
	
	public Temporary(char color) {
		super(color);
		state = true;
		this.type = 't';
		cont = 0;
	}
	
	public int sum() {
		cont += 1;
		return cont;
	}
	
	public boolean getState() {
		return state;
	}
	
	public void changeState() {
		state = false;
	}
	
	public int changeCont() {
		cont += 1;
		return cont;
	}
	
	
	
}
