import java.awt.Point;


/**
 * Classe qui represente les figures geometriques
 * @author Groupe 2
 * @version 1
 */
public abstract class FigureGeom  {

	private static final int tolerance = 5;
	private Point P1, P2 ;
	boolean selectOn = false;
	
	public boolean isSelectOn() {
		return selectOn;
	}

	public void setSelectOn(boolean selectOn) {
		this.selectOn = selectOn;
	}

	
	/**
	 * Constructeur de Figure geometrique
	 * @param p1 point 1
	 * @param p2 point 2
	 */
	public FigureGeom(Point p1, Point p2) {
		P1 = p1 ;
		P2 = p2 ;
	}
	
	// a check 
	public Point getP1() {
		return P1 ;
	}
	
	// a check 
	public Point getP2() {
		return P2;
	}
	
	public void setP1(Point p) {
		 P1 = p ;
	}
	
	// a check 
	public void setP2(Point p) {
		P2 = p;
	}


	
	/**
	 * retourne si un point est dans la zone de tol�rance d'un autre point
	 */
	public boolean tolerance (Point p) {
		return isInToleranceZone(p,this.getP1()) || isInToleranceZone(p,this.getP2());		   
	}	
	
	/**
	 * retourne si un point est dans le cercle qui a pour centre un point donn�
	 */
	public boolean isInToleranceZone (Point p1, Point p2) {
		double dx = p1.getX()- p2.getX();
	   double dy = p1.getY()- p2.getY();
	   return dx * dx + dy * dy <= tolerance * tolerance;
	}
	
	public boolean noPoint1 (Point p1) {
		return  (!(this.isInToleranceZone(p1, this.getP1())));	    	
	}
	public boolean noPoint2 (Point p1) {		    
		return (!(this.isInToleranceZone(p1, this.getP2())));
	}
	
	/**
	 * Selectionneur du premier point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return slct selectionne ou non
	 */
	public boolean selectionP1(Point p, int tolerance) {
		boolean slct = false;
		int distance = distance(getP1(), p);
		if (distance < tolerance) slct = true;
		return slct;
	}
	
	/**
	 * Selectionneur du deuxieme point
	 * @param p point a selectionner 
	 * @param tolerance marge appliquee
	 * @return slct selectionne ou non
	 */
	public boolean selectionP2(Point p, int tolerance) {
		boolean slct = false;
		int distance = distance(getP2(), p);
		if (distance < tolerance) slct = true;
		return slct;
	}
	
	/**
	 * Renvoie la distance entre deux points
	 * @param p1 point 1
	 * @param p2 point 2
	 * @return la distance entre les deux points
	 */
	public int distance(Point p1, Point p2){
		return (int)(Math.sqrt(((p1.getX() - p2.getX())*(p1.getX() - p2.getX())) + 
				((p1.getY() - p2.getY())*(p1.getY() - p2.getY())))); 
	}

	/**
	 * @return the edition
	 */
	public int nbPoints(){
		int nbPoints = 0 ;
		if (this instanceof UnRectangle || this instanceof UnTrait || this instanceof UnCercle) 
			nbPoints = 2;
		else if (this instanceof UnTriangle) nbPoints = 2;
		return nbPoints;			
	}
	
	public void editerFigure2p(Point p1, Point p2){		
		if (this.isInToleranceZone(p1, this.getP1()))
			if (this.noPoint2(p2)) 
				this.setP1(p2);
		if (this.isInToleranceZone(p1, this.getP2()))
			if (this.noPoint1(p2) )
				this.setP2(p2);		
	}
	
	public void deplacerFigure2p(Point p1, Point p2){
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
	}
	
}