package domain;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class GomokuPOOS {
	private Player player1;
	private Player player2;
	private Board tablero;
	private int turn;
	private int size;
	

	public GomokuPOOS(String nameP1,Color colorP1, String nameP2,Color colorP2,String modoJuego,int size,String type1,String type2,double porcentaje){
	this.size = size;
	tablero = new Board(size,porcentaje);	
	addPlayers(nameP1,colorP1,nameP2,colorP2,type1,type2,tablero);
	turn = 1;	
	}

	
	public void play(int row,int column,String type) throws GomokuPOOSException{
		if(win() == true) {throw new GomokuPOOSException(GomokuPOOSException.PLAYER_WIN);}
			if(turn % 2 == 0) {
				player2.play(row,column,type,tablero);
			
			}else {
				player1.play(row,column,type, tablero);
				

			}
			turn++;

	}
	
	private void isTemporary() {
		tablero.isTemporary();
	}
	
	
	
	public boolean win() {
        if (tablero.win()) {
            return true;
        }
        return false;
    }
	
	
	
	
	private void addPlayers(String nameP1,Color colorP1,String nameP2,Color colorP2,String type1, String type2, Board tablero){
           
        if(type1.equals("Human")&& type2.equals("Human")) {
        	
        	player1 = new Human(nameP1, colorP1,size);
        	player2 = new Human(nameP2,colorP2,size);
        	
        }else if(type1.equals("Human")&& type2.equals("Miedosa")) {
        	
        	player1 = new Human(nameP1, colorP1,size);
        	player2 = new Fearful(nameP2, colorP2,size);
        	
        }else if(type1.equals("Human")&& type2.equals("Agresiva")) {
        	
        	player1 = new Human(nameP1, colorP1,size);
        	player2 = new Agressive(nameP2, colorP2,size);
        	
        }else if(type1.equals("Human")&& type2.equals("Experta")) {
        	
        	player1 = new Human(nameP1, colorP1,size);
        	player2 = new Expert(nameP2, colorP2,size);
        	
        }
        
	}
	
	
	public Box getBox(int i, int j) {
		return tablero.getBox(i,j);
	}
	
	
	public Player[] getPlayers() {
	    Player[] players = {player1, player2};
	    return players;
	}
	
	public char[][] getBoardP1() {
	    char[][] piedras = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (player1.getPlayerFichas()[i][j] != null && player1.getPlayerFichas()[i][j].getFicha() != null) {
	                piedras[i][j] = player1.getPlayerFichas()[i][j].getType();
	            } else {
	                piedras[i][j] = '-'; 
	            }
	        }
	    }
	    return piedras;
	}
	
	
	public char[][] getBoardP2() {
	    char[][] piedras = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (player2.getPlayerFichas()[i][j] != null && player2.getPlayerFichas()[i][j].getFicha() != null) {
	                piedras[i][j] = player2.getPlayerFichas()[i][j].getType();
	            } else {
	                piedras[i][j] = '-'; 
	            }
	        }
	    }
	    return piedras;
	}
	

	public int getTurn() {
		
		return turn;
	}
	
	public Box[][] getBoard() {
		return tablero.getTablero();
	}
	
	public char[][] getTablero(){
		return tablero.getBoard();
	}
	
	public char[][] getBoardWithSymbols(){
		return tablero.getBoardWithSymbols();
	}
	
	
	public int getSize() {
		return tablero.getSize();
	}
	public Player getPlayerOnTurn(){
		if(turn % 2 == 0) {
			return player2;
		}else {
			return player1;
		}
	}
} 