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
	public static final int VERTEX_SIZE = 6;

	/**
	 *Constructeur vide pour l'instant, sert a l'instanciation
	 */
	public VueGraphique() {
		setBackground(Color.white);
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
			for (int i = 0; i < this.listeFigures.getFigures().size(); i++) {
				FigureGeom tr = this.listeFigures.getFigures().get(i);
				if (tr instanceof UnTrait){
					g.drawLine((int)tr.getP1().getX(), (int)tr.getP1().getY(),
							(int)tr.getP2().getX(), (int)tr.getP2().getY()); 
					if(tr.selectOn)
						drawVertex(g, tr);
				}
				if (tr instanceof UnTriangle ){
					UnTriangle trait = (UnTriangle) tr ;					
					int [] X = {(int)trait.getP1().getX(), (int)trait.getP2().getX(), (int)trait.getP3().getX()};
					int [] Y = {(int)trait.getP1().getY(), (int)trait.getP2().getY(), (int)trait.getP3().getY()};
					g.drawPolygon(X, Y, 3);	
				}
			}
	}
	
	public void drawVertex(Graphics g, FigureGeom fg) {
		
		int vrtx = VERTEX_SIZE;
		
		g.drawRect((int)fg.getX1()-vrtx/2, (int)fg.getY1()-vrtx/2, vrtx, vrtx);
		g.drawRect((int)fg.getX2()-vrtx/2, (int)fg.getY2()-vrtx/2, vrtx, vrtx);
	}
}
