package org.valetnoir.panels;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * @author Gérard Bourriaud dit JHelp
 * @version 1.0
 */
/**
 * Layout pour placer les élémnts en vertical
 */
public class VerticalLayout implements LayoutManager {
	// Nombre de pixels de vide entre le composant et le bord du conteneur en
	// horizontal
	private int espaceHorizontal = 1;
	// Nombre de pixels de vide entre le composant et le bord du conteneur en
	// vertical
	private int espaceVertical = 1;

	/**
	 * Layout vertical par défaut
	 */
	public VerticalLayout() {
	}

	/**
	 * LayoutVertical en présisant les écart avec le bord
	 */
	public VerticalLayout(int espaceHorizontal, int espaceVertical) {
		this.espaceHorizontal = espaceHorizontal;
		this.espaceVertical = espaceVertical;
	}

	/**
	 * Ajoute un composant avec une contrainte particuliére, inutile ici, mias
	 * laissé pour implémenté l'interface LayoutManager
	 */
	public void addLayoutComponent(String name, Component comp) {
	}

	/**
	 * Place les composants dans le conteneur
	 */
	public void layoutContainer(Container parent) {
		// On se synchronize sur le conteneur pour éviter des défauts
		// d'affichage
		synchronized (parent.getTreeLock()) {
			// On récupère le décalage du conteneur
			Insets insets = parent.getInsets();
			// On récupère le nombre de composants contenus dans le conteneur
			int nb = parent.getComponentCount();
			// On initialise les coordonées de placement
			int x = espaceVertical + insets.left;
			int y = espaceHorizontal + insets.top;
			// Pour chaque composant du conteneur
			for (int i = 0; i < nb; i++) {
				// Si le composant actuel est visible
				if (parent.getComponent(i).isVisible()) {
					// On récupére la taille préferée du composant
					Dimension d = parent.getComponent(i).getPreferredSize();
					// On donne au composant sa taille préférée
					parent.getComponent(i).setSize(d);
					// On place le composant
					parent.getComponent(i).setLocation(x, y);
					// On décale les ordonées pour le prochain composant
					y += d.height + espaceVertical;
				}
			}
		}
	}

	/**
	 * Calcul la dimension minimale du conteneur en paramètre
	 */
	public Dimension minimumLayoutSize(Container parent) {
		// On récupère le nombre de composant contenus
		int nb = parent.getComponentCount();
		// On initialise la réponse
		Dimension reponse = new Dimension(0, 0);
		// Pour chaque composant dans le conteneur
		for (int i = 0; i < nb; i++) {
			// Si le composant actuel est affiché
			if (parent.getComponent(i).isVisible()) {
				// On récupère les dimensions minimales du composant actuel
				Dimension actuel = parent.getComponent(i).getMinimumSize();
				// La largeur totale est le maximum des largeurs
				if (reponse.width < actuel.width)
					reponse.width = actuel.width;
				// On ajoute la hauteur du composant actuel
				reponse.height += actuel.height + espaceVertical;
			}
		}
		// On tiens en compte des divers décalages
		Insets insets = parent.getInsets();
		reponse.width += insets.right + insets.left + espaceHorizontal * 2;
		reponse.height += insets.top + insets.bottom + espaceVertical * 2;
		// On renvoie la réponse
		return reponse;
	}

	/**
	 * Calcul la dimension préférée du conteneur en paramètre
	 */
	public Dimension preferredLayoutSize(Container parent) {
		// On récupère le nombre de composant contenus
		int nb = parent.getComponentCount();
		// On initialise la réponse
		Dimension reponse = new Dimension(0, 0);
		// Pour chaque composant dans le conteneur
		for (int i = 0; i < nb; i++) {
			// Si le composant actuel est affiché
			if (parent.getComponent(i).isVisible()) {
				// On récupère les dimensions préférées du composant actuel
				Dimension actuel = parent.getComponent(i).getPreferredSize();
				// La largeur totale est le maximum des largeurs
				if (reponse.width < actuel.width)
					reponse.width = actuel.width;
				// On ajoute la hauteur du composant actuel
				reponse.height += actuel.height;
			}
		}
		// On tiens en compte des divers décalages
		Insets insets = parent.getInsets();
		reponse.width += insets.right + insets.left + espaceHorizontal * 2;
		reponse.height += insets.top + insets.bottom + espaceVertical * 2;
		// On renvoie la réponse
		return reponse;
	}

	/**
	 * Retire un composant du rendu, inutile ici, mias laissé pour implémenté
	 * l'interface LayoutManager
	 */
	public void removeLayoutComponent(Component comp) {
	}
}