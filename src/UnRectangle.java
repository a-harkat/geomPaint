import java.awt.Point;

import com.sun.javafx.geom.Rectangle;

/**
 * Classe qui represente un Rectangle
 * @author Groupe 2
 * @version 1
 */
public class UnRectangle extends FigureGeom {

	/**
	 * Constructeur de Rectangle
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public UnRectangle(Point p1, Point p2) {
		super(p1, p2);

	}
	
	public boolean insideRectangle(Point p1) 
	{
		int x = (int) this.getP1().getX() ;
		int y = (int) this.getP1().getY() ;
		int width = (int) Math.abs(this.getP1().getX() - this.getP2().getX());
		int height = (int) Math.abs(this.getP1().getY() - this.getP2().getY());
		if (y>this.getP2().getY()) y = y - height;
		if (x>this.getP2().getX())	x = x - width ;	
		Rectangle r = new Rectangle(x, y,width, height );
		return r.contains((int)p1.getX(), (int)p1.getY());
	}
	
			
}
