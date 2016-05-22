import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Classe qui represente les figures geometriques
 * @author Groupe 2
 * @version 1
 */
public abstract class FigureGeom extends Line2D.Double {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	private final static int tolerance = 5;
	boolean selectOn = false;
	
	/**
	 * Constructeur de Figure geometrique
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public FigureGeom(Point p1, Point p2) {
		super(p1, p2) ;
	}

	public boolean isSelectOn() {
		return selectOn;
	}

	public void setSelectOn(boolean selectOn) {
		this.selectOn = selectOn;
	}
	
	/**
	 * retourne si un point est dans la zone de tolérance d'un autre point
	 */
	public boolean tolerance (Point2D p) {
		return isInToleranceZone(p,this.getP1()) || isInToleranceZone(p,this.getP2())  ;		   
	}	
	
	/**
	 * retourne si un point est dans le cercle qui a pour centre un point donné
	 */
	public boolean isInToleranceZone (Point2D p1, Point2D p2) {
		double dx = p1.getX()- p2.getX();
	   double dy = p1.getY()- p2.getY();
	   return dx * dx + dy * dy <= tolerance * tolerance;
	}
}
