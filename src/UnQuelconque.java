import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe qui represente une figure quelquonque
 * @author Groupe 2
 * @version 1
 */
public class UnQuelconque extends UnPolygone{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Point> listPoints;
	int nbPoint ;

	public UnQuelconque(Point p1, Point p2, ArrayList<Point> lp) {
		super(p1, p2);
		this.listPoints = lp ;
	}



}
