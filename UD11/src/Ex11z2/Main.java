package Ex11z2;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("11.2");
    frame.setVisible(true);
    frame.setResizable(true);
    frame.setSize(600 , 100);

    JPanel panellPrincipal = (JPanel) frame.getContentPane();
    panellPrincipal.setLayout(new FlowLayout());

    JLabel etiqueta1 = new JLabel("Counter: ");
    panellPrincipal.add(etiqueta1);
    JTextField text = new JTextField("0", 7);
    panellPrincipal.add(text);
    ButtonGroup grupo = new ButtonGroup();
    JRadioButton up = new JRadioButton();
    panellPrincipal.add(up);
    JLabel etiqueta2 = new JLabel("Up");
    panellPrincipal.add(etiqueta2);
    JRadioButton down = new JRadioButton();
    panellPrincipal.add(down);
    grupo.add(down);
    grupo.add(up);
    JLabel etiqueta3 = new JLabel("Down");
    panellPrincipal.add(etiqueta3);
    JLabel etiqueta4 = new JLabel("Step: ");
    panellPrincipal.add(etiqueta4);
    JComboBox step = new JComboBox<>(new String[] {"1", "2", "3", "4"});
    panellPrincipal.add(step);
    JButton boton = new JButton("Count");
    panellPrincipal.add(boton);

  }
}
