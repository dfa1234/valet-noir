package org.valetnoir.main;

import org.valetnoir.handler.HandlerGeneral;

/**
 * Classe main principale Fait appel au gestionnaire de jeux.
 * 
 * @author Djf
 * 
 */
public class ValetNoir {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HandlerGeneral GestionnaireDeJeux = new HandlerGeneral();
		GestionnaireDeJeux.start();

	}

}
