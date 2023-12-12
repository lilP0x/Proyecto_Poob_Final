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

	public void play(String type,char jugador) throws GomokuPOOSException {
		if(ficha == null) {
			if(type.equals("Temporary")) {
				ficha = new Temporary(jugador);
			}else if(type.equals("Normal")){
				ficha = new NormalFicha(jugador);
			}else if(type.equals("Heavy")) {
				ficha = new Heavy(jugador);
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
	
	public void changeCont() {
		if(ficha instanceof Temporary) {
			((Temporary) ficha).changeCont();
		}
	}
	
	public void removeTemporary() {
		if(ficha instanceof Temporary && ficha.getCont() >= 3) {
			setFicha(null);
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
	public char colorficha() {
		return ficha.colorficha();
	}
	
	public abstract void action(Board tablero);
		
}
