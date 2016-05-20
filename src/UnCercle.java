import java.awt.Point;

/**
 * Classe qui represente un Cerle
 * @author Groupe 2
 * @version 1
 */
public class UnCercle extends FigureGeom {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de Cercle
	 * @param p1 point 1 centre
	 * @param p2 point 2 rayon
	 */
	public UnCercle(Point p1, Point p2) {
		super(p1, p2);		
	}

}
