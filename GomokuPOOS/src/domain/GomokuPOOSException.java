package domain;

public class GomokuPOOSException extends Exception {
	
	public static final String FICHA_ON_BOX = "La casilla jugada esta ocupada";
	public static final String PLAYER_WIN = "El jugador gano";
	
	
	public GomokuPOOSException(String message) {
		super(message);
		
	}
}
