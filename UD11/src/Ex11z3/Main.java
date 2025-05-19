package Ex11z3;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("11.3");

    frame.setVisible(true);
    frame.setSize(380, 250);
    
    
    JPanel panellPrincipal = (JPanel) frame.getContentPane();
    panellPrincipal.setLayout(new BorderLayout());
    
    JButton botoNord = new JButton("Button 1(PAGE_START)");
    panellPrincipal.add(botoNord, BorderLayout.PAGE_START);
    JButton botoEst = new JButton("Button 3(LINE_START)");
    panellPrincipal.add(botoEst, BorderLayout.LINE_START);
    JButton botoSud = new JButton("Button 4(PAGE_END)");
    panellPrincipal.add(botoSud, BorderLayout.PAGE_END);
    JButton botoOest = new JButton("5(LINE_END)");
    panellPrincipal.add(botoOest, BorderLayout.LINE_END);

    JPanel panellAuxiliar = new JPanel();

    Box caixa = Box.createVerticalBox();

    panellAuxiliar.add(caixa);
    panellPrincipal.add(panellAuxiliar, BorderLayout.CENTER);
    JButton boto1 = new JButton("Central 1");
    caixa.add(boto1);
    JButton boto2 = new JButton("Central 2");
    caixa.add(boto2);
    JButton boto3 = new JButton("Central 3");
    caixa.add(boto3);
    JButton boto4 = new JButton("Central 4");
    caixa.add(boto4);
    JButton boto5 = new JButton("Central 5");
    caixa.add(boto5);


    
  }
}
