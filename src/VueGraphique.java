import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Classe qui sert de vue Graphique
 * @author Groupe 2
 * @version 1
 */
public class VueGraphique extends JPanel implements Observer {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *Liste des figures
	 */
	private ListFigures listeFigures;

	/**
	 *Constructeur vide pour l'instant, sert a l'instanciation
	 */
	public VueGraphique() {
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		this.listeFigures = (ListFigures)arg0;
		repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);

		if(this.listeFigures != null)
			for (int i = 0; i < this.listeFigures.figures.size(); i++) {
				FigureGeom tr = this.listeFigures.figures.get(i);

				if (tr != null) {
					g.drawLine((int)tr.getP1().getX(), (int)tr.getP1().getY(),
							(int)tr.getP2().getX(), (int)tr.getP2().getY()); 
				}
			}
	}
}
