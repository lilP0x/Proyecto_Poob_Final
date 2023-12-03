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
	
	public void play(int row, int column,Ficha ficha) throws GomokuPOOSException{
		
		boxes[row][column].play(ficha);
		
		
	}
	
	public Box[][] getTablero(){
		return boxes;
	}
	
	public int casillasEspeciales(double porcentaje) {
        double casillasEspeciales = (size * size) * porcentaje;
        numeroCasillasEspeciales = (int) casillasEspeciales;
        return (int) casillasEspeciales;
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
	            int casillaEspecial = rand.nextInt(3) + 1;  // Generar números de 1 a 3
	            setCasillasEspeciales(casillaEspecial, posicionX, posicionY);
	            System.out.println(casillaEspecial);
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
	    } else if (casillaEspecial == 2) {
	        boxes[posicionX][posicionY] = new Mine();
	    } else if (casillaEspecial == 3) {
	        boxes[posicionX][posicionY] = new Teleport();
	    }
	}
	
	public boolean win() {
        // Verifica las filas, columnas y diagonales
        if (checkRows() || checkColumns() || checkDiagonals()) {
            return true;
        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j <= boxes.length - 5; j++) {
                if (checkSequence(boxes[i][j], boxes[i][j + 1], boxes[i][j + 2], boxes[i][j + 3], boxes[i][j + 4])) {
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
            if (boxes[i].getFicha() == null || boxes[i + 1].getFicha() == null || !boxes[i].getFicha().color.equals(boxes[i + 1].getFicha().color)) {
                return false;
            }
        }
        return true;
    }

	
	
	private void initializeBoxes(double porcentaje) {
		int casillasE = casillasEspeciales(porcentaje);
		iniciarCasillasEspeciales(casillasE);
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if(boxes[i][j]== null) {
            		boxes[i][j] = new NormalBox(); 
            	}
            }
        }
	}
	

}