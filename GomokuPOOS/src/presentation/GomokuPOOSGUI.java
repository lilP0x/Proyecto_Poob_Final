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
        System.out.println(getWidth());
        System.out.println(getHeight());
       
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
    

   /** private void pantallaInicial() {
        if (pantallaInicial != null) {
            pantallaInicial.removeAll();
        } else {
            pantallaInicial = new JLayeredPane();
            getContentPane().add(pantallaInicial);
        }
        JLabel label = new JLabel();
        File archivoImagen = new File("src/recursos/pantalla1.jpg");
        String rutaCompleta = archivoImagen.getAbsolutePath();
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
    }*/
    
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
}