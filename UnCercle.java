import java.awt.Color;
import java.awt.Point;

/**
 * Classe qui represente un Cerle
 * @author Groupe 2
 * @version 1
 */
public class UnCercle extends FigureGeom {

	/**
	 * Constructeur de Cercle
	 * @param p1 point 1 centre
	 * @param p2 point 2 rayon
	 * @param border la couleur du cercle
	 */
	public UnCercle(Point p1, Point p2, Color border) {
		super(p1, p2, border);		
	}
	
	/**
	 * Constructeur de Cercle
	 * @param p1 Point 1 centre
	 * @param p2 Point 2 rayon
	 * @param border Color la couleur du cercle
	 * @param filled Boolean True si
	 * cercle plein false sinon
	 */
	public UnCercle(Point p1, Point p2, Color border, boolean filled) {
		super(p1, p2, border, filled);		
	}
	
	/**
	 * Teste si un point est a l'interieure du cercle
	 * @param p1 le point a tester
	 * @return true si vrai
	 */
	public boolean insideCercle (Point p1) {
		double dx = p1.getX()- this.getP1().getX();
	   double dy = p1.getY()- this.getP1().getY();
	   return dx * dx + dy * dy <= distance(p1, this.getP1())*distance(p1, this.getP1());
	}
	/**
	 * retourne la distance entre deux points, le rayon du cercle
	 * @param p1 centre
	 * @param p2 point
	 * @return la distance 
	 */
	public int distance (Point p1, Point p2) {
		double x1 = this.getP1().getX();
		double y1 = this.getP1().getY();
		double x2 = this.getP2().getX();
		double y2 = this.getP2().getY();								
		return (int) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
}
