 
package presentation;
import javax.swing.JButton;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import domain.GomokuPOOS;
import domain.GomokuPOOSException;
public class GomokuPOOSGUI extends JFrame {
	
	//generales
	private JLayeredPane pantallaEnUso;
	private ImageIcon fondoImagen;
	private JLabel fondo;
	private Font fuenteTitulo;
	private Font fuenteBotones;
	private Font fuenteGeneral;


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
    private JButton inicioJuegoPvsP;
    private JButton volverJuegoPvsP;
    private JPanel detalleTablero;
    private JTextField  nombreJugador1;
    private JComboBox  colorJugador1;
    private JTextField  nombreJugador2;
    private JComboBox  colorJugador2;
    private String color1;
    private String color2;	
	private JTextField nombreTablero;
	private JTextField porcentajeCasillasEspeciales;
	private JTextField porcentajePiedrasEspeciales;
    private JComboBox  dimensionesTablero;
    private JComboBox  tipoJuego;
    private String modoJuego;
    private String tiempo;
    private String numFichas;
    private JTextField tiempoJuego;
	private JTextField numeroFichas;
	private JPanel todo;
    
    //pantalla tablero
    private JLayeredPane pantallaTablero;
    private JButton salirJuego;
    private JButton reiniciarJuego;
    private JButton[][] tablero;
    private JPanel tableroGrafico;
    private JPanel player1Informacion;
    private JPanel player2Informacion;
    private String nombreTableroTEXTO;
    private String nombreJugador1TEXTO;
    private String nombreJugador2TEXTO;
    private String puntaje1;
    private String puntaje2;
    private char fichaSeleccionada1;
    private char fichaSeleccionada2;
    private JButton pesadaJugador2 ;
	private JButton normalJugador2;
	private JButton temporalJugador2;
	private JButton pesadaJugador1;
	private JButton normalJugador1;
	private JButton temporalJugador1;
	
    //ficahsJugadores
	private HashMap<Character, ImageIcon> negroMap;
	private HashMap<Character, ImageIcon> moradoMap;
	private HashMap<Character, ImageIcon> rosaMap;
	private HashMap<Character, ImageIcon> blancoMap;
	private HashMap<Character, ImageIcon> grisMap;
	private HashMap<Character, ImageIcon> jugador2Fichas;
	private HashMap<Character, ImageIcon> jugador1Fichas;
	//CasillasEspeciales
	private HashMap<Character, Color> casillasReference;
    //jueguito
    
