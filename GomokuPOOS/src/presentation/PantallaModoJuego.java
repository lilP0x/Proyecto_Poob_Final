package presentation;

import java.awt.Image;
import java.io.File;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class PantallaModoJuego extends JLayeredPane {
	private Font fuenteTitulo;
	private JButton playerVSplayer;
	private JButton playerVSbot;
	private JLabel textoPantallaModoJuego;
	private JLabel textoPantallaModoJuego2;
	private JLabel fondo;
	private ImageIcon fondoImagen;
    int width, height;
    public PantallaModoJuego(Font fuente, int width, int height){
    	fuenteTitulo = fuente;
        this.width = width;
        this.height = height;
        imagenEscalada();
        fondo();
        add(fondo,Integer.valueOf(0)); 
        textoInferior();
        textoSuperior();


        // Agregar textos en una capa superior (capa 1)
        add(textoPantallaModoJuego2,Integer.valueOf(1));
        add(textoPantallaModoJuego,Integer.valueOf(1));
    }
    
    private void textoSuperior() {
        // Texto Encabezado
        textoPantallaModoJuego = new JLabel("Seleccione el modo del");
        
        textoPantallaModoJuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego.setVerticalAlignment(SwingConstants.BOTTOM);
        textoPantallaModoJuego.setBounds(0,getHeight()/20,getWidth(), getHeight()/4);
        textoPantallaModoJuego.setFont(fuenteTitulo);
    }
    private void textoInferior() {
        textoPantallaModoJuego2 = new JLabel("juego");
        textoPantallaModoJuego2.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego2.setVerticalAlignment(SwingConstants.TOP);
        textoPantallaModoJuego2.setBounds(0,((height/6)+(height/20)),width,height/4);
        textoPantallaModoJuego2.setFont(fuenteTitulo);
    }
    private void imagenEscalada(){
        File archivoImagen = new File("src/recursos/fondo.jpg");
        
        String rutaCompleta = archivoImagen.getAbsolutePath();
        System.out.println(rutaCompleta);
        ImageIcon imagenIcono = new ImageIcon(rutaCompleta);
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        fondoImagen = new ImageIcon(imagenEscalada);
    }

    private void fondo(){
        fondo = new JLabel();
        fondo.setIcon(fondoImagen);
        fondo.setBounds(0,0,width,height);
    }

}
