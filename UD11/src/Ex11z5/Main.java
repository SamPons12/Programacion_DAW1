package Ex11z5;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("11.5");
    frame.setVisible(true);
    frame.setSize(500, 600);

    JPanel panellPrincipal = (JPanel) frame.getContentPane();


    panellPrincipal.setLayout(new GridBagLayout());
    JTextArea cajaTexto = new JTextArea("Area texto");
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weighty = 1.0;
    panellPrincipal.add (cajaTexto, constraints);
    constraints.weighty = 0.0;



    JButton boton1 = new JButton ("Boton 1");
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.anchor = GridBagConstraints.NORTH;
    constraints.fill = GridBagConstraints.NONE;
    panellPrincipal.add (boton1, constraints);
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weighty = 0.0;

    JButton boton2 = new JButton ("Boton 2");
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weighty = 1.0;
    constraints.anchor = GridBagConstraints.CENTER;
    panellPrincipal.add (boton2, constraints);
    constraints.weighty = 0.0;

    JButton boton3 = new JButton ("Boton 3");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    panellPrincipal.add (boton3, constraints);

    JButton boton4 = new JButton ("Boton 4");
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    panellPrincipal.add (boton4, constraints);

    JTextField campoTexto = new JTextField ("Campo texto");
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1.0;
    constraints.fill = GridBagConstraints.BOTH;
    panellPrincipal.add (campoTexto, constraints);
    
  }
  
}
