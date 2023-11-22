package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class GomokuPOOS {
	private Player player1;
	private Player player2;
	private Board tablero;
	private int turn;
	

	public GomokuPOOS(String nameP1,Color colorP1, String nameP2,Color colorP2,String modoJuego,int size,String type){
	tablero = new Board(nameP1,colorP1,nameP2,colorP2,size);	
	addPlayers(nameP1,colorP1,nameP2,colorP2,type);
	turn = 1;	
	}

	
	public void play(int row,int column,String type){
		
		if(turn % 2 == 0) {
			player2.play(row,column,type);
		}else {
			player1.play(row,column,type);
		}
		turn++;
		
	}
	
	private void addPlayers(String nameP1,Color colorP1,String nameP2,Color colorP2,String type){
		try {
            Class<? extends Player> playerClass = Class.forName(type).asSubclass(Player.class);
            Constructor<? extends Player> constructor = playerClass.getConstructor(String.class, int[][].class);
            player1 = constructor.newInstance(nameP1,colorP1);
            player2 = constructor.newInstance(nameP2,colorP2);
        } catch (Exception e) {
        }
		
		
	}
} 