package org.valetnoir.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.valetnoir.lang.Messages;
import org.valetnoir.player.Player;

public class RaiseBoard extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 6570274663706833906L;
	private final static String cancel = Messages.getString("RaiseBoard.0"); //$NON-NLS-1$
	private JLabel lJeton;
	private int mise;
	private JButton mise1;
	private JButton mise10;
	private JButton mise100;
	private JButton mise5;
	private JButton mise50;
	private JButton miseEffacer;
	private Player player;
	private JTextField tmise;

	public RaiseBoard(Player player) {
		setBackground(Table.COULEUR_TAPIS);
		this.player = player;
		mise1 = new JButton("1"); //$NON-NLS-1$
		mise5 = new JButton("5"); //$NON-NLS-1$
		mise10 = new JButton("10"); //$NON-NLS-1$
		mise50 = new JButton("50"); //$NON-NLS-1$
		mise100 = new JButton("100"); //$NON-NLS-1$

		mise1.setBackground(new Color(240, 250, 150));
		mise5.setBackground(new Color(250, 184, 127));
		mise10.setBackground(new Color(150, 130, 250));
		mise50.setBackground(new Color(132, 132, 250));
		mise100.setBackground(new Color(250, 100, 100));
		miseEffacer = new JButton(cancel);
		mise1.addActionListener(this);
		mise5.addActionListener(this);
		mise10.addActionListener(this);
		mise50.addActionListener(this);
		mise100.addActionListener(this);
		miseEffacer.addActionListener(this);
		tmise = new JTextField(5);
		mise = 0;
		tmise.setText("" + mise); //$NON-NLS-1$
		lJeton = new JLabel(Messages.getString("RaiseBoard.solde") + player.getJetons()); //$NON-NLS-1$
		lJeton.setForeground(Color.WHITE);

		// Boutons editables que si on se trouve avant la distrib des cartes
		setEnabled(player.getGames().get(player.getCurrentPlayedGame())
				.getCards().size() == 0);
		// Layout:
		this.setLayout(new GridLayout(1, 7));
		// Ajout au panel:
		this.add(lJeton);
		this.add(tmise);
		this.add(mise1);
		this.add(mise5);
		this.add(mise10);
		this.add(mise50);
		this.add(mise100);
		this.add(miseEffacer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton action = (JButton) e.getSource();
		int plus = 0;
		if (action.getText() == cancel) {
			RaiseBoard.this.mise = 0;
		} else {

			switch (Integer.parseInt(action.getText())) {
			case 1:
				plus = 1;
				break;
			case 5:
				plus = 5;
				break;
			case 10:
				plus = 10;
				break;
			case 50:
				plus = 50;
				break;
			case 100:
				plus = 100;
				break;
			}
			if ((mise + plus <= player.getJetons())
					&& (player.getJetons() - mise) >= 0)
				mise = mise + plus;
		}

		RaiseBoard.this.tmise.setText("" + RaiseBoard.this.mise); //$NON-NLS-1$
		lJeton.setText(Messages.getString("RaiseBoard.solde") + (player.getJetons() - mise)); //$NON-NLS-1$
		player.setMise(mise);
	}

	@Override
	public void setEnabled(boolean b) {
		mise1.setEnabled(b);
		mise5.setEnabled(b);
		mise10.setEnabled(b);
		mise50.setEnabled(b);
		mise100.setEnabled(b);
		miseEffacer.setEnabled(b);
	}

}
