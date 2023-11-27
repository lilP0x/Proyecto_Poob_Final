package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GomokuPOOSGUI extends JFrame {

    private JLayeredPane pantallaInicial;
    private JButton inicio;
    private JLayeredPane pantallaModoJuego;
    public GomokuPOOSGUI() {
        prepareElements();
        pantallaInicial();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("GomokuPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width / 2, dimension.height / 2));
        setLocationRelativeTo(null);
        pantallaInicial();
    }

    public static void main(String[] args) {
        
            GomokuPOOSGUI gomoku = new GomokuPOOSGUI();
            gomoku.setVisible(true);
    }
    

    private void pantallaInicial() {
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
    	
    }
    private void prepareActions(){
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
        inicio.addActionListener(oyenteDeInicio);
    }
    private void actualizarDimensiones() {
        pantallaInicial();
        revalidate();
        repaint();
    }
}