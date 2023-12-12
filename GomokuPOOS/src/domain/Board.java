package domain;

import java.awt.*;
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
	
	public void play(int row, int column,String type,char jugador) throws GomokuPOOSException{
		boxes[row][column].play(type,jugador);
		isTemporary();
		removeTemporary();
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
	            if (boxes[i][j] != null && boxes[i][j].getFicha() != null) {
	                piedras[i][j] = boxes[i][j].getType();
	            } else {
	                piedras[i][j] = '-'; 
	            }
	        }
	    }
	    return piedras;
	}

	public void isTemporary() {
		for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	        	if(boxes[i][j].getFicha() instanceof Temporary) {
	            	boxes[i][j].changeCont();
	            	
	        		}
	            } 
			}
		}
	
	public void removeTemporary() {
		for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	        	if(boxes[i][j].getFicha() instanceof Temporary) {
	            		boxes[i][j].removeTemporary();
	        		}
	            } 
	        }
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
	/**
	public boolean win() {
		
        if (checkRows() || checkColumns() || checkDiagonals()) {
            return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j <= boxes.length - 5; j++) {
                if (checkSequence(boxes[i][j], boxes[i][j + 1], boxes[i][j + 2], boxes[i][j + 3], boxes[i][j + 4])){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i <= boxes.length - 5; i++) {
            for (int j = 0; j < boxes.length; j++) {
                if (checkSequence(boxes[i][j], boxes[i + 1][j], boxes[i + 2][j], boxes[i + 3][j], boxes[i + 4][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        for (int i = 0; i <= boxes.length - 5; i++) {
            for (int j = 0; j <= boxes.length - 5; j++) {
                if (checkSequence(boxes[i][j], boxes[i + 1][j + 1], boxes[i + 2][j + 2], boxes[i + 3][j + 3], boxes[i + 4][j + 4])) {
                    return true;
                }

                if (checkSequence(boxes[i][j + 4], boxes[i + 1][j + 3], boxes[i + 2][j + 2], boxes[i + 3][j + 1], boxes[i + 4][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    
    private boolean checkSequence(Box... boxes) {
        for (int i = 0; i < boxes.length - 1; i++) {
            if (boxes[i].getFicha() == null || boxes[i + 1].getFicha() == null || 
                (boxes[i].getFicha().color == null || boxes[i + 1].getFicha().color == null) ||
                !boxes[i].getFicha().color.equals(boxes[i + 1].getFicha().color)) {
                return false;
            }
        }
        return true;
    }

	*/
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
	

