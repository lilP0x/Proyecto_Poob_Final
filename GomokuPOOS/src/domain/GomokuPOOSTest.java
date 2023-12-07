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
        double porcentaje = 0.2;
        String tipo1 = "Human";
        String tipo2 = "Human";
        juego = new GomokuPOOS(nombreJugador1, colorJugador1, nombreJugador2, colorJugador2, modoDeJuego, tamañoTablero, tipo1, tipo2,porcentaje);
    }
/*

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
			
		}
    	
    	Box [][] aux = juego.getBoard();
  
    	// printBoard(juego.getTablero());
    	assertNotNull(aux[1][1].getFicha());
    //	assertEquals(1,juego.getTurn());
    	
    	
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
    
    
    private int countSpecialFichas(Box[][] board, Class<? extends Ficha> specialFichaClass) {
        int specialFichaCount = 0;
        for (Box[] row : board) {
            for (Box box : row) {
                if (box != null ) {
                	if(box.getFicha()!= null && specialFichaClass.isInstance(box));
                    specialFichaCount++;
                }
            }
        }
        return specialFichaCount;
    }
    
  @Test
  public void shouldPlaySpecialFichas() {
	  

  	try {
			juego.play(1, 1, "Normal");
			juego.play(1, 2, "Heavy");
			juego.play(1, 3, "Temporary");
			
		} catch (GomokuPOOSException e) {

		}
  	
  	
  	Box [][] aux = juego.getBoard();
  	printBoard(juego.getTablero());
  	assertNotNull(aux[1][1]);
  	assertNotNull(aux[1][2]);
  	assertNotNull(aux[1][3]);
  	

    int normalCount = countSpecialFichas(aux, NormalFicha.class);
    int heavyCount = countSpecialFichas(aux, Heavy.class);
    int temporaryCount = countSpecialFichas(aux, Temporary.class);


    assertTrue(normalCount > 0);
    assertTrue(heavyCount > 0);
    assertTrue(temporaryCount > 0);
  	
  	
	  
  }
    
    @Test
    public void shouldWin() {
    	String s = "";
        try {
            juego.play(0, 0, "Normal");
            juego.play(1, 0, "Normal");
            juego.play(0, 1, "Temporary");
            juego.play(1, 1, "Normal");
            juego.play(0, 2, "Heavy");
            juego.play(1, 2, "Normal");
            juego.play(0, 3, "Normal");
            juego.play(1, 3, "Normal");
            juego.play(0, 4, "Normal");
            juego.play(1, 4, "Normal");

            printBoard(juego.getTablero());

            
            

        } catch (GomokuPOOSException e) {
        	s = e.getMessage();
        }
        assertEquals("El jugador gano", s);
    }
   */
  
    public void printBoard(char[][] board) {
        for (int i = 0; i < juego.getSize(); i++) {
            for (int j = 0; j < juego.getSize(); j++) {
                if (board[i][j] != 0) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
 
 /*
    @Test
    public void shouldTeleportFicha() {
    	 printBoard(juego.getBoardWithSymbols());
    }
 
    
    /*
    @Test
    public void testTeleportBox() {
        try {
           
            juego.getBoard()[0][0] = new Teleport();

        
            juego.play(0, 0, "Normal");

       
            printBoard(juego.getBoardWithSymbols());
            System.out.println("-------------------------------------------");
            printBoard(juego.getTablero());
            assertNull(juego.getBoard()[0][0].getFicha());
            
        } catch (GomokuPOOSException e) {
            e.printStackTrace();
            fail("No debería lanzar una excepción aquí");
        }
    }
*/
 
    @Test
    public void testMineExplode() {
        try {
            // Agrega una casilla Mine en una ubicación específica
            juego.getBoard()[1][1] = new Mine();
            
            // Coloca fichas alrededor de la casilla Mine
            printBoard(juego.getTablero());
            System.out.println("-------------------------------------------");
            juego.play(0, 0, "Normal");
            juego.play(0, 1, "Normal");
            juego.play(0, 2, "Normal");
            juego.play(1, 0, "Normal");
            juego.play(1, 2, "Normal");
            juego.play(2, 0, "Normal");
            juego.play(2, 1, "Normal");
            juego.play(2, 2, "Normal");
            printBoard(juego.getTablero());
            System.out.println("-------------------------------------------");
            juego.play(1, 1, "Normal");
            printBoard(juego.getTablero());
            System.out.println("-------------------------------------------");
            printBoard(juego.getBoardWithSymbols());
           

            // Verifica que las fichas alrededor de la casilla Mine hayan desaparecido
            assertNull(juego.getBoard()[0][0].getFicha());
            assertNull(juego.getBoard()[0][1].getFicha());
            assertNull(juego.getBoard()[0][2].getFicha());
            assertNull(juego.getBoard()[1][0].getFicha());
            assertNull(juego.getBoard()[1][2].getFicha());
            assertNull(juego.getBoard()[2][0].getFicha());
            assertNull(juego.getBoard()[2][1].getFicha());
            assertNull(juego.getBoard()[2][2].getFicha());
        } catch (GomokuPOOSException e) {
            e.printStackTrace();
            fail("No debería lanzar una excepción aquí");
        }
    }

                 
        
}
