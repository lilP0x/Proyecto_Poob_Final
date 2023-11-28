package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Player{
	protected String name;
	protected Color color;
	protected Board tablero;
	protected ArrayList<Ficha> fichas = new ArrayList<>();  

	public Player(String name,Color color, Board tablero){
		this.name = name;
		this.color = color;
		this.tablero = tablero;
	}
	
	public abstract void play(int row,int column,String type) throws GomokuPOOSException;
	
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
}

