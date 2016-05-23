import java.awt.Point;

/**
 * Classe qui sert a dessiner les traits A REVOIR  !
 * @author Groupe 2
 * @version 1
 */
public class UnTrait extends FigureGeom{
	
	/**
	 * Constructeur de Trait 
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public UnTrait(Point p1, Point p2) {
		super(p1, p2);
	}
	
	public boolean insideTrait(Point p) {
		double x1 = this.getP1().getX() ;
		double y1 =  this.getP1().getY() ;
		double x2 = this.getP2().getX() ;
		double y2 =  this.getP2().getY() ;
		double x = p.getX() ;
		double y = p.getY() ;
		double dxc = x - x1;
		double dyc = y - y1;
		double dxl = x2 - x1;
		double dyl = y2 - y1;
		double cross = dxc * dyl - dyc * dxl;
		if (Math.abs(cross) > 2500)
			  return false;
		if (Math.abs(dxl) >= Math.abs(dyl)){
			  return dxl > 0 ? 
			   x1 <= x && x <= x2 :
			    x2 <= x && x <= x1;
			  }
		else{
		  return dyl > 0 ? 
		    y1 <= y && y <= y2 :
		    y2 <= y && y <= y1;
		}
	}
}
