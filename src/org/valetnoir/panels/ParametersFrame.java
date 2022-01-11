package org.valetnoir.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.valetnoir.lang.Messages;

/**
 * La fenetre des parametres
 * 
 * @author Djf
 * 
 */
public class ParametersFrame extends JFrame {

	private static final long serialVersionUID = 4688722618477017826L;
	private static URLClassLoader urlLoader = (URLClassLoader) ClassLoader
			.getSystemClassLoader();

	public static URL chargeFichier(String fichier) {
		return urlLoader.findResource(fichier);
	}

	public ParametersFrame() {
		super(Messages.getString("ParametersFrame.0")); //$NON-NLS-1$
		setBackground(Table.COULEUR_TAPIS);
		java.awt.Container c = this.getContentPane();
		c.setBackground(Table.COULEUR_TAPIS);
		Parameters parameters = new Parameters();

		String fichier = "/black_jack_green.jpg"; //$NON-NLS-1$

		java.net.URL imageURL = ParametersFrame.class.getResource(fichier);
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(imageURL);
		Icon icone = new ImageIcon(image);
		JLabel lImage = new JLabel(icone);
		lImage.setBackground(Table.COULEUR_TAPIS);

		c.add(new JPanel().add(lImage), BorderLayout.NORTH);
		c.add(new JPanel().add(parameters), BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 4,
				(screen.height - this.getSize().height) / 4);
		this.pack();
		this.setVisible(true);
	}
}
