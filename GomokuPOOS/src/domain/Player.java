package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Player{
	protected String name;
	protected String color;
	protected ArrayList<Box> casillas = new ArrayList<>();  

	public Player(String name,String color){
		this.name = name;
		this.color = color;
	}
	
	public abstract void play(int row,int column,String type,Board tablero,char jugador) throws GomokuPOOSException;
	
	

	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
}

