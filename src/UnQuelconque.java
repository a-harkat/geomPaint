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
	 * @param lp liste des points 
	 */
	public UnQuelconque(Point p1, Point p2, Point[] p, Color border) {
		super(p1, p2, border);
		listPoints = p ;	

	}
	/**
	 * M�thode qui va permettre de
	 * r�cup�rer la liste des points
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
	 * methode qui �dite (redimensionne) un triangle
	 */
	public void editerQuelconque(Point p1, Point p2) {
		
		if (this.isInToleranceZone(p1, this.getP1())) 
			if (noPointOnPoint(0,p2)) 
				this.setP1(p2)	;		
		
		if (this.isInToleranceZone(p1, this.getP2()))
			if (noPointOnPoint(1, p2))  
				this.setP2(p2);	
		
		for (int i = 0; i<listPoints.length; i++){			
			if (this.isInToleranceZone(p1, listPoints[i])){
				if (noPointOnPoint(i+2, p2)) 
					listPoints[i] = p2;	
			}
		}
	}

	public boolean noPointOnPoint (int j, Point p) {
		boolean b = true ;
		for (int i = 0; i<listPoints.length; i++){	
			if (i!= j)
				if (isInToleranceZone(p,listPoints[i])) 
		        	b = false;											
		    }
		return b;   
	}
	
	public boolean toleranceQuelconque (Point p) {		
		boolean b = false ;
		if ((isInToleranceZone(p,this.getP1()) ||  isInToleranceZone(p,this.getP2()))) b = true ;
		for (int i = 0; i < listPoints.length; i++){
			if ( isInToleranceZone(p,listPoints[i])) b = true ;
		}	
		return b ;
	}
	
	public void deplacerQuelconque (Point p1, Point p2){		
		int xp1 = (int)this.getP1().getX() ;
		int xp2 = (int)this.getP2().getX();		
		int yp1 = (int)this.getP1().getY() ;
		int yp2 = (int)this.getP2().getY();	
		int x = (int) p1.getX();
		int y = (int) p1.getY();
		int nx = (int) (p2.getX()-x) ;
		int ny = (int) (p2.getY()-y) ;
		Point np1 = new Point( xp1+nx, yp1+ny);
		Point np2 = new Point( xp2+nx, yp2+ny);		
		this.setP1(np1);
		this.setP2(np2);						
		for (int i = 0; i<listPoints.length; i++){
			int xp3 = (int)listPoints[i].getX() ;
			int yp3 = (int)listPoints[i].getY() ;
			Point np = new Point( xp3+nx, yp3+ny);
			listPoints[i] = np;
		}
	}
	
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
