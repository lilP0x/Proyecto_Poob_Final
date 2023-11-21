package domain;

import java.awt.*;

public abstract class Player{
	private String name;
	private Color color;
	private boolean turn;

	public Player(String name,Color color){
		this.name = name;
		this.color = color; 
	}


}