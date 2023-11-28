package domain;

import java.lang.reflect.Constructor;

public abstract class Box {
	protected Ficha ficha;
	
	public Box(){

	}

	public void play(Ficha ficha) throws GomokuPOOSException {
		if(ficha.equals(null)) {
			this.ficha = ficha;
			action();
		}else {throw new GomokuPOOSException(GomokuPOOSException.FICHA_ON_BOX);
		}
		
	}
	
	public abstract void action();
		
}
