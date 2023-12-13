package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Board{
	
	private Box[][] boxes;
	private int size;
	private int numeroCasillasEspeciales;

	public Board(int size,double porcentaje){
		boxes = new Box[size][size];
		this.size = size;
		initializeBoxes(porcentaje);
		
	}
	
	
	public boolean win() {
        char[][] stones = colorsficha();

        // Verifica filas y columnas
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - 5; j++) {
                // Verifica filas
                if (stones[i][j] == '1' && stones[i][j + 1] == '1' && stones[i][j + 2] == '1' &&
                    stones[i][j + 3] == '1' && stones[i][j + 4] == '1') {
                    return true;  // Jugador 1 ha ganado
                }

                // Verifica columnas
                if (stones[j][i] == '1' && stones[j + 1][i] == '1' && stones[j + 2][i] == '1' &&
                    stones[j + 3][i] == '1' && stones[j + 4][i] == '1') {
                    return true;  // Jugador 1 ha ganado
                }

                // Repite el proceso para el Jugador 2
                if (stones[i][j] == '0' && stones[i][j + 1] == '0' && stones[i][j + 2] == '0' &&
                    stones[i][j + 3] == '0' && stones[i][j + 4] == '0') {
                    return true;  // Jugador 2 ha ganado
                }

                if (stones[j][i] == '0' && stones[j + 1][i] == '0' && stones[j + 2][i] == '0' &&
                    stones[j + 3][i] == '0' && stones[j + 4][i] == '0') {
                    return true;  // Jugador 2 ha ganado
                }
            }
        }

        // Verifica diagonales
        for (int i = 0; i <= size - 5; i++) {
            for (int j = 0; j <= size - 5; j++) {
                // Diagonal de arriba a abajo
                if (stones[i][j] == '1' && stones[i + 1][j + 1] == '1' && stones[i + 2][j + 2] == '1' &&
                    stones[i + 3][j + 3] == '1' && stones[i + 4][j + 4] == '1') {
                    return true;  // Jugador 1 ha ganado
                }

                if (stones[i][j] == '0' && stones[i + 1][j + 1] == '0' && stones[i + 2][j + 2] == '0' &&
                    stones[i + 3][j + 3] == '0' && stones[i + 4][j + 4] == '0') {
                    return true;  // Jugador 2 ha ganado
                }

                // Diagonal de abajo a arriba
                if (stones[i + 4][j] == '1' && stones[i + 3][j + 1] == '1' && stones[i + 2][j + 2] == '1' &&
                    stones[i + 1][j + 3] == '1' && stones[i][j + 4] == '1') {
                    return true;  // Jugador 1 ha ganado
                }

                if (stones[i + 4][j] == '0' && stones[i + 3][j + 1] == '0' && stones[i + 2][j + 2] == '0' &&
                    stones[i + 1][j + 3] == '0' && stones[i][j + 4] == '0') {
                    return true;  // Jugador 2 ha ganado
                }
            }
        }

        return false;  // No hay ganador
    }
    
	
	public void play(int row, int column,String type,char jugador) throws GomokuPOOSException{
		boxes[row][column].play(type,jugador);
		if (type.equals("Heavy")){
			heavyEffect(row,column);
		}
		if(boxes[row][column]instanceof Mine){
			boxes[row][column].action(this);
		}else if(boxes[row][column] instanceof Teleport){
			boxes[row][column].action(this);
		}else if(boxes[row][column] instanceof Golden){
			boxes[row][column].action(this);
		}else if(boxes[row][column] instanceof NormalBox){
			boxes[row][column].action(this);
		}
		
	}
	
	public void heavyEffect(int i,int j){
		
		if (i-1 >= 0) {
			try{
			boxes[i-1][j].play("Normal",'2');
			boxes[i-1][j].setAfectada('T');
			}catch  (GomokuPOOSException e1){
			}
			if (j+1 < size) {
				try{
				boxes[i-1][j+1].play("Normal",'2');
				boxes[i-1][j+1].setAfectada('T');
				}catch  (GomokuPOOSException e1) {
				}
			}
		}
		if (j+1 < size) {
			try{
			boxes[i][j+1].play("Normal",'2');
			boxes[i][j+1].setAfectada('T');
			}catch  (GomokuPOOSException e1) {
			}
		}
		
	}
	
	
	public void verificarTemporales(){
		for (int i = 0; i< size; i++) {
			for (int j = 0; j< size; j++) {
				boxes[i][j].verificarTemporales();
				
			}
		}
	}
	
	public Box[][] getTablero(){
		return boxes;
	}
	
	
	//casillas
	public char[][] getBoardWithSymbols() {
	    char[][] symbols = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (boxes[i][j] != null) {
	                symbols[i][j] = boxes[i][j].getSymbol();
	            } else{
	                symbols[i][j] = '-';
	            }
	        }
	    }
	    return symbols;
	}
	
	public int casillasEspeciales(double porcentaje) {
        double casillasEspeciales = (size * size) * porcentaje;
        numeroCasillasEspeciales = (int) casillasEspeciales;
        return (int) casillasEspeciales;
    }
	//color fichas
	public char[][] colorsficha() {
	    char[][] colors = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (boxes[i][j] != null && boxes[i][j].getFicha() != null) {
	                colors[i][j] = boxes[i][j].colorficha();
	            } else {
	                colors[i][j] = '-'; 
	            }
	        }
	    }
	    return colors;
	}
	//fichas
	public char[][] getBoard() {
	    char[][] piedras = new char[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (boxes[i][j] != null  && boxes[i][j].getFicha() != null) {
	                piedras[i][j] = boxes[i][j].getType();
	            } else {
	                piedras[i][j] = '-'; 
	            }
	        }
	    }
	    return piedras;
	}
	
	public int getSize () {
		return size;
	}
	
	public Box getBox(int i, int j) {
		return boxes[i][j];
	}

	/**
	 * Initializes the special tiles on the game board.
	 *
	 * @param casillasE The number of special tiles to be placed.
	 */		
	public void iniciarCasillasEspeciales(double casillasE) {
	    Random rand = new Random();
	    int parar = 0;

	    while (parar < casillasE) {
	        int posicionX = rand.nextInt(size);
	        int posicionY = rand.nextInt(size);
	        //System.out.println(posicionX);
	        //System.out.println(posicionY);

	        if (boxes[posicionX][posicionY] == null) {
	            int casillaEspecial = rand.nextInt(3) + 1;
	            setCasillasEspeciales(casillaEspecial, posicionX, posicionY);
	            parar++;
	        }
	    }
	}


    /**
     * Sets the specified special tile on the game board.
     *
     * @param casillaEspecial The type of special tile.
     * @param posicionX The x-coordinate of the tile.
     * @param posicionY The y-coordinate of the tile.
     */
	public void setCasillasEspeciales(int casillaEspecial, int posicionX, int posicionY) {
	    if (casillaEspecial == 1) {
	        boxes[posicionX][posicionY] = new Golden();
	        boxes[posicionX][posicionY].setPosition(posicionX, posicionY);
	        //boxes[posicionX][posicionY].init(this);
	    } else if (casillaEspecial == 2) {
	        boxes[posicionX][posicionY] = new Golden();
	        boxes[posicionX][posicionY].setPosition(posicionX, posicionY);
	        //boxes[posicionX][posicionY].init(this);
	    } else if (casillaEspecial == 3) {
	        boxes[posicionX][posicionY] = new Teleport();
	        boxes[posicionX][posicionY].setPosition(posicionX, posicionY);
	        //boxes[posicionX][posicionY].init(this);
	    }
	}

	private void initializeBoxes(double porcentaje) {
		int casillasE = casillasEspeciales(porcentaje);
		iniciarCasillasEspeciales(casillasE);
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if(boxes[i][j]== null) {
            		boxes[i][j] = new NormalBox();
            		boxes[i][j].setPosition(i,j);
            		}
            		
            	}
            }
		boxes[1][1] = new Teleport();
        }
	}
	

