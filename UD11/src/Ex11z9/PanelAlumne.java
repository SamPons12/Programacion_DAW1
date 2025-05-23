package Ex11z9;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelAlumne extends JPanel{
	private JTextField dniField;
	private JTextField textField_1;
	private JLabel nomField;
	private JTextField textField_2;
	private JLabel edatField;
	public static ArrayList<Alumne> alumnes = new ArrayList<>();


	
	public PanelAlumne () {
		
		JLabel lblNewLabel = new JLabel("DADES DEL ALUMNE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		dniField = new JTextField();
		dniField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		nomField = new JLabel("NOM");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		edatField = new JLabel("EDAT");
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("ESO");
		
		JRadioButton rdbtnBatxillerat = new JRadioButton("Batxillerat");
		
		JRadioButton rdbtnGrauMitja = new JRadioButton("Grau Mitja");
		
		JRadioButton rdbtnGrauSuperior = new JRadioButton("Grau Superior");
		
		group.add(rdbtnGrauSuperior);
		group.add(rdbtnGrauMitja);
		group.add(rdbtnBatxillerat);
		group.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_4 = new JLabel("NIVELL");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSubmit = new JButton("ACCEPTAR");
		btnSubmit.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(group.getSelection().toString());
				if (dniField.getText() != "" &&
					nomField.getText() != "" &&
					edatField.getText() != "" &&
					group.getSelection() != null) 
				{
					alumnes.add(new Alumne(dniField.getText(), nomField.getText(), edatField.getText(), group.getSelection().toString()));
				}
				
			}
			
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(357, Short.MAX_VALUE)
					.addComponent(btnSubmit)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(nomField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(edatField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnGrauSuperior, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnGrauMitja, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnBatxillerat, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(dniField, Alignment.LEADING)
							.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(153, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dniField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nomField))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edatField))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(rdbtnNewRadioButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnBatxillerat)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnGrauMitja))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addComponent(lblNewLabel_4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnGrauSuperior)
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(269, Short.MAX_VALUE)
					.addComponent(btnSubmit)
					.addContainerGap())
		);
		setLayout(groupLayout);
		

	}
}
	
