package org.valetnoir.player;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Player modelise un joueur de blackjack
 * 
 * @author Djf
 * 
 */
public class Player implements Playing {
	private int assurance;
	private int currentPlayedGame;
	private List<Game> games;
	private int id;
	private int jetons;
	private int mise;
	private String nom;
	private boolean split;

	/**
	 * Créé un nouveau joueur, avec un nouvelle id, nom et nombre de jetons
	 * 
	 * @param id
	 * @param nom
	 * @param jetons
	 */
	public Player(int id, String nom, int jetons) {
		this.id = id;
		this.setNom(nom);
		this.setJetons(jetons);
		this.setMise(0);
		this.setSplit(false);
		this.games = new ArrayList<Game>();
		this.games.add(new Game());
		this.assurance = 0;
		setCurrentPlayedGame(0);
	}

	/**
	 * @return the assurance
	 */
	public int getAssurance() {
		return assurance;
	}

	/**
	 * Retourne le jeux du joueur actuellement actif Renvoi toujours 0 si le
	 * jeux n'est pas splitter
	 * 
	 * @return the currentPlayedGame
	 */
	public int getCurrentPlayedGame() {
		return currentPlayedGame;
	}

	/**
	 * Retourne le ou les jeux du joueur
	 * 
	 * @return the games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * Retourne les jetons
	 * 
	 * @return the jetons
	 */
	public int getJetons() {
		return jetons;
	}

	/**
	 * @return the mise
	 */
	public int getMise() {
		return mise;
	}

	/**
	 * Retourne le nom
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne vrai si le jeux du joueur est splitté
	 * 
	 * @return the split
	 */
	public boolean isSplit() {
		return split;
	}

	/**
	 * @param assurance
	 *            the assurance to set
	 */
	public void setAssurance(int assurance) {
		this.assurance = assurance;
	}

	/**
	 * Change de jeux actif
	 * 
	 * @param currentPlayedGame
	 *            the currentPlayedGame to set
	 */
	public void setCurrentPlayedGame(int currentPlayedGame) {
		this.currentPlayedGame = currentPlayedGame;
	}

	/**
	 * Change le jeux de carte
	 * 
	 * @param games
	 *            the games to set
	 */
	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * Change le montant des jetons
	 * 
	 * @param jetons
	 *            the jetons to set
	 */
	public void setJetons(int jetons) {
		this.jetons = jetons;
	}

	/**
	 * @param mise
	 *            the mise to set
	 */
	public void setMise(int mise) {
		this.mise = mise;
	}

	/**
	 * Change le nom (sans interet)
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Change l'etat du splittage (sans interet)
	 * 
	 * @param split
	 *            the split to set
	 */
	public void setSplit(boolean split) {
		this.split = split;
	}

	public int getId(){
		return id;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
