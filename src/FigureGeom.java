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
	private static final int tolerance = 5;
	boolean selectOn = false;
	
	/**
	 * Constructeur de Figure geometrique
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public FigureGeom(Point p1, Point p2) {
		super(p1, p2) ;
	}
	
	// a check 
	public Point getP1() {
		return new Point((int)getX1(), (int)getY1());
	}
	
	// a check 
	public Point getP2() {
		return new Point((int)getX2(), (int)getY2());
	}

	/**
	 * methode getter
	 * @return selectOn si selectionne
	 */
	public boolean isSelectOn() {
		return selectOn;
	}

	/**
	 * methode setter
	 * @param selectOn la valeur a affecter
	 */
	public void setSelectOn(boolean selectOn) {
		this.selectOn = selectOn;
	}
	
	/**
	 * retourne si un point est dans la zone de tol�rance d'un autre point
	 */
	public boolean tolerance (Point2D p) {
		return isInToleranceZone(p,this.getP1()) || isInToleranceZone(p,this.getP2());		   
	}	
	
	/**
	 * retourne si un point est dans le cercle qui a pour centre un point donn�
	 */
	public boolean isInToleranceZone (Point2D p1, Point2D p2) {
		double dx = p1.getX()- p2.getX();
	   double dy = p1.getY()- p2.getY();
	   return dx * dx + dy * dy <= tolerance * tolerance;
	}
	
	public boolean noPoint1 (Point p1) {
		return  (!(this.isInToleranceZone(p1, this.getP1())));	    	
	}
	public boolean noPoint2 (Point p1) {		    
		return (!(this.isInToleranceZone(p1, this.getP2())));
	}
	
	/**
	 * Selectionneur du premier point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return slct selectionne ou non
	 */
	public boolean selectionP1(Point p, int tolerance) {
		boolean slct = false;
		int distance = distance(getP1(), p);
		if (distance < tolerance) slct = true;
		return slct;
	}
	
	/**
	 * Selectionneur du deuxieme point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return slct selectionne ou non
	 */
	public boolean selectionP2(Point p, int tolerance) {
		boolean slct = false;
		int distance = distance(getP2(), p);
		if (distance < tolerance) slct = true;
		return slct;
	}
	
	/**
	 * Renvoie la distance entre deux points
	 * @param p1 point 1
	 * @param p2 point 2
	 * @return la distance entre les deux points
	 */
	public int distance(Point p1, Point p2){
		return (int)(Math.sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + 
				((p1.y - p2.y)*(p1.y - p2.y)))); 
	}
	
}