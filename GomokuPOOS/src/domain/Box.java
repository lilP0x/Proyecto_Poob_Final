package domain;

import java.lang.reflect.Constructor;

public class Box {
	private Ficha ficha;
	
	public Box(){

	}

	public void play(Ficha ficha) {
		if(ficha.equals(null)) {
			this.ficha = ficha;
		}
		
	}
		
}
