package domain;

import java.awt.Color;

public class Expert extends Machine {

	public Expert(String name, Color color) {
		super(name, color);
	}

	@Override
	public void play(int row, int column, String type, Board tablero) throws GomokuPOOSException {
		
	}

}
