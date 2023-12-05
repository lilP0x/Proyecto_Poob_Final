package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;

public class Human extends Player{

	public Human(String name, Color color) {
		super(name, color);
		this.color = color;
		
	}
	

	@Override
	public void play(int row, int column, String type,Board tablero) throws GomokuPOOSException{
		tablero.play(row,column,type,color );
	}
	
 }
	

