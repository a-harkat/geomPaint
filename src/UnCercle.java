import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Classe qui represente un Cerle
 * @author Groupe 2
 * @version 1
 */
public class UnCercle extends FigureGeom {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de Cercle
	 * @param p1 point 1 centre
	 * @param p2 point 2 rayon
	 */
	public UnCercle(Point p1, Point p2) {
		super(p1, p2);		
	}
	
	public boolean insideCercle (Point2D p1) {
		double dx = p1.getX()- this.getP1().getX();
	   double dy = p1.getY()- this.getP1().getY();
	   return dx * dx + dy * dy <= distance(p1, this.getP1())*distance(p1, this.getP1());
	}
	public double distance (Point2D p1, Point2D p2) {
		double x1 = this.getP1().getX();
		double y1 = this.getP1().getY();
		double x2 = this.getP2().getX();
		double y2 = this.getP2().getY();								
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
}
