package domain;


import java.lang.reflect.Constructor;

/**
 * The abstract class Box represents a cell on the game board in a Gomoku game. Each box can
 * contain a game piece (ficha) and provides methods to interact with and manipulate the pieces.
 * This class is part of the domain package and serves as the base class for different types of boxes.
 *
 * @author Pablo , Maria
 * @version 1.5
 */
public abstract class Box {

    // Attributes
    protected Ficha ficha;       
    protected Board tablero;  
    protected char type;         
    protected int row, column;
    protected char afectada = 'F';

    /**
     * Default constructor for the Box class.
     */
    public Box() {

    }

    /**
     * Places a game piece on the box based on the specified type and player.
     *
     * @param type     The type of the game piece (e.g., "Temporary", "Normal", "Heavy").
     * @param jugador  The player character ('X' or 'O') associated with the game piece.
     * @throws GomokuPOOSException if there is already a game piece on the box.
     */
    public void play(String type, char jugador) throws GomokuPOOSException {
        if (ficha == null) {
            if (type.equals("Temporary")) {
                ficha = new Temporary(jugador);
            } else if (type.equals("Normal")) {
                ficha = new NormalFicha(jugador);
            } else if (type.equals("Heavy")) {
                ficha = new Heavy(jugador);
            }
            // action();
        } else {
            throw new GomokuPOOSException(GomokuPOOSException.FICHA_ON_BOX);
        }
    }

    /**
     * Checks and removes temporary game pieces based on specific conditions.
     */
    public void verificarTemporales() {
        if (ficha instanceof Temporary) {
            int estado = ((Temporary) ficha).sum();
            if (estado > 2) {
                ficha = null;
            }
        }
    }

    /**
     * Sets the position of the box on the game board.
     *
     * @param row    The row index of the box on the game board.
     * @param column The column index of the box on the game board.
     */
    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public void setAfectada(char afetada) {
        this.afectada = afectada;
    }

    /**
     * Retrieves the row index of the box on the game board.
     *
     * @return The row index of the box.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column index of the box on the game board.
     *
     * @return The column index of the box.
     */
    public int getColumn() {
        return column;
    }
    public char getAfectada() {
    	return afectada;
    }
    /**
     * Sets a new game piece on the box.
     *
     * @param nuevaFicha The new game piece to be placed on the box.
     */
    public void setFicha(Ficha nuevaFicha) {
        this.ficha = nuevaFicha;
    }

    /**
     * Initializes the box with a reference to the game board.
     *
     * @param tablero The game board to which the box belongs.
     */
    public void init(Board tablero) {
        this.tablero = tablero;
    }

    /**
     * Changes the state of the game piece on the box (specific to Temporary game pieces).
     */
    public void changeState() {
        if (ficha instanceof Temporary) {
            ((Temporary) ficha).changeState();
        }
    }

    /**
     * Changes the count associated with the game piece on the box (specific to Temporary game pieces).
     */
    public void changeCont() {
        if (ficha instanceof Temporary) {
            ((Temporary) ficha).changeCont();
        }
    }

    /**
     * Removes a Temporary game piece from the box if the count condition is met.
     */
    public void removeTemporary() {
        if (ficha instanceof Temporary && ficha.getCont() >= 3) {
            setFicha(null);
        }
    }

    /**
     * Retrieves the game piece on the box.
     *
     * @return The game piece on the box.
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * Retrieves the symbol representing the type of the box.
     *
     * @return The symbol representing the type of the box.
     */
    public char getSymbol() {
        return type;
    }

    /**
     * Retrieves the type of the game piece on the box.
     *
     * @return The type of the game piece on the box.
     */
    public char getType() {
        return ficha.getType();
    }

    /**
     * Retrieves the color of the game piece on the box.
     *
     * @return The color of the game piece on the box.
     */
    public char colorficha() {
        return ficha.colorficha();
    }

    /**
     * Abstract method that represents the action to be performed on the game board when
     * interacting with a specific type of box.
     *
     * @param tablero The game board on which the action is performed.
     */
    public abstract void action(Board tablero);
}
