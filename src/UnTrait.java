import java.awt.Point;

/**
 * Classe qui sert a dessiner les traits A REVOIR  !
 * @author Groupe 2
 * @version 1
 */
public class UnTrait extends FigureGeom{

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur de Trait 
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public UnTrait(Point p1, Point p2) {
		super(p1, p2);
	}
}
