import java.awt.Point;

/**
 * Classe qui represente un Triangle
 * @author Groupe 2
 * @version 1
 */
public class UnTriangle extends UnPolygone{

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
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

}
