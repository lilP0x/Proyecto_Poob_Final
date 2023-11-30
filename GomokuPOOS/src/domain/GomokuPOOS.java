package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class GomokuPOOS {
	private Player player1;
	private Player player2;
	private Board tablero;
	private int turn;
	

	public GomokuPOOS(String nameP1,Color colorP1, String nameP2,Color colorP2,String modoJuego,int size,String type1,String type2,int porcentaje){
	tablero = new Board(size,porcentaje);	
	addPlayers(nameP1,colorP1,nameP2,colorP2,type1,type2);
	turn = 1;	
	}

	
	public void play(int row,int column,String type) throws GomokuPOOSException{
		
		if(turn % 2 == 0) {
			player2.play(row,column,type);
		}else {
			player1.play(row,column,type);
		}
		turn++;
		
	}
	
	private void addPlayers(String nameP1,Color colorP1,String nameP2,Color colorP2,String type1, String type2){
           
        if(type1.equals("Human")&& type2.equals("Human")) {
        	
        	player1 = new Human(nameP1, colorP1,tablero);
        	player2 = new Human(nameP2,colorP2,tablero);
        	
        }else if(type1.equals("Human")&& type2.equals("Miedosa")) {
        	
        	player1 = new Human(nameP1, colorP1, tablero);
        	player2 = new Fearful(nameP2, colorP2, tablero);
        	
        }else if(type1.equals("Human")&& type2.equals("Agresiva")) {
        	
        	player1 = new Human(nameP1, colorP1, tablero);
        	player2 = new Agressive(nameP2, colorP2, tablero);
        	
        }else if(type1.equals("Human")&& type2.equals("Experta")) {
        	
        	player1 = new Human(nameP1, colorP1, tablero);
        	player2 = new Expert(nameP2, colorP2, tablero);
        	
        }
        
	}
	
	
	public Box getBox(int i, int j) {
		return tablero.getBox(i,j);
	}
	
	
	public Player[] getPlayers() {
	    Player[] players = {player1, player2};
	    return players;
	}

	public int getTurn() {
		
		return turn;
	}
	
	public Box[][] getBoard() {
		return tablero.getTablero();
	}
	
	public Player getPlayerOnTurn(){
		if(turn % 2 == 0) {
			return player2;
		}else {
			return player1;
		}
	}
} 