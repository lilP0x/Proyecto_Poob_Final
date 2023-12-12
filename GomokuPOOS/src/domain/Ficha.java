
package domain;

import java.awt.Color;

public abstract class Ficha {
	protected char color;
	protected char type;
	protected int cont;
	
	public Ficha(char color){
		this.color = color;
	}
	
	
	public char getType() {
		return type;
	}
	
	public int getCont() {
		return cont;
	}
	
	public char colorficha() {
		return color;
	}
	
}