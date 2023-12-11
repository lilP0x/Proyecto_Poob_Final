package domain;

public class Mine extends Box {
    public Mine() {
        type = 'm';
    }

    private void explotar(Board tablero) {
        int filaMina = getRow();
        int columnaMina = getColumn();

        tablero.getBox(filaMina, columnaMina).setFicha(null);

        // Fila superior
        int filaSuperior = filaMina - 1;
        if (filaSuperior >= 0) {
            // Izquierda
            int columnaIzquierda = columnaMina - 1;
            if (columnaIzquierda >= 0) {
                tablero.getBox(filaSuperior, columnaIzquierda).setFicha(null);
            }

            // Centro
            tablero.getBox(filaSuperior, columnaMina).setFicha(null);

            // Derecha
            int columnaDerecha = columnaMina + 1;
            if (columnaDerecha < tablero.getSize()) {
                tablero.getBox(filaSuperior, columnaDerecha).setFicha(null);
            }
        }

    }


        
        
        /*
        for (int i = mineRow - 1; i <= mineRow + 1; i++) {
            for (int j = mineColumn - 1; j <= mineColumn + 1; j++) {
                if (i >= 0 && i < board.getSize() && j >= 0 && j < board.getSize()) {
                    board.getBox(i, j).setFicha(null);
                }
            }
        }
        */
    

    @Override
    public void action(Board board) {
        explotar(board);
    }
}
