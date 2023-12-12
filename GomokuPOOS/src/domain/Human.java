package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;

public class Human extends Player{

	public Human(String name, String colorP1) {
		super(name, colorP1);
		this.color = colorP1;
		
	}
	

	@Override
	public void play(int row, int column, String type,Board tablero,char jugador) throws GomokuPOOSException{
		tablero.play(row,column,type,jugador );
	}
	
 }
	

