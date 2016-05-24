import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe qui represente une figure quelquonque
 * @author Groupe 2
 * @version 1
 */
public class UnQuelconque extends UnPolygone{
	
	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contient la liste des points de la figure 
	 */
	ArrayList<Point> listPoints;
	
	/**
	 * Represente le nombre de points de la figure 
	 */
	int nbPoint ;
	
	/**
	 * Constructeur de figure quelquonque 
	 * @param p1 point 1
	 * @param p2 point 2 
	 * @param lp liste des points 
	 */
	public UnQuelconque(Point p1, Point p2, ArrayList<Point> lp) {
		super(p1, p2);
		this.listPoints = lp ;
	}
	
	/**
	 * Méthode qui va permettre de
	 * récupérer la liste des points
	 * du polygone
	 * @return ArrayList<Point> Liste
	 * des points du polygone quelconque
	 */
	public ArrayList<Point> getListPoint() {
		return this.listPoints;
	}
}
