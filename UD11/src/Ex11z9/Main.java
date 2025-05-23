package Ex11z9;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("puta bida");
		frame.setVisible(true);
		frame.setSize(450, 400);
		
		JPanel panellPrincipal = (JPanel) frame.getContentPane();
		CardLayout cl = new CardLayout();
		panellPrincipal.setLayout(cl);
		
		JPanel panellBlanco = new JPanel();
		
		PanelAlumne panellAlumno = new PanelAlumne();
		panellPrincipal.add(panellAlumno, "NouAlumne");
		PanelProfessor panellProfessor = new PanelProfessor();
		panellPrincipal.add(panellProfessor, "NouProfessor");
		
		JMenuBar menuBar = new JMenuBar	();
		
		JMenu menuAlumne = new JMenu("Alumne");
		menuBar.add(menuAlumne);
		
		JMenuItem menuItemAlumne = new JMenuItem("Nou Alumne");
		menuAlumne.add(menuItemAlumne);
		menuItemAlumne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panellPrincipal, "NouAlumne");
				
			}
			
		});
		
		JMenu menuProfessor = new JMenu("Professor");
		menuBar.add(menuProfessor);
		JMenuItem menuItemProfessor = new JMenuItem("Nou Professor");
		menuProfessor.add(menuItemProfessor);
		menuItemProfessor.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panellPrincipal, "NouProfessor");
				
			}
			
		});
		
		JMenu menuFitxer = new JMenu("Fitxer");
		menuBar.add(menuFitxer);

		JMenuItem menuItemEscriureFitxer = new JMenuItem("Escriure Fitxer");
		menuFitxer.add(menuItemEscriureFitxer);
		menuItemEscriureFitxer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File fileAlumne = new File("C:\\Users\\Sam\\Documents\\alumnes.dat");
				ObjectOutputStream outputAlumne = null;
				ObjectOutputStream outputProfessor = null;
				File fileProfessor = new File("C:\\Users\\Sam\\Documents\\professors.dat");

				try {
					if (!fileAlumne.exists()){
						fileAlumne.createNewFile();
					
					}else if (!fileProfessor.exists()){
						fileProfessor.createNewFile();
					}

					outputAlumne = new ObjectOutputStream(new FileOutputStream(fileAlumne));
					outputAlumne.writeObject(PanelAlumne.alumnes);

					outputProfessor = new ObjectOutputStream(new FileOutputStream(fileProfessor));
					outputProfessor.writeObject(PanelProfessor.professors);
					
				} catch (Exception x) {
					x.printStackTrace();
				}finally{
						try {
							if (outputAlumne != null) {outputAlumne.close();} 
							if (outputProfessor != null) {outputProfessor.close();}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				}
			}
			
		});
		JMenuItem menuItemLlegirFitxer = new JMenuItem("Llegir Fitxer");
		menuFitxer.add(menuItemLlegirFitxer);
		
		JMenu menuExit = new JMenu("Exit");
		menuBar.add(menuExit);
		
		frame.setJMenuBar(menuBar);
		
	
	}

}
