package domain;

import java.awt.Color;

public class Expert extends Machine {

	public Expert(String name, Color color, Board tablero) {
		super(name, color, tablero);
	}

	@Override
	public void play(int row, int column, String type) throws GomokuPOOSException {
		
	}

}
