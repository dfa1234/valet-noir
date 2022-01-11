package org.valetnoir.panels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.valetnoir.dealer.Card;
import org.valetnoir.dealer.Dealer;
import org.valetnoir.handler.Partie;
import org.valetnoir.player.Player;
import org.valetnoir.player.Playing;

/**
 * La classe panel des jeux de cartes
 * 
 * @author Djf
 * 
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = -6255566217627734635L;

	/**
	 * Redimensionne les BufferedImage
	 * 
	 * @param source
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image scale(Image source, int width, int height) {
		/* On crée une nouvelle image aux bonnes dimensions. */
		BufferedImage buf = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		/* On dessine sur le Graphics de l'image bufferisée. */
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();

		/* On retourne l'image bufferisée, qui est une image. */
		return buf;
	}

	public GamePanel(Playing playing, int step) {
		setBackground(Table.COULEUR_TAPIS);
		List<Card> cards;
		boolean cachePremiere;
		if (playing instanceof Player) {
			Player player = (Player) playing;
			cards = player.getGames().get(player.getCurrentPlayedGame())
					.getCards();
			cachePremiere = false;
		} else {
			Dealer dealer = (Dealer) playing;
			cards = dealer.getGames().get(0).getCards();
			cachePremiere = step < Partie.stepAfterACTION;
		}

		if (cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				if ((i == 0) && cachePremiere) {
					String fichier = "/flipside_130px.jpg";
					java.net.URL imageURL = getClass().getResource(fichier);
					java.awt.Toolkit toolkit = java.awt.Toolkit
							.getDefaultToolkit();
					Image image = toolkit.getImage(imageURL);
					Icon icone = new ImageIcon(image);
					this.add(new JLabel(icone));
				} else {
					Image image = cards.get(i).getImage();
					// image = scale(image, 94, 130);
					Icon icone = new ImageIcon(image);
					this.add(new JLabel(icone));
				}
			}
		} else {
			String fichier = "/Vide.jpg";
			java.net.URL imageURL = getClass().getResource(fichier);
			java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage(imageURL);
			Icon icone = new ImageIcon(image);
			this.add(new JLabel(icone));
		}

	}

}
