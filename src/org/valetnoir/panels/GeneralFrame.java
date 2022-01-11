package org.valetnoir.panels;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import org.valetnoir.dealer.Dealer;
import org.valetnoir.lang.Messages;
import org.valetnoir.player.Player;

public class GeneralFrame extends JFrame {

	private static final long serialVersionUID = -8431629330663800864L;
	private Dealer dealer;
	private List<Player> players;
	private Table table;

	public GeneralFrame(List<Player> ps, Dealer d) {
		super(Messages.getString("GeneralFrame.0")); //$NON-NLS-1$
		setBackground(Table.COULEUR_TAPIS);
		this.dealer = d;
		players = ps;
		Container c = this.getContentPane();
		table = new Table(players, dealer);
		c.add(table);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 4,
				(screen.height - this.getSize().height) / 4);
		this.setSize(new Dimension(800, 430));
	}

	public Table getTable() {
		return table;
	}
}
