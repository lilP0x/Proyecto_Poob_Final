package domain;

import java.awt.*;
import java.util.HashMap;

public class Board{
	
	private Box[][] boxes;

	public Board(int size){
		boxes = new Box[size][size];
		
	}
	
	public void play(int row, int column,Ficha ficha){
		
		boxes[row][column].play(ficha);
		
		
	}
	
	

}