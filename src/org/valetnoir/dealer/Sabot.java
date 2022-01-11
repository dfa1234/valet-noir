package org.valetnoir.dealer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Modelise le sabot comportant 3 paquets de 52 cartes.
 * 
 * Lorsque le sabot est épuisé il se régénére automatiquement.
 * 
 * @author Djf
 * 
 */
public class Sabot {
	private List<Card> cards;

	/**
	 * Le constructeur initialise et modelise le jeu
	 */
	public Sabot() {
		this.cards = new ArrayList<Card>();
		this.genererSabot(this.cards);
	}

	/**
	 * Genere le sabot de carte
	 * 
	 * @param cards2
	 */
	private void genererSabot(List<Card> cards2) {
		for (int paquet = 1; paquet <= 3; paquet++)
			for (int couleur = 0; couleur <= 3; couleur++)
				for (int numero = 2; numero <= Card.AS; numero++)
					cards2.add(new Card(numero, couleur));
	}

	/**
	 * Prendre une carte du sabot au hasard
	 */
	public Card getCard() {
		Card newCard;
		Random r = new Random();
		int size = this.cards.size();
		if (size <= 0) {
			this.genererSabot(this.cards);
			newCard = this.getCard();
		} else {
			int index = r.nextInt(this.cards.size());
			newCard = this.cards.get(index);
			this.cards.remove(index);
		}
		return newCard;
	}

}
