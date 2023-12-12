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
	

	public GomokuPOOS(String nameP1,String colorP1, String nameP2,String colorP2,String modoJuego,int size,String type1,String type2,double porcentaje){
	tablero = new Board(size,porcentaje);	
	addPlayers(nameP1,colorP1,nameP2,colorP2,type1,type2,tablero);
	turn = 1;
	this.size = size;
	}

	
	public void play(int row,int column,String type) throws GomokuPOOSException{
		if(type == null) {throw new GomokuPOOSException(GomokuPOOSException.NOT_SELECTED_FICHA);}
		//if(win() == true) {throw new GomokuPOOSException(GomokuPOOSException.PLAYER_WIN);}
			if(turn % 2 == 0) {
				player2.play(row,column,type,tablero,'0');
				printBoard(tablero.colorsficha());
			
			}else {
				player1.play(row,column,type, tablero,'1');
				printBoard(tablero.colorsficha());

			}
			turn++;

	}
	
	private void isTemporary() {
		tablero.isTemporary();
	}
	
	
	/**
	public boolean win() {
        if (tablero.win()) {
            return true;
        }
        return false;
    }
	
	
	*/
	
	private void addPlayers(String nameP1,String colorP1,String nameP2,String colorP2,String type1, String type2, Board tablero){
           
        if(type1.equals("Human")&& type2.equals("Human")) {
        	
        	player1 = new Human(nameP1, colorP1);
        	player2 = new Human(nameP2,colorP2);
        	
        }else if(type1.equals("Human")&& type2.equals("Miedosa")) {
        	
        	player1 = new Human(nameP1, colorP1);
        	player2 = new Fearful(nameP2, colorP2);
        	
        }else if(type1.equals("Human")&& type2.equals("Agresiva")) {
        	
        	player1 = new Human(nameP1, colorP1);
        	player2 = new Agressive(nameP2, colorP2);
        	
        }else if(type1.equals("Human")&& type2.equals("Experta")) {
        	
        	player1 = new Human(nameP1, colorP1);
        	player2 = new Expert(nameP2, colorP2);
        	
        }
        
	}
	
	
	  public void printBoard(char[][] board) {
	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                if (board[i][j] != 0) {
	                    System.out.print(board[i][j] + " ");
	                } else {
	                    System.out.print("-");
	                }
	            }
	            System.out.println();
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
	
	public char[][] getTablero(){
		return tablero.getBoard();
	}
	public char[][] colorsficha(){
		return tablero.colorsficha();
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