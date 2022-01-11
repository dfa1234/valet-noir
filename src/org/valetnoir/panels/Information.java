package org.valetnoir.panels;

import java.awt.Color;

import javax.swing.JLabel;

import org.valetnoir.lang.Messages;
import org.valetnoir.player.Player;

/**
 * Une classe panel d'informations
 * 
 * @author Djf
 * 
 */
public class Information extends javax.swing.JPanel {

	private static final long serialVersionUID = -3683411240495131183L;
	private String action;
	private JLabel lAction;
	private JLabel lNom;

	private Player player;

	/** Display les infos */
	public Information(Player p) {
		player = p;
		setBackground(Table.COULEUR_TAPIS);
		String affiche = ""; //$NON-NLS-1$
		if (player.getGames().get(player.getCurrentPlayedGame()).getCards()
				.size() == 0)
			affiche = Messages.getString("Information.1") + player.getNom() + Messages.getString("Information.2"); //$NON-NLS-1$ //$NON-NLS-2$
		else
			affiche = Messages.getString("Information.3") //$NON-NLS-1$
					+ player.getNom()
					+ Messages.getString("Information.4") //$NON-NLS-1$
					+ player.getGames().get(player.getCurrentPlayedGame())
							.getScore() + "."; //$NON-NLS-1$
		lNom = new JLabel(affiche);
		lNom.setForeground(Color.YELLOW);
		this.add(lNom);

		/*
		 * Autres panels... lJeu = new JLabel("Jeux numero " +
		 * (player.getCurrentPlayedGame() + 1)); lScore = new
		 * JLabel("Votre score actuel: " +
		 * player.getGames().get(player.getCurrentPlayedGame()) .getScore());
		 * action = ""; lAction = new JLabel(action); // Coloriage:
		 * 
		 * lScore.setForeground(Color.WHITE);
		 * lAction.setForeground(Color.WHITE); lJeu.setForeground(Color.WHITE);
		 * // Layout: this.setLayout(new GridLayout(5, 1)); // Ajout au panel:
		 * lScore
		 * .setVisible(player.getGames().get(player.getCurrentPlayedGame())
		 * .getCards().size()!=0); lJeu.setVisible(player.isSplit());
		 * this.add(lNom); this.add(lJeu); this.add(lScore); this.add(lAction);
		 */

	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
		this.lAction.setText(action);
		this.revalidate();
	}

}
