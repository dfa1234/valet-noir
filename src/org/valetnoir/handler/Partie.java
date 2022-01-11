package org.valetnoir.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.valetnoir.dealer.Dealer;
import org.valetnoir.lang.Messages;
import org.valetnoir.panels.ControlBoard;
import org.valetnoir.panels.GeneralFrame;
import org.valetnoir.panels.Historique;
import org.valetnoir.player.Game;
import org.valetnoir.player.Player;
import org.valetnoir.sound.Sound;

/**
 * Cette classe modélise une nouvelle partie, ou "manche"
 * 
 * @author Djf
 * 
 */
public class Partie {

	private static String action = ""; //$NON-NLS-1$

	public static final String DRAW = Messages.getString("Partie.1"); //$NON-NLS-1$

	public static final String LOST = Messages.getString("Partie.2"); //$NON-NLS-1$

	public static final String NO_RESULT = Messages.getString("Partie.3"); //$NON-NLS-1$

	public static int numPartie = 0;

	public static final int stepACTION = 2;

	public static final int stepAfterACTION = 7;
	public static final int stepAfterASSURANCE = 5;
	public static final int stepAfterDOUBLE = 6;
	public static final int stepAfterHIT = 3;

	public static final int stepAfterSPLIT = 4;
	public static final int stepNEWGAME = 8;
	public static final int stepRAISE = 1;
	private static boolean threadSuspended;

