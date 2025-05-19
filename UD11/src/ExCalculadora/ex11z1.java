package ExCalculadora;

import javax.swing.*;
import java.awt.*;

public class ex11z1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(4, 4));
        frame.setVisible(true);


        frame.setTitle("Calculadora");
        JPanel panel = (JPanel) frame.getContentPane();

        JTextField display = new JTextField(20);
        JLabel label = new JLabel("Resultat: ");

        display.add(label);

        panel.add(display);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        panel.add(buttonPanel);
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonMultiply = new JButton("*");
        JButton buttonDivide = new JButton("/");
        JButton buttonEquals = new JButton("=");
        JButton buttonClear = new JButton("C");
        JButton buttonDot = new JButton(".");
        JButton buttonPercent = new JButton("%");
        JButton buttonSquareRoot = new JButton("√");
        JButton buttonPower = new JButton("^");
        JButton buttonOpenParenthesis = new JButton("(");
        JButton buttonCloseParenthesis = new JButton(")");
        JButton buttonPi = new JButton("π");
        JButton buttonE = new JButton("e");
        JButton buttonFactorial = new JButton("!");
        JButton buttonLog = new JButton("log");
        JButton buttonSin = new JButton("sin");
        JButton buttonCos = new JButton("cos");


        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(button0);
        buttonPanel.add(buttonPlus);
        buttonPanel.add(buttonMinus);
        buttonPanel.add(buttonMultiply);
        buttonPanel.add(buttonDivide);
        buttonPanel.add(buttonEquals);
        buttonPanel.add(buttonClear);
        buttonPanel.add(buttonDot);
        buttonPanel.add(buttonPercent);
        buttonPanel.add(buttonSquareRoot);
        buttonPanel.add(buttonPower);
        buttonPanel.add(buttonOpenParenthesis);
        buttonPanel.add(buttonCloseParenthesis);
        buttonPanel.add(buttonPi);
        buttonPanel.add(buttonE);
        buttonPanel.add(buttonFactorial);
        buttonPanel.add(buttonLog);
        buttonPanel.add(buttonSin);
        buttonPanel.add(buttonCos);



    }
}
