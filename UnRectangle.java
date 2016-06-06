import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * Classe qui represente un Rectangle
 * @author Groupe 2
 * @version 1
 */
public class UnRectangle extends UnPolygone {

	/**
	 * Constructeur de Rectangle
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param border couleur du rectangle
	 */
	public UnRectangle(Point p1, Point p2, Color border) {
		super(p1, p2, border);
	}
	
	/**
	 * Constructeur de Rectangle
	 * @param p1 Point 1
	 * @param p2 Point 2
	 * @param border Color couleur du rectangle
	 * @param filled Boolean True si le
	 * rectangle est plein false sinon
	 */
	public UnRectangle(Point p1, Point p2, Color border, boolean filled) {
		super(p1, p2, border, filled);
	}
	
	/**
	 * Teste si un point est dans le rectangle
	 * @param p1 le point a tester
	 * @return true si vrai
	 */
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
