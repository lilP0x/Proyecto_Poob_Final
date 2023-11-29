/**
package presentation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GomokuPOOSGUI extends JFrame {

    private JLayeredPane pantallaInicial;
    
    
    //BOTONES
    //private JButton inicio;
    //private JButton continuar;
    private JButton playerVSplayer;
    private JButton playerVSbot;
    
    
    private JLayeredPane pantallaModoJuego;
    private JLabel fondo;
    private File archivoImagenFondo ;
    private String rutaCompletaFondo;
    private ImageIcon imagenIconoFondo;
    private Image imagenEscaladaFondo;
    private ImageIcon imagenEscaladaIconoFondo; 
    
    //FUENTES
    private Font fuenteTitulo;
    private Font fuenteBotones; 
    
    public GomokuPOOSGUI() {
        prepareElements();
        //prepareActions();
    }

    private void prepareElements() {
        setTitle("GomokuPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width , dimension.height ));
        setLocationRelativeTo(null);
        File rutaFuente = new File("src/recursos/Blackness.ttf");
        if (rutaFuente.exists()) {
            try {
                fuenteTitulo = Font.createFont(Font.TRUETYPE_FONT, rutaFuente);
                fuenteTitulo = fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/15f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteTitulo =new Font("Arial", Font.BOLD, 20);
        }
        File rutaFuenteB = new File("src/recursos/little Boy.otf");
        if (rutaFuenteB.exists()) {
            try {
                fuenteBotones = Font.createFont(Font.TRUETYPE_FONT, rutaFuenteB);
                fuenteBotones = fuenteBotones.deriveFont(Font.PLAIN, getWidth()/15f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteTitulo =new Font("Arial", Font.BOLD, 20);
        }
        fondo = new JLabel();
        archivoImagenFondo = new File("src/recursos/fondo.jpg");
        rutaCompletaFondo = archivoImagenFondo.getAbsolutePath();
        imagenIconoFondo = new ImageIcon(rutaCompletaFondo);
        imagenEscaladaFondo = imagenIconoFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imagenEscaladaIconoFondo = new ImageIcon(imagenEscaladaFondo);
        fondo.setIcon(imagenEscaladaIconoFondo);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        //pantallaInicial();
        preparePantallaSeleccionModoJuego();

    }

    public static void main(String[] args) {
        
            GomokuPOOSGUI gomoku = new GomokuPOOSGUI();
            gomoku.setVisible(true);
    }
    

   /* private void pantallaInicial() {
        if (pantallaInicial != null) {
            pantallaInicial.removeAll();
        } else {
            pantallaInicial = new JLayeredPane();
            getContentPane().add(pantallaInicial);
        }
        JLabel label = new JLabel();
        File archivoImagen = new File("src/recursos/pantalla1.jpg");
        String rutaCompleta = archivoImagen.getAbsolutePath();
<<<<<<< HEAD
        //System.out.println(rutaCompleta);
        //System.out.println("C:\\Users\\juanp\\OneDrive\\Escritorio\\Universidad\\POOB\\Proyecto Final\\GomokuPOOS\\src\\recursos");
=======
>>>>>>> e783a0ae927b0243d8a06a7ec5322f5a5de573cf
        ImageIcon imagenIcono = new ImageIcon(rutaCompleta);
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);
        label.setIcon(imagenEscaladaIcono);
        label.setBounds(0, 0, getWidth(), getHeight());
        JPanel firstLayer = new JPanel();
        firstLayer.add(label);

        inicio = new JButton();
        JButton continuar = new JButton();
        File rutaBotonI = new File("src/recursos/boton inicio.jpg");
        String rutabotonI = rutaBotonI.getAbsolutePath();
        ImageIcon imagenBotonI = new ImageIcon(rutabotonI);
        Image imagenEscaladaBOTONI = imagenBotonI.getImage().getScaledInstance((getWidth() / 2)-(getWidth() / 10),getHeight() / 6, Image.SCALE_SMOOTH);
        ImageIcon iniciB= new ImageIcon(imagenEscaladaBOTONI);
        inicio.setIcon(iniciB);
        
        File rutaBotonC = new File("src/recursos/continuar.png");
        String rutabotonC = rutaBotonC.getAbsolutePath();
        ImageIcon imagenBotonC = new ImageIcon(rutabotonC);
        Image imagenEscaladaBOTONC = imagenBotonC.getImage().getScaledInstance((getWidth() / 2)-(getWidth() / 10),getHeight() / 6, Image.SCALE_SMOOTH);
        ImageIcon continuarB= new ImageIcon(imagenEscaladaBOTONC);
        continuar.setIcon(continuarB);
        
        inicio.setPreferredSize(new Dimension((getWidth() / 2)-(getWidth() / 10), getHeight() / 6));
        continuar.setPreferredSize(new Dimension((getWidth() / 2)-(getWidth() / 10), getHeight() / 6));
        JPanel botones = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 0, 20); 
        botones.add(inicio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labels = new JLabel();
        labels.setPreferredSize(new Dimension( getWidth(),getHeight()/20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        botones.add(labels, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1000000 , 20, 0, 20); 
        botones.add(continuar, gbc);
        botones.setBackground(new Color(155,155,155,0));
       

        pantallaInicial = new JLayeredPane();
        pantallaInicial.setLayout(null);  
        firstLayer.setBounds(0, 0, getWidth(), getHeight());
        pantallaInicial.add(firstLayer, Integer.valueOf(1));
        botones.setBounds(0, getHeight() / 2 + getHeight() / 16, getWidth(), getHeight() / 2);
        pantallaInicial.add(botones, Integer.valueOf(2));

        add(pantallaInicial);
    }
    
    private void preparePantallaSeleccionModoJuego() {
        if (pantallaModoJuego != null) {
            pantallaModoJuego.removeAll();
        } else {
            pantallaModoJuego = new JLayeredPane();
            getContentPane().add(pantallaModoJuego);
        }

        // Fondo
        
        JLabel fondoModoJuego = new JLabel();
        fondoModoJuego.setIcon(imagenEscaladaIconoFondo);
        
        //BOTONES
        
        playerVSplayer = new JButton("Player VS Player");
        playerVSbot = new JButton("Player VS Bot");
        
        playerVSplayer.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 6));
        playerVSbot.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 6));
        JPanel botones = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        botones.add(playerVSplayer, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1000000 , 20, 0, 20);
        botones.add(playerVSbot, gbc);
        botones.setBackground(new Color(155,155,155,0));
        
        // Texto Encabezado
        JLabel textoPantallaModoJuego = new JLabel("Seleccione el modo del");
        JLabel textoPantallaModoJuego2 = new JLabel("juego");
        textoPantallaModoJuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego.setVerticalAlignment(SwingConstants.BOTTOM);
        textoPantallaModoJuego.setBounds(0,getHeight()/20,getWidth(), getHeight()/4);
        textoPantallaModoJuego2.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego2.setVerticalAlignment(SwingConstants.TOP);
        textoPantallaModoJuego2.setBounds(0,((getHeight()/6)+(getHeight()/20)),getWidth(), getHeight()/4);
        textoPantallaModoJuego.setFont(fuenteTitulo);
        textoPantallaModoJuego2.setFont(fuenteTitulo);
        
        botones.setBounds(0, getHeight() / 2 + getHeight() / 16, getWidth(), getHeight() / 2);
        pantallaModoJuego.add(botones, Integer.valueOf(2));
        fondoModoJuego.add(textoPantallaModoJuego, Integer.valueOf(2));
        fondoModoJuego.add(textoPantallaModoJuego2, Integer.valueOf(2));
        fondoModoJuego.setBounds(0, 0,getWidth(), getHeight());
        pantallaModoJuego.add(fondoModoJuego, Integer.valueOf(1));

        add(pantallaModoJuego);
    }

    private void exitWindow(){
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir", "¿Salir?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (result == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    private void prepareActions(){
    	 WindowAdapter oyenteDeSalidaW;
         oyenteDeSalidaW = new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 exitWindow();
             }
         };
         this.addWindowListener(oyenteDeSalidaW);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                actualizarDimensiones();
            }
        });
        ActionListener oyenteDeInicio;
        oyenteDeInicio = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                getContentPane().add(pantallaModoJuego);
                revalidate();
                repaint();
            }
        };
        //inicio.addActionListener(oyenteDeInicio);
    }
    private void actualizarDimensiones() {

    	preparePantallaSeleccionModoJuego();
        revalidate();
        repaint();
    }
}*/
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
public class GomokuPOOSGUI extends JFrame {
	
