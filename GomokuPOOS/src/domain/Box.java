package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;

public abstract class Box {
	protected Ficha ficha;
	protected Board tablero;
	protected char type;
	protected int row,column;
	
	public Box(){

	}

	public void play(String type,Color color) throws GomokuPOOSException {
		if(ficha == null) {
			if(type.equals("Temporary")) {
				ficha = new Temporary(color);
			}else if(type.equals("Normal")){
				ficha = new NormalFicha(color);
			}else if(type.equals("Heavy")) {
				ficha = new Heavy(color);
			}
			//action();
		}else {throw new GomokuPOOSException(GomokuPOOSException.FICHA_ON_BOX);
		}
		
	}
	
	public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
	
	
	public void setFicha(Ficha nuevaFicha) {
        this.ficha = nuevaFicha;
    }
    
	
	 public void init(Board tablero) {
	        this.tablero = tablero;
	    }
	
	public void changeState() {
		if(ficha instanceof Temporary) {
			((Temporary) ficha).changeState();
		}
	}
	
	public Ficha getFicha() {
		return ficha;
	}
	
	public char getSymbol() {
		return type;
	}
	
	public char getType(){
		return ficha.getType();
	}
	
	public abstract void action(Board tablero);
		
}
