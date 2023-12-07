
package domain;

import java.awt.Color;

public abstract class Ficha {
	protected Color color;
	protected char type;
	protected int cont;
	
	public Ficha(Color color){
		this.color = color;
	}
	
	
	public char getType() {
		return type;
	}
	
	public int getCont() {
		return cont;
	}
	
}