package domain;

import java.util.Random;

public class Teleport extends Box {
	
	public Teleport() {
		type = 't';
	}
	

    @Override
    public void action(Board tablero) {
    	this.tablero = tablero;
        if (ficha != null) {
            teleportFicha();
        }
    }

    private void teleportFicha() {

    	
        int nuevaX = generarCoordenadaAleatoria();
        int nuevaY = generarCoordenadaAleatoria();

        tablero.getBox(nuevaX, nuevaY).setFicha(ficha);
        ficha = null; 
    }

    private int generarCoordenadaAleatoria() {
    	if(tablero == null) {
    		System.out.println("no se que putas wey");
    	}
        return new Random().nextInt(tablero.getSize());
    }
}