	public static final String WIN = Messages.getString("Partie.4"); //$NON-NLS-1$
	public static void setAction(String s) {
		action = s;
	}
	public static void setThreadSuspended(boolean b) {
		threadSuspended = b;
	}
	private Dealer dealer;
	private Historique h;
	private List<Player> players;
	private Map<Player, String> resultats;
	public Partie(List<Player> pls, Dealer d, GeneralFrame gf) {
		
		// INIT
		Partie.numPartie += 1;
		players = pls;
		dealer = d;
		h = gf.getTable().getHisto();
		resultats = new HashMap<Player, String>();
		for (Player player : players)
			resultats.put(player, NO_RESULT);
		dealer.getGames().get(0).getCards().clear();
		for (Player player : players)
			player.getGames().get(0).getCards().clear();
		// DEBUT
		h.ajouterTexte("________________________________"); //$NON-NLS-1$
		h.ajouterTexte(Messages.getString("Partie.6") + numPartie); //$NON-NLS-1$
		// LES MISES DE DEPART
		for (Player player : players) {
			gf.getTable().setCurrentPlayer(player);
			gf.getTable().refresh(stepRAISE);
			h.ajouterTexte(Messages.getString("Partie.7") + player + Messages.getString("Partie.8")); //$NON-NLS-1$ //$NON-NLS-2$
			attenteMise(player);
			player.setJetons(player.getJetons() - player.getMise());
		}

		// DISTRIBUTION DES CARTES
		dealer.giveCard(dealer);
		gf.getTable().refresh(stepRAISE);
		h.ajouterTexte(Messages.getString("Partie.9") + dealer.toString()); //$NON-NLS-1$
		h.ajouterTexte(Messages.getString("Partie.carte") + dealer.giveCard(dealer).toString() + Messages.getString("Partie.a") //$NON-NLS-1$ //$NON-NLS-2$
				+ dealer.toString());
		gf.getTable().refresh(stepRAISE);
		for (Player player : players) {
			h.ajouterTexte(Messages.getString("Partie.carte") + dealer.giveCard(player).toString() //$NON-NLS-1$
					+ Messages.getString("Partie.a") + player.toString()); //$NON-NLS-1$
			gf.getTable().refresh(stepRAISE);
			h.ajouterTexte(Messages.getString("Partie.carte") + dealer.giveCard(player).toString() //$NON-NLS-1$
					+ Messages.getString("Partie.a") + player.toString()); //$NON-NLS-1$
			gf.getTable().refresh(stepRAISE);
		}
		// ACTION DES JOUEURS
		for (Player player : players) {
			gf.getTable().setCurrentPlayer(player);
			gf.getTable().refresh(stepACTION);

			h.ajouterTexte(Messages.getString("Partie.16") + player + Messages.getString("Partie.17")); //$NON-NLS-1$ //$NON-NLS-2$

			// SI BLACK JACK OU BRULE RESULTAT IMMEDIAT
			resultats.put(player, finRapide(player));

			if (resultats.get(player) == NO_RESULT) {
				// Attente action utilisateur
				bloquerPartie(player);

				if (action.equals(ControlBoard.sINSURANCE)) {
					if (player.getJetons() >= player.getMise() / 2) {
						player.setAssurance(player.getMise() / 2);
						player.setJetons(player.getJetons()
								- player.getAssurance());
						h.ajouterTexte(player + Messages.getString("Partie.18") //$NON-NLS-1$
								+ player.getAssurance());
					} else {
						h.ajouterTexte(Messages.getString("Partie.19")); //$NON-NLS-1$
					}
					gf.getTable().refresh(stepAfterASSURANCE);
					bloquerPartie(player);

				}

				if (action.equals(ControlBoard.sSPLIT)) {
					splitter(player);
				}

				if (action.equals(ControlBoard.sDOUBLE)) {
					player.setJetons(player.getJetons() - player.getMise());
					player.setMise(player.getMise() * 2);
					h.ajouterTexte(Messages.getString("Partie.carte") //$NON-NLS-1$
							+ dealer.giveCard(player).toString() + Messages.getString("Partie.a") //$NON-NLS-1$
							+ player.toString());
					gf.getTable().refresh(stepAfterDOUBLE);
				}

				if (action.equals(ControlBoard.sHIT)) {
					while (action.equals(ControlBoard.sHIT)) {
						h.ajouterTexte(Messages.getString("Partie.carte") //$NON-NLS-1$
								+ dealer.giveCard(player).toString() + Messages.getString("Partie.a") //$NON-NLS-1$
								+ player.toString());
						gf.getTable().refresh(stepAfterHIT);
						if (player.getGames()
								.get(player.getCurrentPlayedGame()).getScore() >= 21)
							break;
						bloquerPartie(player);
						if (action.equals(ControlBoard.sSTAND))
							break;
					}

				}
				// SI BLACK JACK OU BRULE RESULTAT IMMEDIAT
				resultats.put(player, finRapide(player));
			}

		}

		// LE DEALER TIRE SES CARTES POUR CHAQUE JOUEUR
		for (Player player : players)
			if ((dealer.getGames().get(0).getScore() < 21)
					&& (resultats.get(player) == NO_RESULT)) {
				while (ia()) {
					gf.getTable().refresh(stepAfterACTION);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					h.ajouterTexte(Messages.getString("Partie.carte") //$NON-NLS-1$
							+ dealer.giveCard(dealer).toString() + Messages.getString("Partie.a") //$NON-NLS-1$
							+ dealer.toString());
					gf.getTable().refresh(stepAfterACTION);
				}
			}
		// SI LE DEALER BRULE OU FAIT BLACK JACK ON A UN RESULTAT IMMEDIAT
		for (Player player : players)
			if (resultats.get(player) == NO_RESULT)
				resultats.put(player, resultatTirageDealer());
		// SI ON A TOUJOURS AUCUN RESULTAT ON COMPARE LES SCORE
		for (Player player : players)
			if (resultats.get(player) == NO_RESULT)
				resultats.put(player, hasBestScore(player));

		// ON AFFICHE LE RESULTAT
		for (Player player : players) {
			int score = player.getGames().get(player.getCurrentPlayedGame())
					.getScore();
			if (resultats.get(player) == WIN) {
				h.ajouterTexte("" + score + ": " + player + Messages.getString("Partie.28")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				player.setJetons(player.getJetons() + 2 * player.getMise());
				Sound son=new Sound("/dring.wav");
			} else if (resultats.get(player) == LOST) {
				h.ajouterTexte("" + score + ": " + player + Messages.getString("Partie.31")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				player.setJetons(player.getJetons() + player.getAssurance());
				Sound son=new Sound("/verre-casse.wav");
			} else {
				h.ajouterTexte("" + score + ": " + player //$NON-NLS-1$ //$NON-NLS-2$
						+ Messages.getString("Partie.34")); //$NON-NLS-1$
				player.setJetons(player.getJetons() + player.getMise());
			}
			player.setMise(0);
			player.setAssurance(0);
			// SI JOUEUR PERD IL SORT
			if (player.getJetons() <= 0) {
				h.ajouterTexte(player
						+ Messages.getString("Partie.35")); //$NON-NLS-1$
				players.remove(player);
			}
			// S IL N Y A PLUS DE JOUEUR ON FINI LA PARTIE
			if (players.size() == 0) {
				HandlerGeneral.setContinuerdeJouer(false);
				break;
			}

		}

		gf.getTable().refresh(stepNEWGAME);
		setThreadSuspended(true);
		setAction(""); //$NON-NLS-1$
		while (threadSuspended) {
			System.out.print(""); //$NON-NLS-1$
		}
	}

	private int attenteMise(Player player) {
		bloquerPartie(player);
		if (action.equals(ControlBoard.sRAISE) && player.getMise() > 0) {
			h.ajouterTexte(player + Messages.getString("Partie.38") + player.getMise()); //$NON-NLS-1$
		} else {
			h.ajouterTexte(Messages.getString("Partie.39")); //$NON-NLS-1$
			return attenteMise(player);
		}
		return player.getMise();
	}

	private void bloquerPartie(Player player) {
		setThreadSuspended(true);
		setAction(""); //$NON-NLS-1$
		while (threadSuspended) {
			System.out.print(""); //$NON-NLS-1$
		}
		if (action.equals(ControlBoard.sINSURANCE))
			h.ajouterTexte(player + Messages.getString("Partie.42")); //$NON-NLS-1$
		else if (action.equals(ControlBoard.sDOUBLE))
			h.ajouterTexte(player + Messages.getString("Partie.43")); //$NON-NLS-1$
		else if (action.equals(ControlBoard.sSPLIT))
			h.ajouterTexte(player + Messages.getString("Partie.44")); //$NON-NLS-1$
		else if (action.equals(ControlBoard.sSTAND))
			h.ajouterTexte(player + Messages.getString("Partie.45")); //$NON-NLS-1$
		else if (action.equals(ControlBoard.sHIT))
			h.ajouterTexte(player + Messages.getString("Partie.46")); //$NON-NLS-1$
		else if (action.equals(ControlBoard.sDEAL))
			h.ajouterTexte(player + Messages.getString("Partie.47")); //$NON-NLS-1$
	}

	/**
	 * Teste si la partie ne doit pas s'achever avec un resultat precose,
	 * notamment si le joueur bust ou fait 21
	 */
	private String finRapide(Player player) {
		String resultat;
		if (player.getGames().get(player.getCurrentPlayedGame()).isBlackJack())
			resultat = WIN;
		else if (player.getGames().get(player.getCurrentPlayedGame()).isBust())
			resultat = LOST;
		else
			resultat = NO_RESULT;

		return resultat;
	}

	/**
	 * Compare le score du joueur à celui du croupier
	 * 
	 * @param player
	 * @return
	 */
	private String hasBestScore(Player player) {
		int scoreP = player.getGames().get(player.getCurrentPlayedGame())
				.getScore();
		int scoreD = dealer.getGames().get(0).getScore();
		if (scoreP > scoreD) {
			return WIN;
		} else if (scoreD > scoreP) {
			return LOST;
		} else {
			return DRAW;
		}
	}

	/**
	 * Renvoi vrai si le dealer doit encore retirer une carte
	 * 
	 * @return
	 */
	private boolean ia() {
		int score = dealer.getGames().get(0).getScore();
		if (score == 21) {
			h.ajouterTexte("" + score + Messages.getString("Partie.49")); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		} else if (score > 21) {
			h.ajouterTexte("" + score + Messages.getString("Partie.51")); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		} else if (score > 17) {
			h.ajouterTexte("" + score + Messages.getString("Partie.53")); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		} else if (score < 16) {
			h.ajouterTexte("" + score + Messages.getString("Partie.55")); //$NON-NLS-1$ //$NON-NLS-2$
			return true;
		} else {
			return true;
		}
	}

	/**
	 * Renvoi le resultat DU PLAYER en fonction du jeux du dealer
	 * 
	 * @return
	 */
	private String resultatTirageDealer() {
		int score = dealer.getGames().get(0).getScore();
		if (score == 21) {
			return LOST;
		} else if (score > 21) {
			return WIN;
		} else {
			return NO_RESULT;
		}
	}

	private void splitter(Player player) {
		Game premierGame = player.getGames().get(player.getCurrentPlayedGame());
		if (premierGame.canSplit()) {
			Game secondGame = new Game();
			switch (premierGame.getCards().size()) {
			case 2:
				secondGame.addNewCard(premierGame.getCards().get(1));
				dealer.giveCard(player);
				player.setCurrentPlayedGame(1);
				dealer.giveCard(player);
				player.setCurrentPlayedGame(0);
				break;
			case 3:
				if (Game.valeursEguales(premierGame.getCards().get(0),
						premierGame.getCards().get(1))) {
					secondGame.addNewCard(premierGame.getCards().get(1));
					premierGame.getCards()
							.remove(premierGame.getCards().get(1));
					dealer.giveCard(player);
					player.setCurrentPlayedGame(1);
					dealer.giveCard(player);
					player.setCurrentPlayedGame(0);
				} else if (Game.valeursEguales(premierGame.getCards().get(1),
						premierGame.getCards().get(2))) {
					secondGame.addNewCard(premierGame.getCards().get(2));
					premierGame.getCards()
							.remove(premierGame.getCards().get(2));
					dealer.giveCard(player);
					player.setCurrentPlayedGame(1);
					dealer.giveCard(player);
					player.setCurrentPlayedGame(0);
				} else {
					secondGame.addNewCard(premierGame.getCards().get(2));
					premierGame.getCards()
							.remove(premierGame.getCards().get(2));
					dealer.giveCard(player);
					player.setCurrentPlayedGame(1);
					dealer.giveCard(player);
					player.setCurrentPlayedGame(0);
				}
				break;
			default:
				System.out.println(Messages.getString("Partie.56")); //$NON-NLS-1$
			}
		}
	}

}
