package domain;

public class Mine extends Box{
	
	public Mine (){
		type = 'm';
	}

	private void explode(Board board) {
	    int mineRow = getRow();
	    int mineColumn = getColumn();

	    
	    board.getBox(mineRow, mineColumn).setFicha(null);

	 
	    for (int i = mineRow - 1; i <= mineRow + 1; i++) {
	        for (int j = mineColumn - 1; j <= mineColumn + 1; j++) {

	            if (i >= 0 && i < board.getSize() && j >= 0 && j < board.getSize()) {
	                board.getBox(i, j).setFicha(null);
	            }
	        }
	    }
	}



    @Override
    public void action(Board tablero) {
        explode(tablero);
    }

	
}
