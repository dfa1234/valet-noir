package org.valetnoir.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.valetnoir.dealer.Card;
import org.valetnoir.dealer.Dealer;
import org.valetnoir.handler.Partie;
import org.valetnoir.lang.Messages;
import org.valetnoir.player.Player;

/**
 * La classe panel des boutons d'action
 * 
 * @author Djf
 * 
 */
public class ControlBoard extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = -4636028609323585916L;
	public static final int iDEAL = 7;
	public static final int iDOUBLE = 2;
	public static final int iHIT = 4;
	public static final int iINSURANCE = 1;
	public static final int iRAISE = 6;
	public static final int iSPLIT = 3;
	public static final int iSTAND = 5;

	public static final String sDEAL = Messages.getString("ControlBoard.0"); //$NON-NLS-1$
	public static final String sDOUBLE = Messages.getString("ControlBoard.1"); //$NON-NLS-1$
	public static final String sHIT = Messages.getString("ControlBoard.2"); //$NON-NLS-1$
	public static final String sINSURANCE = Messages.getString("ControlBoard.3"); //$NON-NLS-1$
	public static final String sRAISE = Messages.getString("ControlBoard.4"); //$NON-NLS-1$
	public static final String sSPLIT = Messages.getString("ControlBoard.5"); //$NON-NLS-1$
	public static final String sSTAND = Messages.getString("ControlBoard.6"); //$NON-NLS-1$

	private JButton deal;
	private Dealer dealer;
	private JButton doubler;
	private JButton hit;
	private JButton insurance;
	private Player player;
	private JButton raise;
	private JButton split;
	private JButton stand;
	private int step;

	/**
	 * créé un nouveau panel
	 * 
	 * @param player
	 * @param dealer
	 * @param first
	 */
	public ControlBoard(Player p, Dealer d, int s) {
		this.player = p;
		this.dealer = d;
		this.step = s;
		insurance = new JButton(sINSURANCE);
		doubler = new JButton(sDOUBLE);
		split = new JButton(sSPLIT);
		stand = new JButton(sSTAND);
		hit = new JButton(sHIT);
		deal = new JButton(sDEAL);
		raise = new JButton(sRAISE);

		insurance.addActionListener(this);
		doubler.addActionListener(this);
		split.addActionListener(this);
		stand.addActionListener(this);
		hit.addActionListener(this);
		deal.addActionListener(this);
		raise.addActionListener(this);

		insurance.setEnabled(false);
		doubler.setEnabled(false);
		split.setEnabled(false);
		stand.setEnabled(false);
		hit.setEnabled(false);
		deal.setEnabled(false);
		raise.setEnabled(false);
		refreshButton(step);

		this.setLayout(new GridLayout(1, 7));
		this.add(insurance);
		this.add(doubler);
		this.add(split);
		this.add(stand);
		this.add(hit);
		this.add(raise);
		this.add(deal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton click = (JButton) e.getSource();
		Partie.setAction(click.getText());
		Partie.setThreadSuspended(false);
	}

	public void refreshButton(int step) {
		switch (step) {
		case Partie.stepNEWGAME:
			insurance.setEnabled(false);
			doubler.setEnabled(false);
			split.setEnabled(false);
			stand.setEnabled(false);
			hit.setEnabled(false);
			raise.setEnabled(false);
			deal.setEnabled(true);
			break;
		case Partie.stepRAISE:
			insurance.setEnabled(false);
			doubler.setEnabled(false);
			split.setEnabled(false);
			stand.setEnabled(false);
			hit.setEnabled(false);
			raise.setEnabled(true);
			deal.setEnabled(false);
			break;
		case Partie.stepACTION:
			insurance.setEnabled(dealer.getGames().get(0).getCards().get(1)
					.getNumero() == Card.AS);
			doubler.setEnabled(true);
			split.setEnabled(player.getGames().get(
					player.getCurrentPlayedGame()).canSplit());
			stand.setEnabled(true);
			hit.setEnabled(true);
			raise.setEnabled(false);
			deal.setEnabled(false);
			break;
		case Partie.stepAfterHIT:
			insurance.setEnabled(false);
			doubler.setEnabled(false);
			split.setEnabled(false);
			stand.setEnabled(true);
			hit.setEnabled(true);
			raise.setEnabled(false);
			deal.setEnabled(false);
			break;
		case Partie.stepAfterASSURANCE:
			insurance.setEnabled(false);
			doubler.setEnabled(true);
			split.setEnabled(false);
			stand.setEnabled(true);
			hit.setEnabled(true);
			raise.setEnabled(false);
			deal.setEnabled(false);
			break;
		case Partie.stepAfterDOUBLE:
			insurance.setEnabled(false);
			doubler.setEnabled(false);
			split.setEnabled(false);
			stand.setEnabled(false);
			hit.setEnabled(false);
			raise.setEnabled(false);
			deal.setEnabled(false);
			break;
		case Partie.stepAfterSPLIT:
			insurance.setEnabled(false);
			doubler.setEnabled(false);
			split.setEnabled(false);
			stand.setEnabled(true);
			hit.setEnabled(true);
			raise.setEnabled(false);
			deal.setEnabled(false);
			break;
		default:
			break;
		}
	}
}
