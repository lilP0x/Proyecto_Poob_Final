package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class GomokuPOOS {
	private Player player1;
	private Player player2;
	private Board tablero;
	private int turn;
	

	public GomokuPOOS(String nameP1,Color colorP1, String nameP2,Color colorP2,String modoJuego,int size,String type1,String type2){
	tablero = new Board(size);	
	addPlayers(nameP1,colorP1,nameP2,colorP2,type1,type2);
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
	
	
	private void addPlayers(String nameP1,Color colorP1,String nameP2,Color colorP2,String type1, String type2){
		try {
            Class<? extends Player> playerClass1 = Class.forName(type1).asSubclass(Player.class);
            Constructor<? extends Player> constructor = playerClass1.getConstructor(String.class, int[][].class);
            Class<? extends Player> playerClass2 = Class.forName(type2).asSubclass(Player.class);
            Constructor<? extends Player> constructor1 = playerClass2.getConstructor(String.class, int[][].class);
            player1 = constructor.newInstance(nameP1,colorP1,tablero);
            player2 = constructor1.newInstance(nameP2,colorP2,tablero);
        } catch (Exception e) {
        }
		
		
	}
} 