package domain;

import java.awt.*;
import java.util.HashMap;

public class Board{
	
	private Box[][] boxes;
	private int size;

	public Board(int size){
		boxes = new Box[size][size];
		this.size = size;
		initializeBoxes();
		
	}
	
	public void play(int row, int column,Ficha ficha) throws GomokuPOOSException{
		
		boxes[row][column].play(ficha);
		
		
	}
	
	public Box[][] getTablero(){
		return boxes;
	}
	
	
	private void initializeBoxes() { 
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boxes[i][j] = new NormalBox(); 
            }
        }
	}
	

}