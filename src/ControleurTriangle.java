import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.event.MouseInputAdapter;

/**
 * Classe qui sert de controleur graphique
 * @author Groupe 2
 * @version 1
 */
public class ControleurTriangle extends MouseInputAdapter implements ControleurFigure{
	
	boolean edition, deplacement ;
	int index ;
	Point p1, p2, p3, pointEditer, pointDeplacer ;
	UnTriangle triangle ;
	ListFigures lsFigures;
	
	private final static int tolerance = 5;
	/**
	 * Constructeur de ControleurTriangle
	 * @param trs
	 */
	public ControleurTriangle(ListFigures trs) {
		lsFigures = trs ;	
	}

	public void mouseDragged(MouseEvent e){		
		if (edition) {			
			UnTriangle Tmp = (UnTriangle)lsFigures.getFigures().get(index);
			if (tolerance(pointEditer, Tmp.getP1())) Tmp.setLine(e.getPoint(), Tmp.getP2());
			if (tolerance(pointEditer, Tmp.getP2()))Tmp.setLine(Tmp.getP1(), e.getPoint());
			if (tolerance(pointEditer, Tmp.getP3())) Tmp.setP3(e.getPoint());
			lsFigures.setFigure(index, Tmp);
			pointEditer = e.getPoint() ;
		}	
		
		if (deplacement) {			
			UnTriangle Tmp = (UnTriangle)lsFigures.getFigures().get(0);
			int xp1 = (int)Tmp.getP1().getX() ;
			int xp2 = (int)Tmp.getP2().getX();
			int xp3 = (int)Tmp.getP3().getX() ;
			int yp1 = (int)Tmp.getP1().getY() ;
			int yp2 = (int)Tmp.getP2().getY();
			int yp3 = (int)Tmp.getP3().getY() ;
			int x = (int) pointEditer.getX();
			int y = (int) pointEditer.getX();
			Point p1 = new Point( ((e.getX()-x)+xp1), ( (e.getY()-y)+yp1));
			Point p2 = new Point( ((e.getX()-x)+xp2), ( (e.getY()-y)+yp2));
			Point p3 = new Point( ((e.getX()-x)+xp3), ( (e.getY()-y)+yp3));
			Tmp.setLine(p1,p2);
			Tmp.setP3(p3);
			lsFigures.setFigure(0, Tmp);
			pointDeplacer = e.getPoint() ;
		}
	
	}
	
	public void mousePressed(MouseEvent e){
		if (indexRectEditer(e.getPoint()) != -1){
			edition = true ;
			index = indexRectEditer(e.getPoint());
			pointEditer = e.getPoint() ;
		}
		pointEditer = e.getPoint() ;
		if (indexRectDeplacer(e.getPoint()) != -1){
			deplacement = true ;			
			index = indexRectDeplacer(e.getPoint());
			pointDeplacer = e.getPoint() ;
		}
	}
	
	public void mouseReleased(MouseEvent e){
		edition = false ;
		deplacement = false ;
		pointDeplacer = null;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if (p1 == null) 
			p1 = new Point (e.getPoint()) ;		
		else if (p2 == null) 
			p2 = new Point (e.getPoint()) ;
		else if (p3 == null) 
			p3 = new Point (e.getPoint()) ;
		if (p3 != null){
			triangle = new UnTriangle(p1,p2,p3);
			lsFigures.addFigure(triangle);
			p1 = null ;
			p2 = null ;
			p3 = null ;
		}
	}
		
	public int indexRectEditer (Point p){
		int index = -1 ;
		UnTriangle tr = null;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnTriangle)
					tr = (UnTriangle)lsFigures.getFigures().get(i);
			if (tr != null)
				if (tolerance(p,tr.getP1()) || tolerance(p,tr.getP2()) || tolerance(p,tr.getP3()) )
					 index = i ;			
			}
		return index ;
	}
	
	public boolean tolerance (Point2D p1, Point2D p2) {
		   double dx = p1.getX()- p2.getX();
		   double dy = p1.getY()- p2.getY();
		   return dx * dx + dy * dy <= tolerance * tolerance;
	}
	
	public int indexRectDeplacer (Point p){
		int index = -1 ;
		UnTriangle tr = null;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnTriangle)
				tr = (UnTriangle)lsFigures.getFigures().get(i);
			if (tr != null)
				if (isInsideTriangle(p,tr))
				 index = i ;	
		}
		return index ;
	}
	
	public boolean isInsideTriangle(Point2D p, UnTriangle t)
    {	    
	    if(t.contains((int) p.getX(),(int) p.getY())) return true; 
	    return false;
    }
}
