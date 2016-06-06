import java.awt.Color;
import java.awt.Point;

/**
 * Classe qui represente un Polygone
 * @author Groupe 2
 * @version 1
 */
public abstract class UnPolygone extends FigureGeom {


	/**
	 * Constructeur de Polygone
	 * @param p1 point 1 
	 * @param p2 point 2
	 * @param border la couleur du polygone
	 */
	public UnPolygone(Point p1, Point p2, Color border) {
		super(p1, p2, border);
	}
	
	/**
	 * Constructeur de Polygone
	 * @param p1 Point 1 
	 * @param p2 Point 2
	 * @param border Color la couleur du polygone
	 * @param filled Boolean True si
	 * le polygone est plein false sinon
	 */
	public UnPolygone(Point p1, Point p2, Color border, boolean filled) {
		super(p1, p2, border, filled);
	}

}
