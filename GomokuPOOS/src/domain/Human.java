package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;

public class Human extends Player{

	public Human(String name, Color color,Board tablero) {
		super(name, color,tablero);
	}
	

	@Override
	public void play(int row, int column, String type) throws GomokuPOOSException{
	    
		if(type.equals("temporary")) {
			Ficha ficha = new Temporary(color);
			tablero.play(row, column,ficha);
			fichas.add(ficha);
		}else if(type.equals("Normal")) {
			Ficha ficha = new NormalFicha(color);
			tablero.play(row, column,ficha);
			fichas.add(ficha);
		}else if(type.equals("Heavy")) {
			Ficha ficha = new Heavy(color);
			tablero.play(row, column,ficha);
			fichas.add(ficha);
		}
	}
	
 }
	

