package org.valetnoir.dealer;

import java.util.ArrayList;
import java.util.List;

import org.valetnoir.lang.Messages;
import org.valetnoir.player.Game;
import org.valetnoir.player.Player;
import org.valetnoir.player.Playing;
import org.valetnoir.sound.Sound;
import org.valetnoir.sound.ThreadSound;

/**
 * Cette classe modélise un dealer virtuel doté de son sabot virtuel. il
 * implemente l'interface isPLaying car il dispose de son propre jeux.
 * 
 * @author Djf
 * 
 */
public class Dealer implements Playing {

	private List<Game> games;
	private Sabot sabot;

	/**
	 * Initisalise un nouveau dealer, avec un nouveau sabot
	 */
	public Dealer() {
		sabot = new Sabot();
		this.games = new ArrayList<Game>();
		this.games.add(new Game());
	}

	@Override
	public List<Game> getGames() {
		// TODO Auto-generated method stub
		return this.games;
	}

	/**
	 * Distribue une carte au joueur (ou a lui meme) Il faut indiquer l'index du
	 * jeux (0 si le jeux n'est pas splitter)
	 * 
	 * Renvoi une carte juste pour info
	 * 
	 * @param player
	 * @param indexGame
	 */
	public Card giveCard(Playing player) {
		Thread son=new ThreadSound("/card.wav");
		son.start();
		Game gameToAdd;
		if (player instanceof Dealer)
			gameToAdd = player.getGames().get(0);
		else
			gameToAdd = player.getGames().get(
					((Player) player).getCurrentPlayedGame());
		Card newCard = sabot.getCard();
		gameToAdd.addNewCard(newCard);
		// System.out.println(gameToAdd);
		return newCard;
	}

	@Override
	public void setGames(List<Game> game) {
		this.games = game;
	}

	@Override
	public String toString() {
		return Messages.getString("Dealer.dealer"); //$NON-NLS-1$
	}
}
