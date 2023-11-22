package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;

public abstract class Player{
	private String name;
	private Color color;
	private Board tablero;
	private ArrayList<Ficha> fichas = new ArrayList<>();  

	public Player(String name,Color color, Board tablero){
		this.name = name;
		this.color = color;
		this.tablero = tablero;
	}
	
	public void play(int row,int column,String type){
		try {
            Class<? extends Ficha> fichaClass = Class.forName(type).asSubclass(Ficha.class);
            Constructor<? extends Ficha> constructor = fichaClass.getConstructor(String.class, int[][].class);
            Ficha ficha = constructor.newInstance(color);
    		tablero.play(row,column,ficha);
        } catch (Exception e) {
        }
		
		
	}
	

}