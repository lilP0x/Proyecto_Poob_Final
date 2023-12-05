package domain;

import java.awt.Color;
import java.lang.reflect.Constructor;

public abstract class Box {
	protected Ficha ficha;
	
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
			action();
		}else {//throw new GomokuPOOSException(GomokuPOOSException.FICHA_ON_BOX);
		}
		
	}
	
	public Ficha getFicha() {
		return ficha;
	}
	
	public char getType(){
		return ficha.getType();
	}
	
	public abstract void action();
		
}
