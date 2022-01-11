package org.valetnoir.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import org.valetnoir.dealer.Dealer;
import org.valetnoir.player.Player;

/**
 * Cette classe modelise une table de blackjack
 * 
 * @author Djf
 * 
 */
public class Table extends JPanel {
	
	private static final long serialVersionUID = -3021498129288998737L;
	public static final Color COULEUR_TAPIS = new Color(1, 50, 10);
	private ControlBoard controlBoard;
	private Player currentPlayer;
	private Dealer dealer;
	private Historique historique;
	private Information information;
	private JPanel pHisto;
	private GamePanel pJeuxCroupier, pJeuxJoueur;
	private List<Player> players;
	private JPanel pTab, pJeux, pInfo;
	private RaiseBoard raiseBoard;

	/**
	 * Construit la table
	 * 
	 * @param h
	 * @param cP
	 */
	public Table(List<Player> ps, Dealer d) {
		setBackground(COULEUR_TAPIS);
		setSize(300, 400);
		players = ps;
		currentPlayer = players.get(0);
		dealer = d;
		pHisto = new JPanel();
		historique = new Historique();
		pHisto.add(historique);
		pJeux = new JPanel();
		pJeux.setBackground(COULEUR_TAPIS);
		pJeux.setLayout(new GridLayout(2, 1));
		afficherNouvelleDonne(0);
		pInfo = new JPanel();
		pInfo.setBackground(COULEUR_TAPIS);
		afficherNouvelleInfos();
		// Tableau de bord
		pTab = new JPanel();
		pTab.setLayout(new GridLayout(2, 1));
		afficherNouveauxBoutons(0);

		JPanel haut = new JPanel();
		haut.setLayout(new BorderLayout());

		JPanel bas = new JPanel();
		bas.setLayout(new BorderLayout());

		haut.add(pJeux, BorderLayout.WEST);
		haut.add(pHisto, BorderLayout.EAST);
		haut.setBackground(COULEUR_TAPIS);

		bas.add(pInfo, BorderLayout.NORTH);
		bas.add(pTab, BorderLayout.SOUTH);
		bas.setBackground(COULEUR_TAPIS);

		// Layout
		this.setLayout(new BorderLayout());

		// Ajout
		this.add(haut, BorderLayout.NORTH);
		this.add(bas, BorderLayout.SOUTH);

	}

	/**
	 * Affiche les nouveaux boutons
	 */
	private void afficherNouveauxBoutons(int step) {
		pTab.removeAll();
		raiseBoard = new RaiseBoard(this.currentPlayer);
		controlBoard = new ControlBoard(this.currentPlayer, dealer, step);
		pTab.add(raiseBoard);
		pTab.add(controlBoard);
		// pTab.revalidate();
	}

	/**
	 * Affiche la nouvelle donne sur la table
	 * 
	 * @param step
	 *            première carte du dealer face cachée ou non
	 */
	private void afficherNouvelleDonne(int step) {
		// Jeux du croupier
		pJeuxCroupier = new GamePanel(dealer, step);
		// Jeux du joueur
		pJeuxJoueur = new GamePanel(currentPlayer, step);
		pJeux.removeAll();
		pJeux.setLayout(new VerticalLayout());
		pJeux.add(pJeuxCroupier);
		pJeux.add(pJeuxJoueur);
		// pJeux.revalidate();
	}

	/**
	 * Affiche les nouvelles infos
	 */
	private void afficherNouvelleInfos() {
		pInfo.removeAll();
		information = new Information(this.currentPlayer);
		pInfo.add(information);
		// pInfo.revalidate();
	}

	public Historique getHisto() {
		return historique;
	}

	/**
	 * Rafraichit la table
	 * 
	 * @param first
	 */
	public void refresh(int step) {
		afficherNouvelleDonne(step);
		afficherNouvelleInfos();
		afficherNouveauxBoutons(step);
		this.revalidate();
	}

	/**
	 * Change le joueur actif
	 * 
	 * @param currentPlayer
	 *            the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
