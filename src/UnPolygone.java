import java.awt.Point;

/**
 * Classe qui represente un Polygone
 * @author Groupe 2
 * @version 1
 */
public abstract class UnPolygone extends FigureGeom {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de Polygone
	 * @param p1 point 1 
	 * @param p2 point 2
	 */
	public UnPolygone(Point p1, Point p2) {
		super(p1, p2);
		// TODO Auto-generated constructor stub
	}

}
