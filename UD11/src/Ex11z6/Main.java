package Ex11z6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("11.6");
    frame.setVisible(true);
    frame.setSize(400, 300);

    JPanel panellPrincipal = (JPanel) frame.getContentPane();
    panellPrincipal.setLayout(new BorderLayout());

    JLabel titol = new JLabel("DADES DE L'ALUMNE", SwingConstants.CENTER);
    panellPrincipal.add(titol, BorderLayout.NORTH);
    titol.setFont(new Font("DADES DE L'ALUMNE", Font.BOLD, 25));

    JPanel panellDatos = new JPanel(new GridBagLayout());
    
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    
    panellPrincipal.add(panellDatos,BorderLayout.CENTER);
    
    constraints.gridx = 0;
    constraints.gridy = 0;
    panellDatos.add(new JLabel("DNI"),constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
     panellDatos.add(new JTextField(" ",8),constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panellDatos.add(new JLabel("Nom"),constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
     panellDatos.add(new JTextField(" ",8),constraints);


    constraints.gridx = 0;
    constraints.gridy = 2;
    panellDatos.add(new JLabel("Edad"),constraints);

    constraints.gridx = 1;
    constraints.gridy = 2;
     panellDatos.add(new JTextField(" ",8),constraints);

    constraints.gridy = 3;
    constraints.gridx = 0;
    constraints.anchor =  GridBagConstraints.CENTER;
    panellDatos.add(new JLabel("Nivell"),constraints);

    
    JPanel panelNiveles = new JPanel();
    panelNiveles.setLayout(new BoxLayout(panelNiveles, BoxLayout.Y_AXIS));

    JRadioButton eso = new JRadioButton("ESO");
    JRadioButton buttonatxillerat = new JRadioButton("Batxillerat");
    JRadioButton grauMitja = new JRadioButton("Grau Mitja");
    JRadioButton grauSuperior = new JRadioButton("Grau Superior");

    ButtonGroup group = new ButtonGroup();
    group.add(eso);
    group.add(buttonatxillerat);
    group.add(grauMitja);
    group.add(grauSuperior);
    

    panelNiveles.add(eso);
    panelNiveles.add(buttonatxillerat);
    panelNiveles.add(grauMitja);
    panelNiveles.add(grauSuperior);

    constraints.gridx = 1;
    panellDatos.add(panelNiveles, constraints);
    
    JPanel panelBoton = new JPanel();
    panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT));
    panellPrincipal.add(panelBoton, BorderLayout.SOUTH);
    
    JButton botonAceptar = new JButton ("ACCEPTAR");
    panelBoton.add(botonAceptar);

  }

}
