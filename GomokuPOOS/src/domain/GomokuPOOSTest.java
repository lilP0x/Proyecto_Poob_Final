package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GomokuPOOSTest {

    private static GomokuPOOS juego;

    @BeforeAll
    public static void setUp() {
        String nombreJugador1 = "Jugador1";
        Color colorJugador1 = Color.RED;
        String nombreJugador2 = "Jugador2";
        Color colorJugador2 = Color.BLUE;
        String modoDeJuego = "Modo Normal";
        int tamañoTablero = 15;
        int porcentaje = 2;
        String tipo1 = "Human";
        String tipo2 = "Human";
        juego = new GomokuPOOS(nombreJugador1, colorJugador1, nombreJugador2, colorJugador2, modoDeJuego, tamañoTablero, tipo1, tipo2,porcentaje);
    }

    @Test
    public void shouldAddPlayers() {

        // Act
    	
        Player[] players = juego.getPlayers();

        // Assert
        assertNotNull(players, "La array de jugadores no debería ser nulo");
        assertEquals(2, players.length, "Debería haber exactamente dos jugadores");

        Player player1 = players[0];
        Player player2 = players[1];

        assertNotNull(player1);
        assertNotNull(player2);

        assertEquals("Jugador1", player1.getName());
        assertEquals(Color.RED, player1.getColor());

        assertEquals("Jugador2", player2.getName());
        assertEquals(Color.BLUE, player2.getColor());
    }
    
    
    @Test
    public void shouldPlay(){
    	
    	try {
			juego.play(1, 1, "Normal");
		} catch (GomokuPOOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Box [][] aux = juego.getBoard();
    	assertNotNull(aux[1][1]);
    	
    	assertEquals(1,juego.getTurn());
    	
    	
    }
    
    @Test
    public void shouldNotPlay() {
        try {
            juego.play(1, 1, "Normal"); 
        } catch (GomokuPOOSException e) {
            assertEquals("La casilla jugada esta ocupada", e.getMessage());
        }

        Box[][] aux = juego.getBoard();
        assertNotNull(aux[1][1]);

        try {
            juego.play(1, 1, "Normal");
            fail("Se esperaba una excepción, pero no se lanzó.");
        } catch (GomokuPOOSException e) {
            assertEquals("La casilla jugada esta ocupada", e.getMessage());
        }
    }
    
    @Test
    public void shouldCreateSpecialBoxes() {
        
        Box[][] board = juego.getBoard();

        assertNotNull(board);

        int goldenCount = countSpecialBoxes(board, Golden.class);
        int mineCount = countSpecialBoxes(board, Mine.class);
        int teleportCount = countSpecialBoxes(board, Teleport.class);

        System.out.println("Golden count: " + goldenCount);
        System.out.println("Mine count: " + mineCount);
        System.out.println("Teleport count: " + teleportCount);

        assertTrue(goldenCount > 0);
        assertTrue(mineCount > 0);
        assertTrue(teleportCount > 0);
    }


    private int countSpecialBoxes(Box[][] board, Class<? extends Box> specialBoxClass) {
        int specialBoxesCount = 0;
        for (Box[] row : board) {
            for (Box box : row) {
                if (box != null && specialBoxClass.isInstance(box)) {
                    specialBoxesCount++;
                }
            }
        }
        return specialBoxesCount;
    }

        
}
