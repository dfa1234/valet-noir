package org.valetnoir.player;

import java.util.ArrayList;
import java.util.List;

import org.valetnoir.dealer.Card;
import org.valetnoir.lang.Messages;

/**
 * Modelise un jeux de carte (un joueur peut avoir plusieurs "game")
 * 
 * @author Djf
 * 
 */
public class Game {
	/**
	 * Retourne vrai si les deux cartes on la même valeur
	 * 
	 * @param une
	 * @param deux
	 * @return
	 */
	public static boolean valeursEguales(Card une, Card deux) {
		int primo, deuxio;
		if (une.getNumero() == Card.ROI || une.getNumero() == Card.REINE
				|| une.getNumero() == Card.VALET || une.getNumero() == Card.AS)
			primo = 10;
		else
			primo = une.getNumero();
		if (deux.getNumero() == Card.ROI || deux.getNumero() == Card.REINE
				|| deux.getNumero() == Card.VALET
				|| deux.getNumero() == Card.AS)
			deuxio = 10;
		else
			deuxio = deux.getNumero();

		if (primo == deuxio)
			return true;
		else
			return false;
	}
	private List<Card> cards;

	private int score;

	/**
	 * Créé et initialise le jeux
	 */
	public Game() {
		this.cards = new ArrayList<Card>();
		this.score = 0;
	}

	/**
	 * Ajoute une carte au jeux
	 * 
	 * @param newCard
	 */
	public void addNewCard(Card newCard) {
		this.cards.add(newCard);
	}

	/**
	 * Retourne vrai si le jeux peut se splitter
	 * 
	 * @return
	 */
	public boolean canSplit() {
		switch (cards.size()) {
		case 1:
			return false;
		case 2:
			return valeursEguales(cards.get(0), cards.get(1));
		case 3:
			return valeursEguales(cards.get(0), cards.get(1))
					|| valeursEguales(cards.get(1), cards.get(2))
					|| valeursEguales(cards.get(0), cards.get(2));
		default:
			return false;
		}
	}

	/**
	 * Renvoi le jeux
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Renvoi le score du jeux
	 */
	public int getScore() {
		score = 0;
		for (Card card : this.cards) {
			if (card.getNumero() == Card.AS) {
				if (score + 11 > 21) {
					score += 1;
				} else {
					score += 11;
				}
			} else if (card.getNumero() == Card.ROI
					|| card.getNumero() == Card.REINE
					|| card.getNumero() == Card.VALET) {
				score += 10;
			} else {
				score += card.getNumero();
			}
		}
		return score;
	}

	public boolean isBlackJack() {
		return getScore() == 21;
	}

	public boolean isBust() {
		return getScore() > 21;
	}

	@Override
	public String toString() {
		String ret = Messages.getString("Game.0"); //$NON-NLS-1$
		for (Card card : cards)
			ret += card.toString() + "  "; //$NON-NLS-1$
		ret += "\n"; //$NON-NLS-1$
		return ret;
	}
}
