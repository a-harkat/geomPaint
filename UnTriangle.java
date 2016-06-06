import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Classe qui represente un Triangle
 * @author Groupe 2
 * @version 1
 */
public class UnTriangle extends UnPolygone{
	
	/**
	 *Represente le troisieme point
	 */
	private Point p3 ;
	
	/**
	 * Constructeur de Triangle
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param p3 point 3
	 * @param border couleur du triangle
	 */
	public UnTriangle(Point p1, Point p2, Point p3, Color border) {
		super(p1, p2, border);
		this.setP3(p3) ;
	}
	
	/**
	 * Constructeur de Triangle
	 * @param p1 Point 1
	 * @param p2 Point 2
	 * @param p3 Point 3
	 * @param border Color couleur du triangle
	 * @param filled Boolean True si
	 * le triangle est plein false sinon
	 */
	public UnTriangle(Point p1, Point p2, Point p3, Color border, boolean filled) {
		super(p1, p2, border, filled);
		this.setP3(p3) ;
	}
	
	/**
	 * Teste si un point est dans le triangle
	 * @param p le point a tester
	 * @return true si vrai
	 */
	public boolean isInsideTriangle(Point2D p)
    {	  
		double x1 = this.getP1().getX() ;
		double x2 = this.getP2().getX();
		double x3 = this.getP3().getX() ;
		double y1 = this.getP1().getY() ;
		double y2 = this.getP2().getY();
		double y3 = this.getP3().getY() ;
		int ABC = (int) (Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)));
		int ABP = (int) (Math.abs (x1 * (y2 - p.getY()) + x2 * (p.getY() - y1) + p.getX() * (y1 - y2)));
		int APC = (int) (Math.abs (x1 * (p.getY() - y3) + p.getX() * (y3 - y1) + x3 * (y1 - p.getY())));
		int PBC = (int) (Math.abs (p.getX() * (y2 - y3) + x2 * (y3 - p.getY()) + x3 * (p.getY() - y2)));
        return  (ABP + APC + PBC) == ABC;
    }
	
	/**
	 * Teste si un point est dans la zone de tolerance 
	 * d'un des points du triangle
	 * @param p le point a tester
	 * @return true si vrai
	 */
	public boolean toleranceTriangle (Point p) {
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
	
	/**
	 * Teste si le troisieme point n'est pas
	 * colle avec les deux autres point
	 * @param p le point a tester
	 * @return false si le point est proche
	 */
	public boolean noPoint3 (Point p) {		    
		return (!(this.isInToleranceZone(p, this.getP3())));
	}
	/**
	 * Modifie la position d'un du triangle
	 * @param p1 point depart
	 * @param p2 point arrivee
	 */
	public void editerTriangle (Point p1, Point p2){
		if (this.isInToleranceZone(p1, this.getP1())) 
			if (this.noPoint2(p2) && this.noPoint3(p2)) 
				this.setP1(p2);
		if (this.isInToleranceZone(p1, this.getP2()))
			if (this.noPoint1(p2) && this.noPoint3(p2)) 
				this.setP2(p2);
		if (this.isInToleranceZone(p1, this.getP3()))
			if (this.noPoint1(p2) && this.noPoint2(p2)) 
				this.setP3(p2);
	}
	/**
	 * Deplace les points du triangle 
	 * @param p1 point de depart
	 * @param p2 point d'arrivee
	 */
	public void deplacerTriangle (Point p1, Point p2){
		int xp1 = (int)this.getP1().getX() ;
		int xp2 = (int)this.getP2().getX();
		int xp3 = (int)this.getP3().getX() ;
		int yp1 = (int)this.getP1().getY() ;
		int yp2 = (int)this.getP2().getY();
		int yp3 = (int)this.getP3().getY() ;
		int x = (int) p1.getX();
		int y = (int) p1.getY();
		int nx = (int) (p2.getX()-x) ;
		int ny = (int) (p2.getY()-y) ;
		Point np1 = new Point( xp1+nx, yp1+ny);
		Point np2 = new Point( xp2+nx, yp2+ny);
		Point np3 = new Point( xp3+nx, yp3+ny);
		this.setP1(np1);
		this.setP2(np2);
		this.setP3(np3);
	}

}
