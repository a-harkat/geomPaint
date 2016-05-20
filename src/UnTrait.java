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
	
	// a check 
	public Point getP1() {
		return new Point((int)getX1(), (int)getY1());
	}
	
	// a check 
	public Point getP2() {
		return new Point((int)getX2(), (int)getY2());
	}
	
	/**
	 * Selectionneur du premier point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return 2BOOLEAN Pas bonne prog
	 */
	public boolean selectionP1(Point p, int tolerance) {
		int distance = distance(getP1(), p);
		if (distance < tolerance) return true;
		else return false;
	}
	
	/**
	 * Selectionneur du deuxieme point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return 2BOOLEAN Pas bonne prog
	 */
	public boolean selectionP2(Point p, int tolerance) {
		int distance = distance(getP2(), p);
		if (distance < tolerance) return true;
		else return false;
	}
	
	/**
	 * Renvoie la distance entre deux points
	 * @param p1 point 1
	 * @param p2 point 2
	 * @return la distance entre les deux points
	 */
	public int distance(Point p1, Point p2){
		return (int)(Math.sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + 
				((p1.y - p2.y)*(p1.y - p2.y)))); 
	}
}
