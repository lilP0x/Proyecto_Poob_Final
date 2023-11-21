package domain;

import java.awt.*;
import java.util.HashMap;

public class Board{
	private HashMap<Color,Player> players = new HashMap<>();
	private Box[][] boxes;

	public Board(String nameP1,Color colorP1,String nameP2,Color colorP2,int size){
		boxes = new Box[size][size];
		addPlayers(nameP1,colorP1,nameP2,colorP2);
	}
	
	public Box[][] play(){
		
		
		return boxes;
	}
	
	private void addPlayers(String nameP1,Color colorP1,String nameP2,Color colorP2){
		//players.put(colorP2, Player);
		//players.put(colorP2, Player);
		
	}

}