    private GomokuPOOS juego;
    private ImageIcon fichaSeleccionadaJugar;
    private String tipoFichaJugado;
    
    
    
    
    public GomokuPOOSGUI() {

    	setTitle("GomokuPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width, dimension.height));
        setLocationRelativeTo(null);
        paquetefichasyCasillas();
    	prepareElements();
        prepareActions();
        juego = new GomokuPOOS(nombreJugador1TEXTO,"fg",nombreJugador2TEXTO,"fg","Normal",10,"Human","Human",0.3);
     
        
    }
    
    public static void main(String[] args) {
        
        GomokuPOOSGUI gomoku = new GomokuPOOSGUI();
        gomoku.setVisible(true);
    }
    private void paquetefichasyCasillas() {
    	//realiza el insert de los tipos de casillas
    	
        negroMap = new HashMap<>();
        moradoMap = new HashMap<>();
        rosaMap = new HashMap<>();
        blancoMap = new HashMap<>();
        grisMap = new HashMap<>();
        casillasReference = new HashMap<>();
        asignarFichas(negroMap, "NEGRA");
        asignarFichas(moradoMap, "MORADA");
        asignarFichas(rosaMap, "ROSADA");
        asignarFichas(blancoMap, "BLANCA");
        asignarFichas(grisMap, "GRIS");
        casillasReference.put('m',new Color(255,105,97));
        casillasReference.put('t',new Color(255,128,0));
        
    }
    private void asignarFichas(HashMap<Character, ImageIcon> colores, String color1) {
        colores.put('h', createImageIcon("src/recursos/PESADA" + color1.toUpperCase() + ".png"));
        colores.put('t', createImageIcon("src/recursos/TEMPORAL" + color1.toUpperCase() + ".png"));
        colores.put('n', createImageIcon("src/recursos/NORMAL" + color1.toUpperCase() + ".png"));
    }

    private ImageIcon createImageIcon(String path) {
	    File archivoImagen = new File(path);
        String rutaCompleta = archivoImagen.getAbsolutePath();
        ImageIcon imagenIcono = new ImageIcon(rutaCompleta);
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(75,75,Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
    
    
    
    private void prepareElements() {
    	fuenteTitulo();
    	imagenEscaladaFONDO();
    	imagenEscalada();
    	preparePantallaI();
    	funcionalidadesBotonesPantallaInicio();  
    	preparePantallaModoJuego();
    	funcionalidadesBotonesModoJuego();
    	preparePantallaDetallesPartidaPlayerVSPlayer();
    	preparePantallaDelTablero();
    	funcionalidadesBotonesDEtallePartidaPVSP();
    	
    }
    
    private void funcionalidadesBotonesPantallaInicio() {
    	ActionListener oyenteInicio;
        oyenteInicio = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(pantallaModoJuego);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        };
        inicio.addActionListener(oyenteInicio);
    }
    
    private void funcionalidadesBotonesModoJuego() {
    	ActionListener oyenteModoJuegoPVSP;
    	oyenteModoJuegoPVSP = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(pantallaDetallesPvsP);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        };
        playerVSplayer.addActionListener(oyenteModoJuegoPVSP);
    }
    

    
    private void nombresPantalla(String nombreJugador1TEXTO,String nombreJugador2TEXTO,String nombreTableroTEXTO) {
        JLabel textoTablero = new JLabel();
        textoTablero.setText(nombreTableroTEXTO);
        textoTablero.setBackground(Color.WHITE);
        Font ff = fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/45f);
        textoTablero.setFont(ff);
        textoTablero.setBounds(getWidth()/2 + getWidth()/10,0,getWidth(), getHeight()/4);
        pantallaTablero.add(textoTablero, Integer.valueOf(2));
        JLabel textotituloPantalla = new JLabel();
    	textotituloPantalla.setText(nombreJugador1TEXTO);
    	textotituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
    	textotituloPantalla.setVerticalAlignment(SwingConstants.BOTTOM);
    	textotituloPantalla.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),0,getWidth(), getHeight()/4);
    	textotituloPantalla.setFont(fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f));
    	player1Informacion.add(textotituloPantalla,BorderLayout.NORTH);
    	JLabel textotituloPantalla1 = new JLabel();
    	textotituloPantalla1.setText(nombreJugador2TEXTO);
    	textotituloPantalla1.setHorizontalAlignment(SwingConstants.CENTER);
    	textotituloPantalla1.setVerticalAlignment(SwingConstants.BOTTOM);
    	textotituloPantalla1.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),0,getWidth(), getHeight()/4);
    	textotituloPantalla1.setFont(fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f));
    	player2Informacion.add(textotituloPantalla1,BorderLayout.NORTH);
    }

    private void preguntaCaracteristicasEspecisles() {
    	
    }
    private void funcionalidadesBotonesDEtallePartidaPVSP() {
    	ActionListener oyenteGuardarDetallesPartida;
        oyenteGuardarDetallesPartida = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	guardarInformacionPartidaPVSP();
                getContentPane().removeAll();
                nombreJugador1TEXTO = nombreJugador1.getText();
                nombreJugador2TEXTO = nombreJugador2.getText();
                nombreTableroTEXTO = nombreTablero.getText();
                nombresPantalla(nombreJugador1TEXTO,nombreJugador2TEXTO, nombreTableroTEXTO);
            	color1 = (String) colorJugador1.getSelectedItem();
            	color2 = (String) colorJugador2.getSelectedItem();
            	String modo = (String) tipoJuego.getSelectedItem();
            	String especial = null;
            	if(modo.equals("Fichas limitadas")) {
            		especial = numeroFichas.getText();
            		System.out.println(especial);
            	}else if (modo.equals("Quicktime")){
            		especial = tiempoJuego.getText();
            	}
                prepareFichasJugador1(color1);
                prepareFichasJugador2(color2);
                fichasjugadores(color1);
                fichasjugadores(color2);
                getContentPane().add(pantallaTablero);
                getContentPane().revalidate();
                getContentPane().repaint();
                prepareActionFichasJugador2();
                prepareActionFichasJugador1();
                
                
            }
        };
        inicioJuegoPvsP.addActionListener(oyenteGuardarDetallesPartida);
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
            fuenteTitulo =new Font("Arial", Font.BOLD, getWidth()/45);
        }
        File rutaFuenteB = new File("src/recursos/little Boy.otf");
        if (rutaFuenteB.exists()) {
            try {
                fuenteBotones = Font.createFont(Font.TRUETYPE_FONT, rutaFuenteB);
                fuenteBotones = fuenteBotones.deriveFont(Font.PLAIN, getWidth()/45f );
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteBotones =new Font("Arial", Font.BOLD, getWidth()/45);
        }
        File rutaFuenteG = new File("src/recursos/fontastique.ttf");
        if (rutaFuenteG.exists()) {
            try {
                fuenteGeneral = Font.createFont(Font.TRUETYPE_FONT, rutaFuenteB);
                fuenteGeneral = fuenteGeneral.deriveFont(Font.PLAIN, getWidth()/45f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            fuenteGeneral =new Font("Arial", Font.BOLD, getWidth()/20);
        }
    }
    
    private void preparePantallaI() {
    	if (pantallaInicial != null) {
    		pantallaInicial .removeAll();
        } else {
        	pantallaInicial = new JLayeredPane();
        }

    	JLabel fondoLabel = new JLabel();
    	fondoLabel.setIcon(fondoImagenP);
    	fondoLabel.setBounds(0,0, getWidth(),getHeight());
    	pantallaInicial.add(fondoLabel,Integer.valueOf(1));
    	prepareBotones();
    	getContentPane().add(pantallaInicial);
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
    	if (pantallaModoJuego != null) {
    		pantallaModoJuego .removeAll();
        } else {
        	pantallaModoJuego = new JLayeredPane();
        }
    	JLabel fondo1 = new JLabel();
	    File archivoImagenFondo = new File("src/recursos/fondo.jpg");
	    String rutaCompletaFondo = archivoImagenFondo.getAbsolutePath();
	    ImageIcon imagenIconoFondo = new ImageIcon(rutaCompletaFondo);
	    Image imagenEscaladaFondo = imagenIconoFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon imagenEscaladaIconoFondo = new ImageIcon(imagenEscaladaFondo);
	    fondo1.setIcon(imagenEscaladaIconoFondo);
	    fondo1.setBounds(0, 0, getWidth(), getHeight());
    	pantallaModoJuego.add(fondo1,Integer.valueOf(1));
    	textoPantalaModoJuego();
    	botonesTipoJuego();
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
    	if (pantallaDetallesPvsP != null) {
    		pantallaDetallesPvsP .removeAll();
        } else {
        	pantallaDetallesPvsP = new JLayeredPane();
        }
    	JLabel fondo1 = new JLabel();
	    File archivoImagenFondo = new File("src/recursos/fondo.jpg");
	    String rutaCompletaFondo = archivoImagenFondo.getAbsolutePath();
	    ImageIcon imagenIconoFondo = new ImageIcon(rutaCompletaFondo);
	    Image imagenEscaladaFondo = imagenIconoFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon imagenEscaladaIconoFondo = new ImageIcon(imagenEscaladaFondo);
	    fondo1.setIcon(imagenEscaladaIconoFondo);
	    fondo1.setBounds(0, 0, getWidth(), getHeight());
    	pantallaDetallesPvsP.add(fondo1,Integer.valueOf(1));
    	detallesTablero();
    	preparebuttonsPvsp();
    	
    	
    }
    
    
    private void preparebuttonsPvsp() {
    	
    	JPanel botonesJuego = new JPanel();
    	inicioJuegoPvsP = new JButton("Iniciar");
    	inicioJuegoPvsP.setBackground(new Color(205,133,63));
    	inicioJuegoPvsP.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	Border bordeGrueso = BorderFactory.createLineBorder(Color.BLACK, getHeight()/100 );
    	inicioJuegoPvsP.setBorder(bordeGrueso);
    	inicioJuegoPvsP.setFont(fuenteBotones);
    	volverJuegoPvsP = new JButton("Volver");
    	volverJuegoPvsP.setBackground(new Color(205,133,63));
    	volverJuegoPvsP.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	volverJuegoPvsP.setBorder(bordeGrueso);
    	volverJuegoPvsP.setFont(fuenteBotones);
        
    	inicioJuegoPvsP.setPreferredSize(new Dimension(getWidth() / 6, getHeight() / 8));
        volverJuegoPvsP.setPreferredSize(new Dimension(getWidth() / 6, getHeight() / 8));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        botonesJuego.add(inicioJuegoPvsP, gbc);
        
        JLabel espacio = new JLabel();
        espacio.setPreferredSize(new Dimension(getWidth() / 4 + getWidth() / 22, getHeight() / 6));
        gbc.gridx = 1;
        gbc.gridy = 0;
        botonesJuego.add(espacio,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        botonesJuego.add(volverJuegoPvsP, gbc);
        botonesJuego.setBackground(new Color(155,155,155,0));

        botonesJuego.setBounds(0, getHeight() / 2+ getHeight() / 4, getWidth(), getHeight() / 2);
        pantallaDetallesPvsP.add(botonesJuego, Integer.valueOf(3));
    }
    
    private void detallesTablero() {
    	JPanel detalle = new JPanel();

    	JLabel textotituloPantalla = new JLabel("Detalles Partida");
    	textotituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
    	textotituloPantalla.setVerticalAlignment(SwingConstants.BOTTOM);
    	textotituloPantalla.setBounds(0,0,getWidth(), getHeight()/4);
    	textotituloPantalla.setFont(fuenteTitulo);
    	detalle.add(textotituloPantalla);
    	
    	detalleTablero = new JPanel();
    	detalleTablero.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()/2-(getWidth()/20)-(getWidth()/20)+(getWidth()/30), getHeight() / 2+getWidth()/50);
    	detalleTablero.setBackground(new Color(254,180,203,110));
    	detalleTablero.setLayout(new BorderLayout());
    	detalleTablero.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));

    	
    	JPanel player2 = new JPanel();
    	player2.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),getHeight() / 5+( getHeight() / 45)+getHeight() / 4+getHeight() / 27 , getWidth()/2-(getWidth()/20)-(getWidth()/20)+(getWidth()/30), getHeight() / 4);
    	player2.setBackground(new Color(254,180,203,110));
    	player2.setLayout(new BorderLayout());
    	player2.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));
    	
    	todo = new JPanel();
    	todo.setBackground(new Color(254,180,203,0));
    	detalleTableroNombre(todo); 
    	detalleTablerodimension(todo);
    	opcionesTablero1(todo);
    	opcionesTablero(todo);
    	opcionesTablero2(todo); 
    	player1();
    	player2();
    	detalleTablero.add(todo,BorderLayout.CENTER);
    	pantallaDetallesPvsP.add(detalleTablero,Integer.valueOf(2));

    	pantallaDetallesPvsP.add(player2,Integer.valueOf(2));
    	detalle.setBounds(0,0, getWidth(), getHeight() / 2);
    	detalle.setBackground(new Color(155,155,155,0));
    	pantallaDetallesPvsP.add(detalle,Integer.valueOf(2));
    }
    
    private void player1() {
    	JPanel player1 = new JPanel();
    	player1.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),getHeight() / 5+( getHeight() / 45), getWidth()/2-(getWidth()/20)-(getWidth()/20)+(getWidth()/30), getHeight() / 4);
    	player1.setBackground(new Color(254,180,203,110));
    	player1.setLayout(new BorderLayout());
    	player1.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));
    	pantallaDetallesPvsP.add(player1,Integer.valueOf(2));
    	Font player = fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f);
    	JLabel textotituloPantalla = new JLabel("Player 1");
    	textotituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
    	textotituloPantalla.setVerticalAlignment(SwingConstants.BOTTOM);
    	textotituloPantalla.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),0,getWidth(), getHeight()/4);
    	textotituloPantalla.setFont(player);
    	player1.add(textotituloPantalla,BorderLayout.NORTH);
    	JPanel auxiliar1 = new JPanel();
    	auxiliar1.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
    	auxiliar1.setBackground(new Color(254,180,203,0));
    	JLabel nombre = new JLabel("Nickname:");
    	nombre.setFont(fuenteGeneral);
    	nombre.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
    	auxiliar1.add(nombre);
    	nombreJugador1 = new JTextField(30);
    	nombreJugador1.setFont(fuenteGeneral);
    	nombreJugador1.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
    	auxiliar1.add(nombreJugador1);
    	player1.add(auxiliar1,BorderLayout.CENTER);
	    
    	JPanel auxiliar2 = new JPanel();
		auxiliar2.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
		auxiliar2.setBackground(new Color(254,180,203,0));
		JLabel colores = new JLabel("Color:");
		colores.setFont(fuenteGeneral);
		colores.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		auxiliar2.add(colores);
		
	    String[] color = {"Blanco","Negro","Rosa","Morado","Gris"};
	    colorJugador1 = new JComboBox<>(color);
	    colorJugador1.setFont(fuenteGeneral);
	    colorJugador1.setPreferredSize(new Dimension(getWidth()/2 - getWidth()/6 , getHeight() / 16));
	    auxiliar2.add(colorJugador1);
	    player1.add(auxiliar2,BorderLayout.SOUTH);
        
    	
    }
    private void player2() {
    	JPanel player2 = new JPanel();
    	player2.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),getHeight() / 5+( getHeight() / 45)+getHeight() / 4+getHeight() / 27 , getWidth()/2-(getWidth()/20)-(getWidth()/20)+(getWidth()/30), getHeight() / 4);
    	player2.setBackground(new Color(254,180,203,110));
    	player2.setLayout(new BorderLayout());
    	player2.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));
    	pantallaDetallesPvsP.add(player2,Integer.valueOf(2));
    	Font player = fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f);
    	JLabel textotituloPantalla = new JLabel("Player 2");
    	textotituloPantalla.setHorizontalAlignment(SwingConstants.CENTER);
    	textotituloPantalla.setVerticalAlignment(SwingConstants.BOTTOM);
    	textotituloPantalla.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)-(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),0,getWidth(), getHeight()/4);
    	textotituloPantalla.setFont(player);
    	player2.add(textotituloPantalla,BorderLayout.NORTH);
    	
    	JPanel auxiliar1 = new JPanel();
    	auxiliar1.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
    	auxiliar1.setBackground(new Color(254,180,203,0));
    	JLabel nombre = new JLabel("Nickname:");
    	nombre.setFont(fuenteGeneral);
    	nombre.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
    	auxiliar1.add(nombre);
    	nombreJugador2 = new JTextField(30);
    	nombreJugador2.setFont(fuenteGeneral);
    	auxiliar1.add(nombreJugador2);
    	player2.add(auxiliar1,BorderLayout.CENTER);
	    
    	JPanel auxiliar2 = new JPanel();
		auxiliar2.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
		auxiliar2.setBackground(new Color(254,180,203,0));
		JLabel colores = new JLabel("Color:");
		colores.setFont(fuenteGeneral);
		colores.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		auxiliar2.add(colores);
		
	    String[] color = {"Seleccione un color","Blanco","Negro","Rosa","Morado","Gris"};
	    colorJugador2 = new JComboBox<>(color);
	    colorJugador2.setFont(fuenteGeneral);
	    colorJugador2.setPreferredSize(new Dimension(getWidth()/2 - getWidth()/6 , getHeight() / 16));
	    auxiliar2.add(colorJugador2);
	    player2.add(auxiliar2,BorderLayout.SOUTH);
    }
    private void detalleTableroNombre(JPanel todo) {

    	GridBagConstraints gbc = new GridBagConstraints();

    	JPanel auxiliar1 = new JPanel();
    	auxiliar1.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
    	auxiliar1.setBackground(new Color(254,180,203,0));
    	JLabel nombre = new JLabel("Nombre:");
    	nombre.setFont(fuenteGeneral);
    	nombre.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
    	auxiliar1.add(nombre);
    	nombreTablero = new JTextField(30);
    	nombreTablero.setFont(fuenteGeneral);
    	nombreTablero.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
    	auxiliar1.add(nombreTablero);
        gbc.gridx = 0;
        gbc.gridy = 0;
        todo.add(auxiliar1,gbc);
    }
    
    
    private void detalleTablerodimension(JPanel todo) {
    	
	    JPanel auxiliar2 = new JPanel();
		auxiliar2.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
		auxiliar2.setBackground(new Color(254,180,203,0));
		JLabel dimension = new JLabel("Dimensiones:");
		dimension.setFont(fuenteGeneral);
		dimension.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		auxiliar2.add(dimension);
		
	    String[] dimensiones = {"10 x 10", "20 x 20","otra"};
	    dimensionesTablero = new JComboBox<>(dimensiones);
	    dimensionesTablero.setFont(fuenteGeneral);
	    dimensionesTablero.setPreferredSize(new Dimension(getWidth()/2 - getWidth()/6 , getHeight() / 16));
	    auxiliar2.add(dimensionesTablero);
	    
	    GridBagConstraints gbc = new GridBagConstraints();	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    todo.add(auxiliar2,gbc);

    }
    
    private void opcionesTablero1(JPanel todo) {
    	JPanel auxiliar3 = new JPanel();
    	GridBagConstraints gbc = new GridBagConstraints();
		auxiliar3.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
		auxiliar3.setBackground(new Color(254,180,203,0));
		JLabel modo = new JLabel("Tipo de juego:");
		modo.setFont(fuenteGeneral);
		modo.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		auxiliar3.add(modo);
	    String[] modos = {"Normal", "Fichas limitadas","Quicktime"};
	    tipoJuego = new JComboBox<>(modos);
	    tipoJuego.setFont(fuenteGeneral);
	    tipoJuego.setPreferredSize(new Dimension(getWidth()/2 - getWidth()/6 , getHeight() / 16));
	    auxiliar3.add(tipoJuego);
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    todo.add(auxiliar3,gbc);
    }
    private void opcionesTablero(JPanel todo) {
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	JPanel auxiliar5 = new JPanel();
    	auxiliar5.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
    	auxiliar5.setBackground(new Color(254,180,203,0));
    	JLabel pf = new JLabel("Porcentaje de piedras especiales:");
    	pf.setFont(fuenteGeneral);
    	pf.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
    	auxiliar5.add(pf);
    	
    	porcentajePiedrasEspeciales = new JTextField(18);
    	porcentajePiedrasEspeciales.setFont(fuenteGeneral);
    	porcentajePiedrasEspeciales.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
    	auxiliar5.add(porcentajePiedrasEspeciales);	  
        gbc.gridx = 0;
        gbc.gridy = 3;
        todo.add(auxiliar5,gbc);
    }
