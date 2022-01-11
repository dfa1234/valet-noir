package org.valetnoir.dealer;

import java.awt.Image;

import org.valetnoir.lang.Messages;

/**
 * Cette classe modélise une carte.
 * 
 * @author Djf
 * 
 */
public class Card {
	public static final int AS = 14;
	public static final int CARREAU = 1;
	public static final int COEUR = 2;
	public static final int PIQUE = 3;
	public static final int REINE = 12;
	public static final int ROI = 13;
	public static final int TREFLE = 0;
	public static final int VALET = 11;
	private int couleur;
	private Image image;
	private int numero;

	/**
	 * Créé une carte à partir de son numero et de sa couleur On lui attribue
	 * une image spécifique.
	 * 
	 * @param numero
	 * @param couleur
	 */
	public Card(int numero, int couleur) {
		this.setNumero(numero);
		this.setCouleur(couleur);
		int file = 0;
		switch (couleur) {
		case CARREAU:
			file += 0;
			break;
		case COEUR:
			file += 13;
			break;
		case PIQUE:
			file += 26;
			break;
		case TREFLE:
			file += 39;
			break;
		}
		switch (numero) {
		case 2:
			file += 0;
			break;
		case 3:
			file += 1;
			break;
		case 4:
			file += 2;
			break;
		case 5:
			file += 3;
			break;
		case 6:
			file += 4;
			break;
		case 7:
			file += 5;
			break;
		case 8:
			file += 6;
			break;
		case 9:
			file += 7;
			break;
		case 10:
			file += 8;
			break;
		case VALET:
			file += 9;
			break;
		case REINE:
			file += 10;
			break;
		case ROI:
			file += 11;
			break;
		case AS:
			file += 12;
			break;
		}

		String fichier = "/" + file + "_130px.jpg"; //$NON-NLS-1$ //$NON-NLS-2$
		java.net.URL imageURL = Card.class.getResource(fichier);
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(imageURL);
		this.setImage(image);

	}

	/**
	 * @return the couleur
	 */
	public int getCouleur() {
		return couleur;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param couleur
	 *            the couleur to set
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		String ret = ""; //$NON-NLS-1$
		switch (numero) {
		case VALET:
			ret += Messages.getString("Card.valet"); //$NON-NLS-1$
			break;
		case REINE:
			ret += Messages.getString("Card.reine"); //$NON-NLS-1$
			break;
		case ROI:
			ret += Messages.getString("Card.roi"); //$NON-NLS-1$
			break;
		case AS:
			ret += Messages.getString("Card.as"); //$NON-NLS-1$
			break;
		default:
			ret += "" + numero; //$NON-NLS-1$
			break;

		}

		switch (couleur) {
		case CARREAU:
			ret += Messages.getString("Card.carreau"); //$NON-NLS-1$
			break;
		case PIQUE:
			ret += Messages.getString("Card.pique"); //$NON-NLS-1$
			break;
		case COEUR:
			ret += Messages.getString("Card.coeur"); //$NON-NLS-1$
			break;
		case TREFLE:
			ret += Messages.getString("Card.trefle"); //$NON-NLS-1$
			break;
		}

		return ret;
	}
}
