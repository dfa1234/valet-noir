package org.valetnoir.handler;

import java.util.ArrayList;
import java.util.List;

import org.valetnoir.data.Preferences;
import org.valetnoir.dealer.Dealer;
import org.valetnoir.lang.Messages;
import org.valetnoir.panels.GeneralFrame;
import org.valetnoir.panels.Historique;
import org.valetnoir.panels.ParametersFrame;
import org.valetnoir.player.Player;
import org.valetnoir.sound.Sound;

/**
 * Cette classe est le gestionnaire principale du jeux. Il initialise un nouveau
 * jeux avec de nouveau parametre Et fait appel à la classe "Partie" pour
 * debuter une nouvelle partie.
 * 
 * @author Djf
 * 
 */
public class HandlerGeneral extends Thread {
	private static boolean continuerdeJouer;
	private static boolean threadSuspended;
	public static void setContinuerdeJouer(boolean b) {
		continuerdeJouer = b;
	}

	public static void setThreadSuspended(boolean b) {
		threadSuspended = b;
	}

	private Historique h;

	@Override
	public void run() {
		// Lancement de la fenetre de parametres
		ParametersFrame parametersFrame = new ParametersFrame();
		parametersFrame.setVisible(true);
		setThreadSuspended(true);
		while (threadSuspended) {
			System.out.print(""); //$NON-NLS-1$
		}
		parametersFrame.setVisible(false);
		// Initialisation du jeux
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < Preferences.nomJoueurs.size(); i++) {
			Player player = new Player(i, Preferences.nomJoueurs.get(i),
					Preferences.jetons);
			players.add(player);
		}
		Dealer dealer = new Dealer();
		// Lancement fenetre principale
		Sound son=new Sound("/ouvertureporte1.wav");
		GeneralFrame generalFrame = new GeneralFrame(players, dealer);
		generalFrame.setVisible(true);
		// Display des infos:
		h = generalFrame.getTable().getHisto();
		h.ajouterTexte(Messages.getString("HandlerGeneral.1")); //$NON-NLS-1$
		h.ajouterTexte(Messages.getString("HandlerGeneral.2")); //$NON-NLS-1$
		for (String nom : Preferences.nomJoueurs) {
			h.ajouterTexte("\t" + nom); //$NON-NLS-1$
		}
		h.ajouterTexte(Messages.getString("HandlerGeneral.4") + Preferences.jetons); //$NON-NLS-1$
		// Lancement de la partie
		HandlerGeneral.continuerdeJouer = true;
		while (HandlerGeneral.continuerdeJouer) {
			@SuppressWarnings("unused")
			Partie partie = new Partie(players, dealer, generalFrame);
			Partie.setThreadSuspended(false);
		}
		// Fin du jeux
		h.ajouterTexte("________________________________"); //$NON-NLS-1$
		h.ajouterTexte(Messages.getString("HandlerGeneral.6")); //$NON-NLS-1$
		if (players.size() == 0) {
			h
					.ajouterTexte(Messages.getString("HandlerGeneral.7")); //$NON-NLS-1$
		} else {
			int max = 0;
			List<String> winners = new ArrayList<String>();
			for (Player player : players)
				if (player.getJetons() > max)
					max = player.getJetons();

			for (Player player : players)
				if (player.getJetons() == max)
					winners.add(player.getNom());

			for (String winner : winners)
				h.ajouterTexte(winner + Messages.getString("HandlerGeneral.8")); //$NON-NLS-1$
		}

	}

}
