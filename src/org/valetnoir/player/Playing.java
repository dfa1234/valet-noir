package org.valetnoir.player;

import java.util.List;

/**
 * Une classe int�gre l'interface "isPlaying" si elle mod�lise un des
 * participant au jeux. En l'occurence, les classes "Player" et "Dealer" le
 * font. Cette interface permet de traiter le player et le dealer de la m�me
 * mani�re quant � l'attribution de jeux de carte, la distribution de carte,
 * etc...
 * 
 * @author Djf
 * 
 */
public interface Playing {

	/**
	 * @return the games
	 */
	public abstract List<Game> getGames();

	/**
	 * @param games
	 *            the games to set
	 */
	public abstract void setGames(List<Game> game);

}
