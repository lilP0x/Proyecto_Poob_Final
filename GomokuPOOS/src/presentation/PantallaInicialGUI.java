package presentation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.io.File;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class PantallaInicialGUI extends JLayeredPane{
    JLabel fondo;
    ImageIcon fondoImagen;
    JButton inicio;
    JButton continuar;
    JPanel botones;
    int width, height;
    
    public PantallaInicialGUI(int width, int height){
        this.width = width;
        this.height = height;
        imagenEscalada();
        fondo();
        add(fondo,Integer.valueOf(0));
    }

    private void imagenEscalada(){
        File archivoImagen = new File("src/recursos/pantalla1.jpg");
        
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
