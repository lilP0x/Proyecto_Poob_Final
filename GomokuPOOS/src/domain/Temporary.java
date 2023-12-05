package domain;

import java.awt.Color;

public class Temporary extends Ficha{
	private boolean state;
	
	public Temporary(Color color) {
		super(color);
		state = true;
		this.type = 't';
	}
	
	public boolean getState() {
		return state;
	}
}