private void opcionesTablero2(JPanel todo) {
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	JPanel auxiliar5 = new JPanel();
    	auxiliar5.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
    	auxiliar5.setBackground(new Color(254,180,203,0));
    	JLabel pf = new JLabel("Porcentaje de casillas especiales:");
    	pf.setFont(fuenteGeneral);
    	pf.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
    	auxiliar5.add(pf);
    	
    	porcentajeCasillasEspeciales = new JTextField(18);
    	porcentajeCasillasEspeciales.setFont(fuenteGeneral);
    	porcentajeCasillasEspeciales.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
    	auxiliar5.add(porcentajeCasillasEspeciales);	  
        gbc.gridx = 0;
        gbc.gridy = 4;
        todo.add(auxiliar5,gbc);
    }

private void fechasLimit(JPanel todo) {
	GridBagConstraints gbc = new GridBagConstraints();
	JPanel auxiliar5 = new JPanel();
	auxiliar5.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
	auxiliar5.setBackground(new Color(254,180,203,0));
	JLabel pf = new JLabel("Numero de fichas para los jugadores:");
	pf.setFont(fuenteGeneral);
	pf.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
	auxiliar5.add(pf);
	
	numeroFichas = new JTextField(18);
	numeroFichas.setFont(fuenteGeneral);
	numeroFichas.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
	auxiliar5.add(numeroFichas);	  
    gbc.gridx = 0;
    gbc.gridy = 5;
    todo.add(auxiliar5,gbc);
	
}

