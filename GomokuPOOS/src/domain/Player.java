package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Player{
	protected String name;
	protected Color color;
	protected ArrayList<Box> boxes = new ArrayList<>();
	protected int cantidadfichas = 700;

	public Player(String name,Color color){
		this.name = name;
		this.color = color;
	}
	
	public abstract void play(int row,int column,String type,Board tablero) throws GomokuPOOSException;
	
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
}

