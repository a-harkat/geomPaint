import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Classe qui represente les figures geometriques
 * @author Groupe 2
 * @version 1
 */
public abstract class FigureGeom extends Line2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FigureGeom(Point p1, Point p2) {
		super(p1, p2) ;
	}
}
