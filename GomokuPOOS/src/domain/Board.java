package domain;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Board{
	
	private Box[][] boxes;
	private int size;
	private int numeroCasillasEspeciales;

	public Board(int size,int porcentaje){
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
    public void iniciarCasillasEspeciales(int casillasE) {
        int parar = 0, casillaEspecial, posicionX, posicionY;
        while (parar < casillasE) {
            Random rand = new Random();
            casillaEspecial = rand.nextInt(1, 4);
            posicionX = rand.nextInt(size);
            posicionY = rand.nextInt(size);
            if (boxes[posicionX][posicionY] == null) {
                setCasillasEspeciales(casillaEspecial, posicionX, posicionY);
                parar++;
            } else if (boxes[posicionX][posicionY] == null) {
                casillaEspecial = rand.nextInt(1, 6);
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
        if (casillaEspecial == 1 && boxes[posicionX][posicionY] == null) {
            boxes[posicionX][posicionY] = new Golden();
        } else if (casillaEspecial == 2 && boxes[posicionX][posicionY] == null) {
            boxes[posicionX][posicionY] = new Mine();
        } else if (casillaEspecial == 3 && boxes[posicionX][posicionY] == null) {
            boxes[posicionX][posicionY] = new Teleport();
        }
    }
	
	
	private void initializeBoxes(int porcentaje) {
		iniciarCasillasEspeciales(porcentaje);
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boxes[i][j] = new NormalBox(); 
            }
        }
	}
	

}