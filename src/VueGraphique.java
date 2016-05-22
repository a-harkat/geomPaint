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
		setBackground(new Color(245,255,250));
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
					
				}
				if (tr instanceof UnTriangle ){
					UnTriangle triangle = (UnTriangle) tr ;					
					int [] X = {(int)triangle.getP1().getX(), (int)triangle.getP2().getX(), (int)triangle.getP3().getX()};
					int [] Y = {(int)triangle.getP1().getY(), (int)triangle.getP2().getY(), (int)triangle.getP3().getY()};
					g.drawPolygon(X, Y, 3);	
				}
				if (tr instanceof UnCercle ){
					UnCercle Cercle = (UnCercle) tr ;
					int r = (int) (Cercle.distance(Cercle.getP1(),Cercle.getP2())) ;
					int x =(int) Cercle.getP1().getX();
					int y =(int) Cercle.getP1().getY();
					g.drawOval(x-r,y-r,r*2,r*2);
				}
				if (tr instanceof UnRectangle ){
					UnRectangle rectangle = (UnRectangle) tr;
					int x = (int) rectangle.getP1().getX() ;
					int y = (int) rectangle.getP1().getY() ;
					int width = (int) Math.abs(rectangle.getP1().getX() - rectangle.getP2().getX());
					int height = (int) Math.abs(rectangle.getP1().getY() - rectangle.getP2().getY());
					if (y>rectangle.getP2().getY()) y = y - height;
					if (x>rectangle.getP2().getX())	x = x - width ;		
					g.drawRect(x, y, width, height );
				}
				if(tr.isSelectOn())
					drawVertex(g, tr);
			}
	}
	
	public void drawVertex(Graphics g, FigureGeom fg) {		
		int vrtx = VERTEX_SIZE;		
		g.drawRect((int)fg.getP1().getX()-vrtx/2, (int)fg.getP1().getY()-vrtx/2, vrtx, vrtx);
		g.drawRect((int)fg.getP2().getX()-vrtx/2, (int)fg.getP2().getY()-vrtx/2, vrtx, vrtx);
		
		if (fg instanceof UnTriangle ){ 
			UnTriangle triangle = (UnTriangle) fg ;
			g.drawRect((int)triangle.getP3().getX()-vrtx/2, (int)triangle.getP3().getY()-vrtx/2, vrtx, vrtx);
		}
	}
}
