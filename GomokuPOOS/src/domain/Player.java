package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Player{
	protected String name;
	protected Color color;
	protected Board boxes; 
	protected int cantidadfichas = 700;

	public Player(String name,Color color,int size){
		this.name = name;
		this.color = color;
		this.boxes = new Box[size][size];
	}
	
	public abstract void play(int row,int column,String type,Board tablero) throws GomokuPOOSException;
	
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Box[][] getPlayerFichas(){
		return boxes;
	}
}

