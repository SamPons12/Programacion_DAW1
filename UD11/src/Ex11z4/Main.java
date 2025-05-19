package Ex11z4;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
public static void main(String[] args) {
    
  JFrame frame = new JFrame("Calculadora");
  frame.setVisible(true);
  frame.setSize(new Dimension(500, 400));

  JPanel panelResultado = (JPanel) frame.getContentPane();
  panelResultado.setLayout(new GridLayout(2, 1));
  JTextField resultado = new JTextField("0");
  resultado.setBackground(Color.yellow);
  resultado.setHorizontalAlignment(SwingConstants.RIGHT);
  panelResultado.add(resultado);

  JPanel panellBotons = new JPanel();
  panelResultado.add(panellBotons);
  panellBotons.setLayout(new GridLayout(4, 4));
  
  JButton boto7 = new JButton("7");

    JButton boto8 = new JButton("8");
    JButton boto9 = new JButton("9");
    JButton botomas = new JButton("+");
    JButton boto4 = new JButton("4");
    JButton boto5 = new JButton("5");
    JButton boto6 = new JButton("6");
    JButton botomenys = new JButton("-");
    JButton boto3 = new JButton("3");
    JButton boto2 = new JButton("2");
    JButton boto1 = new JButton("1");
    JButton botomulti = new JButton("X");
    JButton boto0 = new JButton("0");
    JButton botoc = new JButton("C");
    JButton botoigual = new JButton("=");
    JButton botodividir = new JButton("/");
  
    panellBotons.add(boto7);
    panellBotons.add(boto8);
    panellBotons.add(boto9);
    panellBotons.add(botomas);
    panellBotons.add(boto4);
    panellBotons.add(boto5);
    panellBotons.add(boto6);
    panellBotons.add(botomenys);
    panellBotons.add(boto3);
    panellBotons.add(boto2);
    panellBotons.add(boto1);
    panellBotons.add(botomulti);
    panellBotons.add(boto0);
    panellBotons.add(botoc);
    panellBotons.add(botoigual);
    panellBotons.add(botodividir);


  }
}
