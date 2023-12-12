package domain;

public class NormalBox extends Box{
	
	public NormalBox() {
		type = 'n';
	}

	@Override
	public void action(Board tablero) {

		
	}
	
	 @Override
	    public Box copyBox() {
	        NormalBox copiedBox = new NormalBox();
	        // Copiar atributos específicos de NormalBox si los hay
	        return copiedBox;
	    }
	

}
