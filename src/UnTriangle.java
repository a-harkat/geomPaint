import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Classe qui represente un Triangle
 * @author Groupe 2
 * @version 1
 */
public class UnTriangle extends UnPolygone{

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *Represente le troisieme point
	 */
	private Point p3 ;
	
	/**
	 * Constructeur de Triangle
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public UnTriangle(Point p1, Point p2, Point p3) {
		super(p1, p2);
		this.setP3(p3) ;
	}
	
	/**
	 * retourne si un point est dans triangle triangle
	 */
	public boolean isInsideTriangle(Point2D p)
    {	  
		double x1 = this.getP1().getX() ;
		double x2 = this.getP2().getX();
		double x3 = this.getP3().getX() ;
		double y1 = this.getP1().getY() ;
		double y2 = this.getP2().getY();
		double y3 = this.getP3().getY() ;
		double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		double ABP = Math.abs (x1 * (y2 - p.getY()) + x2 * (p.getY() - y1) + p.getX() * (y1 - y2));
		double APC = Math.abs (x1 * (p.getY() - y3) + p.getX() * (y3 - y1) + x3 * (y1 - p.getY()));
		double PBC = Math.abs (p.getX() * (y2 - y3) + x2 * (y3 - p.getY()) + x3 * (p.getY() - y2));
        return  ABP + APC + PBC == ABC;
    }
	
	/**
	 * retourne si un point est dans la zone de tolérance d'un autre point
	 */
	public boolean tolerance (Point2D p) {
		return isInToleranceZone(p,this.getP1()) || isInToleranceZone(p,this.getP2()) || isInToleranceZone(p,this.getP3()) ;		   
	}	
	/**
	 * Getteur du troisieme point
	 * @return p3
	 */
	public Point getP3() {
		return p3;
	}
	
	/**
	 * Setteur du troisieme point
	 * @param p3
	 */
	public void setP3(Point p3) {
		this.p3 = p3;
	}

}
