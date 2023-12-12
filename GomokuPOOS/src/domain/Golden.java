package domain;

public class Golden extends Box{
	
	public Golden() {
		
		type = 'g';
	}

	@Override
	public void action(Board tablero) {
		
	}

	
	@Override
    public Box copyBox() {
        Golden copiedBox = new Golden();
        // Copiar atributos específicos de Golden si los hay
        return copiedBox;
    }

}
