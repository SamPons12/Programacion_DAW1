package Ex11z9;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelProfessor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField dniField;
	private JTextField textField_1;
	private JLabel nomField;
	private JTextField textField_2;
	private JLabel edatField;
	public static ArrayList<Professor> professors = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public PanelProfessor() {
		
		JLabel lblNewLabel = new JLabel("DADES DEL PROFESSOR");
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
		JRadioButton rdbtnMates = new JRadioButton("Matematiques");
		
		JRadioButton rdbtnCatala = new JRadioButton("Català");
		
		JRadioButton rdbtnHistoria = new JRadioButton("Historia");
		
		JRadioButton rdbtnCastella = new JRadioButton("Castella");
		
		group.add(rdbtnMates);
		group.add(rdbtnCatala);
		group.add(rdbtnHistoria);
		group.add(rdbtnCastella);
		
		JLabel lblNewLabel_4 = new JLabel("MATERIA");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSubmit = new JButton("ACCEPTAR");
		btnSubmit.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
					if (dniField.getText() != "" &&
					nomField.getText() != "" &&
					edatField.getText() != "" &&
					group.getSelection() != null) 
					{
						professors.add(new Professor(dniField.getText(), nomField.getText(), edatField.getText(), group.getSelection().toString()));
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
						.addComponent(rdbtnCastella, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnHistoria, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnCatala, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMates, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(rdbtnMates)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCatala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnHistoria))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addComponent(lblNewLabel_4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnCastella)
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(269, Short.MAX_VALUE)
					.addComponent(btnSubmit)
					.addContainerGap())
		);
		setLayout(groupLayout);
		

	}

}
