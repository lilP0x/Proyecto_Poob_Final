package domain;

public class GomokuPOOSException extends Exception {
	
	public static final String FICHA_ON_BOX = "la casilla jugada esta ocupada"; 
	
	
	public GomokuPOOSException(String message) {
		super(message);
		
	}
}
