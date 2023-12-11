package domain;

public class GomokuPOOSException extends Exception {
	
	public static final String FICHA_ON_BOX = "La casilla jugada esta ocupada";
	public static final String PLAYER_WIN = "El jugador gano";
	public static final String FICHA_NULL = "la ficha es nula";
	public static final String NO_MORE_FICHAS_REMAIN = "no te quedan fichas de ese tipo";
	
	public GomokuPOOSException(String message) {
		super(message);
		
	}
}
