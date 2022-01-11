package org.valetnoir.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.valetnoir.data.Preferences;
import org.valetnoir.handler.HandlerGeneral;
import org.valetnoir.lang.Messages;

/**
 * La classe panel des parametres
 * 
 * @author Djf
 * 
 */
public class Parameters extends JPanel {
	private static final long serialVersionUID = 3806380081784054911L;
	private JButton bOk;
	private JComboBox combo;
	private JLabel lj1;
	private JLabel lj2;
	private JLabel lj3;
	private JLabel lj4;
	private JLabel lj5;
	private JLabel ljet;
	private JTextField tj1;
	private JTextField tj2;
	private JTextField tj3;
	private JTextField tj4;
	private JTextField tj5;
	private JTextField tjet;

	public Parameters() {
		setBackground(Table.COULEUR_TAPIS);
		lj1 = new JLabel(Messages.getString("Parameters.0")); //$NON-NLS-1$
		lj2 = new JLabel(Messages.getString("Parameters.1")); //$NON-NLS-1$
		lj3 = new JLabel(Messages.getString("Parameters.2")); //$NON-NLS-1$
		lj4 = new JLabel(Messages.getString("Parameters.3")); //$NON-NLS-1$
		lj5 = new JLabel(Messages.getString("Parameters.4")); //$NON-NLS-1$
		lj1.setForeground(Color.white);
		lj2.setForeground(Color.white);
		lj3.setForeground(Color.white);
		lj4.setForeground(Color.white);
		lj5.setForeground(Color.white);
		lj2.setVisible(false);
		lj3.setVisible(false);
		lj4.setVisible(false);
		lj5.setVisible(false);
		tj1 = new JTextField(20);
		tj2 = new JTextField(20);
		tj3 = new JTextField(20);
		tj4 = new JTextField(20);
		tj5 = new JTextField(20);
		tj2.setVisible(false);
		tj3.setVisible(false);
		tj4.setVisible(false);
		tj5.setVisible(false);
		tj1.setText(Messages.getString("Parameters.5")); //$NON-NLS-1$
		tj2.setText(Messages.getString("Parameters.6")); //$NON-NLS-1$
		tj3.setText(Messages.getString("Parameters.7")); //$NON-NLS-1$
		tj4.setText(Messages.getString("Parameters.8")); //$NON-NLS-1$
		tj5.setText(Messages.getString("Parameters.9")); //$NON-NLS-1$
		ljet = new JLabel(Messages.getString("Parameters.10")); //$NON-NLS-1$
		ljet.setForeground(Color.white);
		tjet = new JTextField(20);
		tjet.setText("" + 1000); //$NON-NLS-1$
		bOk = new JButton(Messages.getString("Parameters.12")); //$NON-NLS-1$
		bOk.setBackground(Color.LIGHT_GRAY);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
				if (arg0.getKeyCode() == 10) {
					Parameters.this.bOk.validate();
				}
			}
		});

		combo = new JComboBox();
		combo.setBackground(Color.LIGHT_GRAY);
		combo.addItem("1"); //$NON-NLS-1$
		combo.addItem("2"); //$NON-NLS-1$
		combo.addItem("3"); //$NON-NLS-1$
		combo.addItem("4"); //$NON-NLS-1$
		combo.addItem("5"); //$NON-NLS-1$
		combo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == combo) {
					switch (Integer.parseInt((String) combo.getSelectedItem())) {
					case 5:
						Parameters.this.lj5.setVisible(true);
						Parameters.this.tj5.setVisible(true);
					case 4:
						Parameters.this.lj4.setVisible(true);
						Parameters.this.tj4.setVisible(true);
					case 3:
						Parameters.this.lj3.setVisible(true);
						Parameters.this.tj3.setVisible(true);
					case 2:
						Parameters.this.lj2.setVisible(true);
						Parameters.this.tj2.setVisible(true);
					case 1:
						Parameters.this.lj1.setVisible(true);
						Parameters.this.tj1.setVisible(true);
						break;
					}

					switch (Integer.parseInt((String) combo.getSelectedItem())) {
					case 1:
						Parameters.this.lj2.setVisible(false);
						Parameters.this.tj2.setVisible(false);
					case 2:
						Parameters.this.lj3.setVisible(false);
						Parameters.this.tj3.setVisible(false);
					case 3:
						Parameters.this.lj4.setVisible(false);
						Parameters.this.tj4.setVisible(false);
					case 4:
						Parameters.this.lj5.setVisible(false);
						Parameters.this.tj5.setVisible(false);
						break;
					}
				}
				Parameters.this.repaint();

			}

		});

		bOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HandlerGeneral.setThreadSuspended(false);
				Preferences.nomJoueurs = new ArrayList<String>();
				Preferences.jetons = Integer.parseInt(Parameters.this.tjet
						.getText());
				Preferences.nbJoueurs = Integer
						.parseInt((String) Parameters.this.combo
								.getSelectedItem());
				switch (Preferences.nbJoueurs) {
				case 5:
					Preferences.nomJoueurs.add(0, tj5.getText());
				case 4:
					Preferences.nomJoueurs.add(0, tj4.getText());
				case 3:
					Preferences.nomJoueurs.add(0, tj3.getText());
				case 2:
					Preferences.nomJoueurs.add(0, tj2.getText());
				case 1:
					Preferences.nomJoueurs.add(0, tj1.getText());
					break;
				}

			}
		});
		// Layout
		this.setLayout(new GridLayout(8, 2));
		// Ajout des elements:
		this.add(ljet);
		this.add(tjet);
		JLabel ljoueurs = new JLabel(Messages.getString("Parameters.18")); //$NON-NLS-1$
		ljoueurs.setForeground(Color.white);
		this.add(ljoueurs);
		this.add(combo);
		this.add(lj1);
		this.add(tj1);
		this.add(lj2);
		this.add(tj2);
		this.add(lj3);
		this.add(tj3);
		this.add(lj4);
		this.add(tj4);
		this.add(lj5);
		this.add(tj5);
		this.add(new JLabel());
		this.add(bOk);
	}
}
