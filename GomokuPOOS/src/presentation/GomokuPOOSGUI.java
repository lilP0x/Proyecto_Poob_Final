package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.GomokuPOOS;


public class GomokuPOOSGUI extends JFrame{
	GomokuPOOS juego ;
	
	public GomokuPOOSGUI(){
		juego = new GomokuPOOS();
		prepareElements();
		prepareActions();
	}
		
	public static void main(String [] args){
		GomokuPOOSGUI gomoku = new GomokuPOOSGUI(); 
        gomoku.setVisible(true);
	}
	    

	    
	private void prepareElements() {
        setTitle("GomokuPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width / 2, dimension.height / 2));
        setLocationRelativeTo(null);
        
        //panel de inicio
        
        Image imagePanelInicial = new ImageIcon("/recursos/GOMOKU-Diseño_page-0001.JPG").getImage();
        //imagePanelInicial.setLayout(new BorderLayout()); // Utilizar BorderLayout para superponer componentes

	} 
    private void prepareActions() {}

}