import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Classe qui sert de controleur graphique pour les triangles
 * @author Groupe 2
 * @version 1
 */

public class ControleurRectangle extends MouseInputAdapter implements ControleurFigure{
	
	boolean edition, deplacement, dessiner ;
	int index ;
	Point p1, p2, pointEditer, pointDeplacer ;
	UnRectangle rectangle ;
	ListFigures lsFigures;
	
	/**
	 * Constructeur de ControleurTriangle
	 * @param trs
	 */
	
	public ControleurRectangle(ListFigures trs) {
		lsFigures = trs ;	
		dessiner = true ;
	}

	public void mouseDragged(MouseEvent e){		
		if (edition && !dessiner) {	
			if (rectangle.isInToleranceZone(pointEditer, rectangle.getP1()))
				rectangle.setLine(e.getPoint(),rectangle.getP2());
			if (rectangle.isInToleranceZone(pointEditer, rectangle.getP2()))
				rectangle.setLine(rectangle.getP1(), e.getPoint());
			lsFigures.setFigure(index, rectangle);
			pointEditer = e.getPoint() ;
		}			
		if (deplacement && !dessiner) {	
			int xp1 = (int)rectangle.getP1().getX() ;
			int xp2 = (int)rectangle.getP2().getX();
			int yp1 = (int)rectangle.getP1().getY() ;
			int yp2 = (int)rectangle.getP2().getY();
			int x = (int) pointDeplacer.getX();
			int y = (int) pointDeplacer.getY();
			int nx = e.getX()-x ;
			int ny = e.getY()-y ;
			Point np1 = new Point( xp1+nx, yp1+ny);
			Point np2 = new Point( xp2+nx, yp2+ny);
			rectangle.setLine(np1,np2);
			lsFigures.setFigure(index, rectangle);
			pointDeplacer = e.getPoint() ;			
		}
	}
	
	public void mousePressed(MouseEvent e){
			if (indexCercleEditer(e.getPoint()) != -1 && ! dessiner){
				edition = true ;
				index = indexCercleEditer(e.getPoint());
				pointEditer = e.getPoint() ;
				lsFigures.getFigures().get(index).setSelectOn(true);
				if (lsFigures.getFigures().get(index) instanceof UnRectangle) 
					rectangle = (UnRectangle)lsFigures.getFigures().get(index);
			}		
			else if (indexCercleDeplacer(e.getPoint()) != -1 && ! dessiner){
				deplacement = true ;			
				index = indexCercleDeplacer(e.getPoint());
				pointDeplacer = e.getPoint() ;
				lsFigures.getFigures().get(index).setSelectOn(true);
				if (lsFigures.getFigures().get(index) instanceof UnRectangle) 
					rectangle = (UnRectangle)lsFigures.getFigures().get(index);
			}
	}
	
	public void mouseReleased(MouseEvent e){
		edition = false ;
		deplacement = false;
		pointDeplacer = null;		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if (dessiner) {
				dessiner = false;
				toggleMode (true);
				System.out.println("mode édition");
			}
			else {
				dessiner = true;
				toggleMode (false);
				System.out.println("mode déssin");
			}
		}
		else if ( dessiner)  {
			if (p1 == null) 
				p1 = new Point (e.getPoint()) ;		
			else if (p2 == null) 
				p2 = new Point (e.getPoint()) ;
			
			if (p2 != null){
				rectangle = new UnRectangle(p1,p2);
				lsFigures.addFigure(rectangle);
				p1 = null ;
				p2 = null ;
			}
		}
	}
		
	public int indexCercleEditer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnRectangle)
				rectangle = (UnRectangle)lsFigures.getFigures().get(i);
			if (rectangle != null)
				if (rectangle.tolerance(p))
					 index = i ;			
			}
		return index ;
	}
	
	public int indexCercleDeplacer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnRectangle)
				rectangle = (UnRectangle)lsFigures.getFigures().get(i);
			if (rectangle != null)
				if (rectangle.insideRectangle(p))
				 index = i ;					
		}
		return index ;
	}	
	
	public void toggleMode (boolean b){
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnRectangle)
				lsFigures.getFigures().get(i).setSelectOn(b);
			lsFigures.setFigure(i,lsFigures.getFigures().get(i));					
		}
		p1 = null ;
		p2 = null ;
	}
}