import java.awt.Color;
import java.awt.Point;

/**
 * Classe qui represente les figures geometriques
 * @author Groupe 2
 * @version 1
 */
public abstract class FigureGeom  {

	private static final int tolerance = 5;
	private Point P1, P2 ;
	private boolean selectOn = false;
	private boolean figureFilled;
	
	/**
	 * Couleur de la bordure de la figure 
	 */
	private Color border_color;

	/**
	 *Couleur pleine de la figure
	 */
	private Color full_color;
	
	/**
	 * Constructeur de Figure geometrique
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param border couleur de la figure
	 */
	public FigureGeom(Point p1, Point p2, Color border) {
		border_color = border;
		P1 = p1 ;
		P2 = p2 ;
		figureFilled = false;
	}
	
	/**
	 * getter
	 * @return P1
	 */
	public Point getP1() {
		return P1 ;
	}
	
	/**
	 * getter
	 * @return P2
	 */
	public Point getP2() {
		return P2;
	}
	
	/**
	 * setter
	 * @param p nouveau point
	 */
	public void setP1(Point p) {
		 P1 = p ;
	}
	
	/**
	 * setter
	 * @param p nouveau point
	 */
	public void setP2(Point p) {
		P2 = p;
	}

	/**
	 * retourne si un point est dans la zone de tolerance d'un autre point
	 * @param p le point a tester
	 * @return true si le point est dans la zone
	 */
	public boolean tolerance (Point p) {
		return isInToleranceZone(p,this.getP1()) || isInToleranceZone(p,this.getP2());		   
	}	
	
	/**
	 * retourne si un point est dans le cercle qui a pour centre un point donne
	 * @param p1 
	 * @param p2 
	 * @return true si le point est dans le cercle
	 */
	public boolean isInToleranceZone (Point p1, Point p2) {
		double dx = p1.getX()- p2.getX();
	   double dy = p1.getY()- p2.getY();
	   return dx * dx + dy * dy <= tolerance * tolerance;
	}
	
	/**
	 * teste si le point n'est pas la zone de tolerance de P1
	 * @param p1 le point a tester
	 * @return true si le point n'est pas proche de P1
	 */
	public boolean noPoint1 (Point p1) {
		return  (!(this.isInToleranceZone(p1, this.getP1())));	    	
	}
	/**
	 * teste si le point n'est pas la zone de tolerance de P2
	 * @param p1 le point a tester
	 * @return true si le point n'est pas proche de P2
	 */
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
	
	/**
	 * modifie la position d'un point de la figure
	 * @param p1 point depart
	 * @param p2 point arrivee
	 */
	public void editerFigure2p(Point p1, Point p2){		
		if (this.isInToleranceZone(p1, this.getP1()))
			if (this.noPoint2(p2)) 
				this.setP1(p2);
		if (this.isInToleranceZone(p1, this.getP2()))
			if (this.noPoint1(p2) )
				this.setP2(p2);		
	}
	
	/**
	 * deplace les deux points
	 * @param p1 point de depart
	 * @param p2 point d'arrivee
	 */
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
	
	/**
	 * Getteur de la couleur de la bordure de la figure
	 * @return Color de la bordure
	 */
	public Color getBorder_color() {
		return border_color;
	}
	
	/**
	 * Setteur de la couleur de la bordure de la figure
	 * @param new_border_color nouvelle couleur
	 */
	public void setBorder_color(Color new_border_color) {
		this.border_color = new_border_color;
	}
	
	/**
	 * Getteur de la couleur pleine de la figure
	 * @return full_color pleine de la figure
	 */
	public Color getFull_color() {
		return full_color;
	}
	
	/**
	 * Setteur de la couleur pleine de la figure
	 * @param new_full_color nouvelle couleur
	 */
	public void setFull_color(Color new_full_color) {
		this.full_color = new_full_color;
	}
	
	/**
	 * Methode qui va indiquer si
	 * la figure est pleine ou non
	 * @return Boolean true si la
	 * figure est pleine, false sinon
	 */
	public boolean getFigureFilled () {
		return this.figureFilled;
	}
	
	/**
	 * Methode qui va indiquer si
	 * la figure doit etre pleine
	 * ou non
	 * @param bool Boolean true si
	 * la figure doit etre pleine,
	 * false sinon
	 */
	public void setFigureFilled (boolean bool) {
		this.figureFilled = bool;
	}
	/**
	 * getter 
	 * @return selectOn
	 */
	public boolean isSelectOn() {
		return selectOn;
	}

	/**
	 * setter
	 * @param selectOn nouvelle valeur
	 */
	public void setSelectOn(boolean selectOn) {
		this.selectOn = selectOn;
	}

	/**
	 * methode qui deplace n'importe quelle figure par le mousseDragegd
	 * @param p point d'arrivee
	 * @param pointEditer point de depart
	 * @param lsFigures la list qui contient la figure a deplacer
	 * @param index de la figure a deplacer
	 */
	public void deplacerFigure(Point p, Point pointEditer, ListFigures lsFigures, int index) {
		if (this instanceof UnRectangle) {
			UnRectangle rectangle = (UnRectangle) lsFigures.getFigures().get(index);
			this.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}
		if (this instanceof UnTrait) {
			UnTrait trait = (UnTrait) lsFigures.getFigures().get(index);
			trait.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, trait);
		}
		if (this instanceof UnCercle) {
			UnCercle rectangle = (UnCercle) lsFigures.getFigures().get(index);
			this.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}				
		if (this instanceof UnTriangle) {	
			UnTriangle triangle = (UnTriangle) lsFigures.getFigures().get(index);
			triangle.deplacerTriangle(pointEditer, p) ;
			lsFigures.setFigure(index, triangle);
		}
		if (this instanceof UnQuelconque) {	
			UnQuelconque quelconque = (UnQuelconque) lsFigures.getFigures().get(index);
			quelconque.deplacerQuelconque(pointEditer,  p);
			lsFigures.setFigure(index, quelconque);
		}		
	}
	
	/**
	 * methode qui edite n'importe quelle figure par le mousseDragegd
	 * @param p point d'arrivee
	 * @param pointEditer point de depart
	 * @param lsFigures la list qui contient la figure a modifier
	 * @param index de la figure a modifier
	 */
	public void editerFigure(Point p, Point pointEditer, ListFigures lsFigures, int index) {
		if (this instanceof UnRectangle) {
			UnRectangle rectangle = (UnRectangle) lsFigures.getFigures().get(index);
			rectangle.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}
		if (this instanceof UnTrait) {
			UnTrait trait = (UnTrait) lsFigures.getFigures().get(index);
			trait.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, trait);
		}
		if (this instanceof UnCercle) {
			UnCercle cercle = (UnCercle) lsFigures.getFigures().get(index);
			cercle.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, cercle);
		}				
		if (this instanceof UnTriangle) {	
			UnTriangle triangle = (UnTriangle) lsFigures.getFigures().get(index);
			triangle.editerTriangle(pointEditer, p) ;
			lsFigures.setFigure(index, triangle);
		}
		if (this instanceof UnQuelconque) {	
			UnQuelconque quelconque = (UnQuelconque) lsFigures.getFigures().get(index);
			quelconque.editerQuelconque(pointEditer,  p);
			lsFigures.setFigure(index, quelconque);
		}		
	}
	
}