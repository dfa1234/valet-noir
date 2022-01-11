package org.valetnoir.panels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.valetnoir.sound.Sound;

/**
 * La classe panel de l'historique
 * 
 * @author Djf
 * 
 */
public class Historique extends JPanel {

	private final static String newline = "\n";
	private static final long serialVersionUID = 1L;
	protected JTextArea textArea;

	public Historique() {
		super();
		setBackground(Table.COULEUR_TAPIS);
		textArea = new JTextArea(16, 20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = -7334199241913150517L;
		};
		this.add(scrollPane);
		scrollPane.setSize(200, 600);
		textArea.setBackground(new Color(140, 250, 140));
		this.setBackground(Table.COULEUR_TAPIS);
	}

	public void ajouterTexte(String text) {
		Sound player=new Sound("/presser-touche.wav");
		textArea.append(text + newline);
		textArea.setCaretPosition(textArea.getDocument().getLength());

	}

}
