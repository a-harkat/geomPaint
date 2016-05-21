import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Classe qui represente les figures geometriques
 * @author Groupe 2
 * @version 1
 */
public abstract class FigureGeom extends Line2D.Double {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	boolean selectOn = false;
	
	/**
	 * Constructeur de Figure geometrique
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public FigureGeom(Point p1, Point p2) {
		super(p1, p2) ;
	}

	public boolean isSelectOn() {
		return selectOn;
	}

	public void setSelectOn(boolean selectOn) {
		this.selectOn = selectOn;
	}
}
