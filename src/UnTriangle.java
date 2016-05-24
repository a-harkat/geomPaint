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
	 * retourne si un point est dans la zone de tol�rance d'un autre point
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
	
	public boolean noPoint3 (Point p1) {		    
		return (!(this.isInToleranceZone(p1, this.getP3())));
	}
	
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