	//generales
	private JLayeredPane pantallaEnUso;
	private ImageIcon fondoImagen;
	private JLabel fondo;
	private Font fuenteTitulo;
	private Font fuenteBotones;
	
	//pantalla inicial
    private JLayeredPane pantallaInicial;
    private JButton inicio;
    private JButton continuar;
    private ImageIcon fondoImagenP; 
    
    //pantalla modo juego
    private JLayeredPane pantallaModoJuego;
    private JButton playerVSplayer;
    private JButton playerVSbot;
    
    //pantalla detalles de partida playervsplayer
    private JLayeredPane pantallaDetallesPvsP;
    
    
    public GomokuPOOSGUI() {
    	setTitle("GomokuPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width , dimension.height ));
        setLocationRelativeTo(null);
    	imagenEscalada();
    	prepareElements();
        //prepareActions();
    }
    
    public static void main(String[] args) {
        
        GomokuPOOSGUI gomoku = new GomokuPOOSGUI();
        gomoku.setVisible(true);
    }

    private void prepareElements() {
    	imagenEscaladaFONDO();
    	fuenteTitulo();
    	preparePantallaI();
    	preparePantallaModoJuego();
    	preparePantallaDetallesPartidaPlayerVSPlayer();
    	
    }
    
    private void fuenteTitulo() {
    	File rutaFuente = new File("src/recursos/Blackness.ttf");
        if (rutaFuente.exists()) {
            try {
                fuenteTitulo = Font.createFont(Font.TRUETYPE_FONT, rutaFuente);
                fuenteTitulo = fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/15f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteTitulo =new Font("Arial", Font.BOLD, 20);
        }
        File rutaFuenteB = new File("src/recursos/little Boy.otf");
        if (rutaFuenteB.exists()) {
            try {
                fuenteBotones = Font.createFont(Font.TRUETYPE_FONT, rutaFuenteB);
                fuenteBotones = fuenteBotones.deriveFont(Font.PLAIN, getWidth()/45f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteBotones =new Font("Arial", Font.BOLD, 20);
        }
    }
    
    private void preparePantallaI() {
    	pantallaInicial = new JLayeredPane();

    	JLabel fondoLabel = new JLabel();
    	fondoLabel.setIcon(fondoImagenP);
    	fondoLabel.setBounds(0,0, getWidth(),getHeight());
    	pantallaInicial.add(fondoLabel,Integer.valueOf(1));
    	prepareBotones();
    	//getContentPane().add(pantallaInicial);
    }
    
    private void boton() {
    	 File rutaBotonI = new File("src/recursos/boton inicio.jpg");
         String rutabotonI = rutaBotonI.getAbsolutePath();
    	 ImageIcon imagenBotonI = new ImageIcon(rutabotonI);
         Image imagenEscaladaBOTONI = imagenBotonI.getImage().getScaledInstance((getWidth() / 2)-(getWidth() / 10),getHeight() / 6, Image.SCALE_SMOOTH);
         ImageIcon iniciB= new ImageIcon(imagenEscaladaBOTONI);
         inicio.setIcon(iniciB);
         File rutaBotonC = new File("src/recursos/continuar.png");
         String rutabotonC = rutaBotonC.getAbsolutePath();
         ImageIcon imagenBotonC = new ImageIcon(rutabotonC);
         Image imagenEscaladaBOTONC = imagenBotonC.getImage().getScaledInstance((getWidth() / 2)-(getWidth() / 10),getHeight() / 6, Image.SCALE_SMOOTH);
         ImageIcon continuarB= new ImageIcon(imagenEscaladaBOTONC);
         continuar.setIcon(continuarB);
    }
    private void prepareBotones() {
    	inicio = new JButton();
        continuar = new JButton();
        inicio.setPreferredSize(new Dimension((getWidth() / 2)-(getWidth() / 10), getHeight() / 6));
        continuar.setPreferredSize(new Dimension((getWidth() / 2)-(getWidth() / 10), getHeight() / 6));
        boton();
        JPanel botones = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        botones.add(inicio, gbc);
        JLabel labels = new JLabel();
        labels.setPreferredSize(new Dimension( getWidth(),getHeight()/20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        botones.add(labels, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        botones.add(continuar, gbc);
        botones.setBackground(new Color(155,155,155,0));
        botones.setBounds(0, getHeight() / 2 + getHeight() / 25, getWidth(), getHeight() / 2);
        pantallaInicial.add(botones, Integer.valueOf(2));
    	
    }
    private void imagenEscalada(){
        File archivoImagen = new File("src/recursos/pantalla1.jpg");
        String rutaCompleta = archivoImagen.getAbsolutePath();
        ImageIcon imagenIcono = new ImageIcon(rutaCompleta);
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        fondoImagenP = new ImageIcon(imagenEscalada);
    }
    
    private void imagenEscaladaFONDO(){
	    fondo = new JLabel();
	    File archivoImagenFondo = new File("src/recursos/fondo.jpg");
	    String rutaCompletaFondo = archivoImagenFondo.getAbsolutePath();
	    ImageIcon imagenIconoFondo = new ImageIcon(rutaCompletaFondo);
	    Image imagenEscaladaFondo = imagenIconoFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon imagenEscaladaIconoFondo = new ImageIcon(imagenEscaladaFondo);
	    fondo.setIcon(imagenEscaladaIconoFondo);
	    fondo.setBounds(0, 0, getWidth(), getHeight());
    }
    

    
    private void preparePantallaModoJuego() {
    	pantallaModoJuego = new JLayeredPane();
    	pantallaModoJuego.add(fondo,Integer.valueOf(1));
    	textoPantalaModoJuego();
    	botonesTipoJuego();
    	//getContentPane().add(pantallaModoJuego);
    }
    
    private void textoPantalaModoJuego() {
    	JLabel textoPantallaModoJuego = new JLabel("Seleccione el modo del");
        JLabel textoPantallaModoJuego2 = new JLabel("juego");
        textoPantallaModoJuego.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego.setVerticalAlignment(SwingConstants.BOTTOM);
        textoPantallaModoJuego.setBounds(0,getHeight()/20,getWidth(), getHeight()/4);
        textoPantallaModoJuego2.setHorizontalAlignment(SwingConstants.CENTER);
        textoPantallaModoJuego2.setVerticalAlignment(SwingConstants.TOP);
        textoPantallaModoJuego2.setBounds(0,((getHeight()/6)+(getHeight()/20)),getWidth(), getHeight()/4);
        textoPantallaModoJuego.setFont(fuenteTitulo);
        textoPantallaModoJuego2.setFont(fuenteTitulo);
        pantallaModoJuego.add(textoPantallaModoJuego, Integer.valueOf(2));
        pantallaModoJuego.add(textoPantallaModoJuego2, Integer.valueOf(2));
    }
    
    private void botonesTipoJuego() {
    	JPanel botonesJuego = new JPanel();
    	playerVSplayer = new JButton("Player VS Player");
    	playerVSplayer.setBackground(new Color(205,133,63));
    	playerVSplayer.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	Border bordeGrueso = BorderFactory.createLineBorder(Color.BLACK, 10);
    	playerVSplayer.setBorder(bordeGrueso);
    	playerVSplayer.setFont(fuenteBotones);
        playerVSbot = new JButton("Player VS Bot");
        playerVSbot.setBackground(new Color(205,133,63));
        playerVSbot.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	playerVSbot.setBorder(bordeGrueso);
        playerVSbot.setFont(fuenteBotones);
        
        playerVSplayer.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 6));
        playerVSbot.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 6));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        botonesJuego.add(playerVSplayer, gbc);
        
        JLabel espacio = new JLabel();
        espacio.setPreferredSize(new Dimension(getWidth() / 10, getHeight() / 6));
        gbc.gridx = 1;
        gbc.gridy = 0;
        botonesJuego.add(espacio,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        botonesJuego.add(playerVSbot, gbc);
        botonesJuego.setBackground(new Color(155,155,155,0));
        
        botonesJuego.setBounds(0, getHeight() / 2, getWidth(), getHeight() / 2);
        pantallaModoJuego.add(botonesJuego, Integer.valueOf(2));
    	
    }
    
    private void preparePantallaDetallesPartidaPlayerVSPlayer() {
    	pantallaDetallesPvsP = new JLayeredPane();
    	pantallaDetallesPvsP.add(fondo,Integer.valueOf(1));
    	
    	getContentPane().add(pantallaDetallesPvsP);
    	
    }
}