//PANTALLA TABLERO
		
	private void preparePantallaDelTablero() {
		if (pantallaTablero != null) {
			pantallaTablero .removeAll();
	    } else {
	    	pantallaTablero = new JLayeredPane();
	    }
		
    	JLabel fondo1 = new JLabel();
	    File archivoImagenFondo = new File("src/recursos/fondo.jpg");
	    String rutaCompletaFondo = archivoImagenFondo.getAbsolutePath();
	    ImageIcon imagenIconoFondo = new ImageIcon(rutaCompletaFondo);
	    Image imagenEscaladaFondo = imagenIconoFondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon imagenEscaladaIconoFondo = new ImageIcon(imagenEscaladaFondo);
	    fondo1.setIcon(imagenEscaladaIconoFondo);
	    fondo1.setBounds(0, 0, getWidth(), getHeight());
		pantallaTablero.add(fondo1,Integer.valueOf(1));
		tableroGrafico = new JPanel(new GridLayout(10, 10,2, 2));
		tablero();
		player1Partida();
		player2Partida();
		puntajeJuagadores();
		botonesControlJuego();
		tableroGrafico.setBounds(getWidth()/20, getHeight() / 20, getWidth()/2+getWidth()/25, getHeight() / 2+getWidth()/5);
		tableroGrafico.setBackground(new Color(205,133,63));
		pantallaTablero.add(tableroGrafico, Integer.valueOf(2));	
	}
	
	private void tablero() {
	    tablero = new JButton[10][10];
	    int m = 10;
	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < m; j++) {
	            final int fila = i;  
	            final int columna = j;  
	            tablero[i][j] = new JButton();
	            JButton hola = tablero[i][j];
	            
	            tablero[i][j].addActionListener(new ActionListener() {
	                int auxX = fila;
	                int auxY = columna;  

	                @Override
	                public void actionPerformed(ActionEvent e) {
	      
	                    try {
	                    	juego.play(fila, columna, tipoFichaJugado);
	                        hola.setIcon(fichaSeleccionadaJugar);
	                        fichaSeleccionadaJugar = null;
	                        tipoFichaJugado = null;

	                        // Usar un temporizador en lugar de Thread.sleep()
	                        Timer timer = new Timer(500, new ActionListener() {
	                            @Override
	                            public void actionPerformed(ActionEvent arg0) {
	                                refresh();
	                                tableroGrafico.revalidate();
	                                tableroGrafico.repaint();
	                            }
	                        });
	                        
	                        timer.setRepeats(false); // Hacer que el temporizador se ejecute solo una vez
	                        timer.start();
	                        
	                    } catch (GomokuPOOSException e1) {
	                    	JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                        
	                        
	                    }
	                }
	            });
	            tablero[i][j].setBackground(new Color(250, 240, 230));
	            tableroGrafico.add(tablero[i][j]);
	        }
	    }
	}

    private void refresh() {
    	char[][] colores = juego.colorsficha();
    	char[][] tipo = juego.getTablero();
    	for (int i = 0; i < 10; i++) {
	        for (int j = 0; j < 10; j++) {
	        	JButton hola = tablero[i][j];
	            if (colores[i][j] == '1') {
	                hola.setIcon(jugador1Fichas.get(tipo[i][j]));
	            } else {
	            	hola.setIcon(jugador2Fichas.get(tipo[i][j]));
	            }
	        }
	    }
    	
    }
	
	private void player1Partida() {
		player1Informacion = new JPanel();
		player1Informacion.setBounds(getWidth()/2+getWidth()/10,getHeight() / 5+( getHeight() / 45), getWidth()/3+(getWidth()/60)-(getWidth()/20)+(getWidth()/30), getHeight() / 4);
		player1Informacion.setBackground(new Color(254,180,203,110));
		player1Informacion.setLayout(new BorderLayout());
		player1Informacion.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));
		pantallaTablero.add(player1Informacion,Integer.valueOf(2));
    	

	}
	
	private void player2Partida() {
		player2Informacion = new JPanel();
		player2Informacion.setBounds(getWidth()/2+getWidth()/10,getHeight() / 5+( getHeight() / 45)+getHeight() / 4+getHeight() / 27 ,  getWidth()/3+(getWidth()/60)-(getWidth()/20)+(getWidth()/30), getHeight() / 4);
		player2Informacion.setBackground(new Color(254,180,203,110));
		player2Informacion.setLayout(new BorderLayout());
		player2Informacion.setBorder(BorderFactory.createLineBorder(new Color(255,20,147)));
    	pantallaTablero.add(player2Informacion,Integer.valueOf(2));
    	
    	

		
	}
	
	private void botonesControlJuego() {
    	JPanel botonesJuego = new JPanel();
    	salirJuego = new JButton("Salir");
    	salirJuego.setBackground(new Color(205,133,63));
    	salirJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	Border bordeGrueso = BorderFactory.createLineBorder(Color.BLACK, 5);
    	salirJuego.setBorder(bordeGrueso);
    	salirJuego.setFont(fuenteBotones);
    	reiniciarJuego = new JButton("Reiniciar");
    	reiniciarJuego.setBackground(new Color(205,133,63));
    	reiniciarJuego.setBorder(javax.swing.BorderFactory.createLineBorder(new Color (255,105,180)));
    	reiniciarJuego.setBorder(bordeGrueso);
    	reiniciarJuego.setFont(fuenteBotones);
    	salirJuego.setBounds(getWidth()/20 + getWidth()/2-(getWidth()/20)+(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),getHeight()/2+getHeight()/4+getHeight()/20,getWidth()/6-getWidth()/20, getHeight()/13);
        reiniciarJuego.setBounds(getWidth()/20 +getWidth()/6+ getWidth()/2-(getWidth()/20)+(getWidth()/20)+ getWidth()/20+ getWidth()/30- (getWidth()/53),getHeight()/2+getHeight()/4+getHeight()/20,getWidth()/6-getWidth()/20, getHeight()/13);
        pantallaTablero.add(salirJuego, Integer.valueOf(2));
        pantallaTablero.add(reiniciarJuego, Integer.valueOf(2));
    }

	
	private void puntajeJuagadores() {
		puntaje1 = "0";
		puntaje2 = "0";
		JLabel puntajepersona1 = new JLabel("Puntaje: "+puntaje1);
		JLabel puntajepersona2 = new JLabel("Puntaje: "+puntaje2);
		puntajepersona1.setFont(fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f));
		puntajepersona2.setFont(fuenteTitulo.deriveFont(Font.PLAIN, getWidth()/40f));
		puntajepersona1.setHorizontalAlignment(SwingConstants.CENTER);
		puntajepersona2.setHorizontalAlignment(SwingConstants.CENTER);
		player1Informacion.add(puntajepersona1, BorderLayout.SOUTH);
		player2Informacion.add(puntajepersona2,BorderLayout.SOUTH);
	}

    
    private void prepareActions() {
        WindowAdapter oyenteDeSalidaW;
        oyenteDeSalidaW = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitWindow();
            }
        };
        this.addWindowListener(oyenteDeSalidaW);
        
        ActionListener oyenteModoJuego;
		oyenteModoJuego = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String modo = (String) tipoJuego.getSelectedItem();
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.gridx = 0;
	            gbc.gridy = 5;
	            JPanel auxiliar5 = new JPanel();
	        	auxiliar5.setBounds(getWidth()/20, getHeight() / 5+( getHeight() / 45), getWidth()-(getWidth()/20)-(getWidth()/20), getHeight() / 3);
	        	auxiliar5.setBackground(new Color(254,180,203,0));
	        	auxiliar5.setName("opcion");
	        	Component[] components = todo.getComponents();
	        	for (Component component : components) {
	        	    if (component.getName() != null && component.getName().equals("opcion")) {
	        	        todo.remove(component);
	        	    }
	        	}
	        	if(modo.equals("Fichas limitadas")) {
	        		
		        	JLabel pf = new JLabel("Numero de fichas para cada jugador:");
		        	pf.setFont(fuenteGeneral);
		        	pf.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		        	auxiliar5.add(pf);
		        	numeroFichas = new JTextField(5);
		        	numeroFichas.setFont(fuenteGeneral);
		        	numeroFichas.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
		        	auxiliar5.add(numeroFichas);
		        	
	        	}else if (modo.equals("Quicktime")) {
	        		
	        		JLabel pf = new JLabel("Tiempo de la partida:");
		        	pf.setFont(fuenteGeneral);
		        	pf.setBounds(getWidth()/20,0, getWidth()/20 , getHeight() / 16);
		        	auxiliar5.add(pf);
		        	tiempoJuego = new JTextField(10);
		        	tiempoJuego.setFont(fuenteGeneral);
		        	tiempoJuego.setBounds(getWidth()/20 +  getHeight() / 16,0, getWidth()/3 - getWidth()/6 , getHeight() / 16);
		        	auxiliar5.add(tiempoJuego);
	        	}
	        	todo.add(auxiliar5,gbc);
	            revalidate(); 
	            repaint();
	        }
	    };
	    tipoJuego.addActionListener(oyenteModoJuego);
	    verificacionCasillaEspecial();
	    verificacionPiedrasEspecial();
	    verificacionNombreJugador1();
	    verificacionNombreJugador2();
	    verificacionNombreTablero();
	    
	   
    }
    
    private void prepareActionFichasJugador1()  {
   	 ActionListener oyentePesadaJugador1 ;
   	 oyentePesadaJugador1 = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	seleccionFicha(pesadaJugador1, "Heavy");
	        }
	    };
	    pesadaJugador1.addActionListener(oyentePesadaJugador1 );	
	 ActionListener oyenteTemporalJugador1 ;
	 oyenteTemporalJugador1 = new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	seleccionFicha(temporalJugador1, "Temporary");
		        }
		    };
		temporalJugador1.addActionListener(oyenteTemporalJugador1 );	
	 ActionListener oyenteNormalJugador1 ;
	 oyenteNormalJugador1 = new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
		        	seleccionFicha(normalJugador1, "Normal");
		        }
		    };
		normalJugador1.addActionListener(oyenteNormalJugador1 );	
    }
    private void prepareActionFichasJugador2() {
	   	 ActionListener oyentePesadaJugador2 ;
	   	 oyentePesadaJugador2 = new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	seleccionFicha(pesadaJugador2, "Heavy");
		        }
		    };
		    pesadaJugador2.addActionListener(oyentePesadaJugador2 );	
		 ActionListener oyenteTemporalJugador2 ;
		 oyenteTemporalJugador2 = new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	seleccionFicha(temporalJugador2, "Temporary");
			        }
			    };
			temporalJugador2.addActionListener(oyenteTemporalJugador2 );	
		 ActionListener oyenteNormalJugador2 ;
		 oyenteNormalJugador2 = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			        	seleccionFicha(normalJugador2, "Normal");
			        }
			    };
			normalJugador2.addActionListener(oyenteNormalJugador2 );	
	}
    
    private void seleccionFicha(JButton boton, String tipo) {
    	fichaSeleccionadaJugar =  (ImageIcon) boton.getIcon();
        tipoFichaJugado = tipo;
        //System.out.println(tipoFichaJugado);
    }
    
    private void verificacionCasillaEspecial() {
        porcentajeCasillasEspeciales.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String piedraEspecial = porcentajeCasillasEspeciales.getText();
                try {
                    float especialCasilla = Float.parseFloat(piedraEspecial);
                } catch (NumberFormatException me) {
                    porcentajeCasillasEspeciales.setText("");
                    JOptionPane.showMessageDialog(null, "Error: El porcentaje de casillas especiales no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void verificacionPiedrasEspecial() {
        porcentajePiedrasEspeciales.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String casillaEspecial = porcentajePiedrasEspeciales.getText();
                try {
                    float especialPiedras = Float.parseFloat(casillaEspecial);
                } catch (NumberFormatException me) {
                    porcentajePiedrasEspeciales.setText("");
                    JOptionPane.showMessageDialog(null, "Error: El porcentaje de piedras especiales no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void verificacionNombreJugador1() {
        nombreJugador1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nombrejugador = nombreJugador1.getText();
                if(nombrejugador.equals("")) {
                    JOptionPane.showMessageDialog(null, "Error: El nombre del player 1 es nulo", "Error", JOptionPane.ERROR_MESSAGE);
                } 
               
            }
        });
    }
    private void verificacionNombreJugador2() {
        nombreJugador2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nombrejugador = nombreJugador2.getText();
                if(nombrejugador.equals("")) {
                    JOptionPane.showMessageDialog(null, "Error: El nombre del player 2 es nulo", "Error", JOptionPane.ERROR_MESSAGE);
                } 
               
            }
        });
    }
 
    private void verificacionNombreTablero() {
        nombreTablero.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nombretablero = nombreTablero.getText();
                if(nombretablero.equals("")) {
                    JOptionPane.showMessageDialog(null, "Error: El nombre del tablero es nulo", "Error", JOptionPane.ERROR_MESSAGE);
                } 
               
            }
        });
    }
    private void guardarInformacionPartidaPVSP() {
    	nombreTableroTEXTO = nombreTablero.getText();
    	nombreJugador1TEXTO = nombreJugador1.getText();
    	nombreJugador2TEXTO = nombreJugador2.getText();
    	
    }
    
    private void exitWindow(){
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quiere salir", "¿Salir?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else if (result == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
     
    }
    private JButton[] colors(String color1, JButton pesada,JButton temporal,JButton normal) {
    	pesada.setBackground(new Color(254,180,203,85));
    	temporal.setBackground(new Color(254,180,203,85));
    	normal.setBackground(new Color(254,180,203,85));
    	pesada.setPreferredSize(new Dimension(80, 80));
        temporal.setPreferredSize(new Dimension(80, 80));
        normal.setPreferredSize(new Dimension(80, 80));
    	if (color1.equals("Morado")){
    		pesada.setIcon(moradoMap.get('h'));
    		normal.setIcon(moradoMap.get('n'));
    		temporal.setIcon(moradoMap.get('t'));
    	}else if (color1.equals("Negro")){
    		pesada.setIcon(negroMap.get('h'));
    		normal.setIcon(negroMap.get('n'));
    		temporal.setIcon(negroMap.get('t'));
    	}else if (color1.equals("Rosa")){
    		pesada.setIcon(rosaMap.get('h'));
    		normal.setIcon(rosaMap.get('n'));
    		temporal.setIcon(rosaMap.get('t'));
    	}else if (color1.equals("Blanco")){
        	pesada.setIcon(blancoMap.get('h'));
        	normal.setIcon(blancoMap.get('n'));
        	temporal.setIcon(blancoMap.get('t'));
    	}else {
    		pesada.setIcon(grisMap.get('h'));
    		normal.setIcon(grisMap.get('n'));
    		temporal.setIcon(grisMap.get('t'));	
    	}
    	return new JButton[]{pesada, temporal, normal};
    }
    
    private HashMap<Character, ImageIcon> fichasjugadores(String color1){
    	HashMap<Character, ImageIcon> fichas;
    	 
    	if (color1.equals("Morado")){
    		fichas = moradoMap;
    	}else if (color1.equals("Negro")){
    		fichas = negroMap;
    	}else if (color1.equals("Rosa")){
    		fichas = rosaMap;
    	}else if (color1.equals("Blanco")){
    		fichas = blancoMap;
    	}else {
    		fichas = grisMap;
    	}
    	
    	return fichas;
    }
    
    private void prepareFichasJugador1(String color1) {
    	JPanel fichasDisponibles = new JPanel();
    	pesadaJugador1 = new JButton();
    	normalJugador1 = new JButton();
    	temporalJugador1 = new JButton();
    	pesadaJugador1 = colors(color1,pesadaJugador1,temporalJugador1,normalJugador1)[0];
    	normalJugador1 = colors(color1,pesadaJugador1,temporalJugador1,normalJugador1)[2];
    	temporalJugador1 = colors(color1,pesadaJugador1,temporalJugador1,normalJugador1)[1];
    	jugador1Fichas= fichasjugadores(color1);
    	
    
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        fichasDisponibles.add(pesadaJugador1,gbc);
        gbc.gridx = 2;
        fichasDisponibles.add(normalJugador1,gbc);
        gbc.gridx = 1;
        fichasDisponibles.add(temporalJugador1,gbc);
        fichasDisponibles.setBackground(new Color(15,15,15,0));
        player1Informacion.add(fichasDisponibles,BorderLayout.CENTER);

    }
    
    private void prepareFichasJugador2(String color1) {
    	JPanel fichasDisponibles = new JPanel();
    	pesadaJugador2 = new JButton();
    	normalJugador2 = new JButton();
    	temporalJugador2 = new JButton();
    	pesadaJugador2.setPreferredSize(new Dimension(80, 80));
        temporalJugador2.setPreferredSize(new Dimension(80, 80));
        normalJugador2.setPreferredSize(new Dimension(80, 80));
        pesadaJugador2 = colors(color1,pesadaJugador2,temporalJugador2,normalJugador2)[0];
    	normalJugador2 = colors(color1,pesadaJugador2,temporalJugador2,normalJugador2)[2];
    	temporalJugador2 = colors(color1,pesadaJugador2,temporalJugador2,normalJugador2)[1];
    	jugador2Fichas= fichasjugadores(color1);
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        fichasDisponibles.add(pesadaJugador2,gbc);
        gbc.gridx = 1;
        fichasDisponibles.add(normalJugador2,gbc);
        gbc.gridx = 2;
        fichasDisponibles.add(temporalJugador2,gbc);
        fichasDisponibles.setBackground(new Color(15,15,15,0));
        player2Informacion.add(fichasDisponibles,BorderLayout.CENTER);

    }
    
}

    


/**private class BotonActionListener implements ActionListener {
    private int fila;
    private int columna;

    public BotonActionListener(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}*/




