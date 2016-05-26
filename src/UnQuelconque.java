import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;


/**
 * Classe qui represente une figure quelquonque
 * @author Groupe 2
 * @version 1
 */

public class UnQuelconque extends UnPolygone{
	
	/**
	 * Contient la liste des points de la figure 
	 */
	private Point [] listPoints;

	/**
	 * Constructeur de figure quelquonque 
	 * @param p1 point 1
	 * @param p2 point 2 
	 * @param p tableau de points
	 * @param border couleur du quelconque
	 */
	public UnQuelconque(Point p1, Point p2, Point[] p, Color border) {
		super(p1, p2, border);
		listPoints = p ;	

	}
	
	/**
	 * Constructeur de figure quelquonque 
	 * @param p1 Point 1
	 * @param p2 Point 2 
	 * @param p Point[] tableau de points
	 * @param border Color couleur du quelconque
	 * @param filled Boolean True si le polygone
	 * est plein false sinon
	 */
	public UnQuelconque(Point p1, Point p2, Point[] p, Color border, boolean filled) {
		super(p1, p2, border, filled);
		listPoints = p ;	

	}
	/**
	 * Methode qui va permettre de
	 * recuperer la liste des points
	 * du polygone
	 * @return Point [] Liste
	 * des points du polygone quelconque
	 */
	public Point []  getListPoints() {
		return listPoints;
	}

	/**
	 * @param listPoints the listPoints to set
	 */
	public void setListPoints(Point []  listPoints) {
		this.listPoints = listPoints;
	}
	/**
	 * Change la position d'un point du polygone
	 * @param p1 point de depart
	 * @param p2 point d'arrivee
	 */
	public void editerQuelconque(Point p1, Point p2) {
		
		if (this.isInToleranceZone(p1, this.getP1())
				&& noPointOnPoint(0,p2))
				this.setP1(p2);		
		
		if (this.isInToleranceZone(p1, this.getP2())
				&& noPointOnPoint(1, p2))
				this.setP2(p2);	
		
		for (int i = 0; i<listPoints.length; i++){			
			if (this.isInToleranceZone(p1, listPoints[i])
					&& noPointOnPoint(i+2, p2)){
					listPoints[i] = p2;	
			}
		}
	}
	/**
	 * retourne vrai si un point n'est pas sur un des sommets
	 * du quelconque
	 * @param j l'index du point du quelconque a tester
	 * @param p le point a tester
	 * @return true si vrai
	 */
	public boolean noPointOnPoint (int j, Point p) {
		boolean b = true ;
		for (int i = 0; i<listPoints.length; i++){
			if (i!= j && isInToleranceZone(p,listPoints[i]))
				b = false;											
		    }
		return b;   
	}
	
	/**
	 * teste si un point est proche
	 * a un point du polygone
	 * @param p le point a tester
	 * @return b true ou false
	 */
	public boolean toleranceQuelconque (Point p) {		
		boolean b = false ;
		if (isInToleranceZone(p,this.getP1()) 
				||  isInToleranceZone(p,this.getP2()))
			b = true ;
		for (int i = 0; i < listPoints.length; i++){
			if (isInToleranceZone(p,listPoints[i]))
				b = true ;
		}	
		return b ;
	}
	/**
	 * deplace les points du polygone
	 * @param p1 point de depart
	 * @param p2 point d'arrivee
	 */
	public void deplacerQuelconque (Point p1, Point p2){		
		deplacerFigure2p(p1, p2);
		for (int i = 0; i<listPoints.length; i++){
			int xp3 = (int)listPoints[i].getX() ;
			int yp3 = (int)listPoints[i].getY() ;
			Point np = new Point(xp3 + (int)(p2.getX() - p1.getX()),
					yp3 + (int)(p2.getY() - p1.getY()));
			listPoints[i] = np;
		}
	}
	/**
	 * Teste si un point est dans le polygone
	 * @param p1 le point a tester
	 * @return true si vrai
	 */
	public boolean insidePolygone(Point p1) {
		int taille = getListPoints().length ;
		int [] X = new int [taille+2];
		X[0] = (int)getP1().getX() ;
		X[1] = (int)getP2().getX();
		int [] Y = new int [taille+2] ;
		Y[0] = (int)getP1().getY() ; 
		Y[1] = (int)getP2().getY();
		
		for (int j = 0; j< taille;j++){
			X[j+2] = (int) getListPoints()[j].getX();
			Y[j+2] = (int) getListPoints()[j].getY();
		}
		Polygon p = new Polygon(X, Y,taille+2);
		return p.contains(p1);
	}
}
