import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Classe qui sert de controleur graphique pour les triangles
 * @author Groupe 2
 * @version 1
 */

public class ControleurTriangle extends MouseInputAdapter implements ControleurFigure{
	
	boolean edition, deplacement, dessiner ;
	int index ;
	Point p1, p2, p3, pointEditer, pointDeplacer ;
	UnTriangle triangle ;
	ListFigures lsFigures;
	
	/**
	 * Constructeur de ControleurTriangle
	 * @param trs
	 */
	
	public ControleurTriangle(ListFigures trs) {
		lsFigures = trs ;	
		dessiner = true ;
	}

	public void mouseDragged(MouseEvent e){		
		if (edition && !dessiner) {	
			if (triangle.isInToleranceZone(pointEditer, triangle.getP1())) 
				if (triangle.noPoint2(e.getPoint()) && triangle.noPoint3(e.getPoint())) 
					triangle.setLine(e.getPoint(), triangle.getP2());
			if (triangle.isInToleranceZone(pointEditer, triangle.getP2()))
				if (triangle.noPoint1(e.getPoint()) && triangle.noPoint3(e.getPoint())) 
					triangle.setLine(triangle.getP1(), e.getPoint());
			if (triangle.isInToleranceZone(pointEditer, triangle.getP3()))
				if (triangle.noPoint1(e.getPoint()) && triangle.noPoint2(e.getPoint())) 
					triangle.setP3(e.getPoint());
			lsFigures.setFigure(index, triangle);
			pointEditer = e.getPoint() ;
		}			
		if (deplacement && !dessiner) {	
			int xp1 = (int)triangle.getP1().getX() ;
			int xp2 = (int)triangle.getP2().getX();
			int xp3 = (int)triangle.getP3().getX() ;
			int yp1 = (int)triangle.getP1().getY() ;
			int yp2 = (int)triangle.getP2().getY();
			int yp3 = (int)triangle.getP3().getY() ;
			int x = (int) pointDeplacer.getX();
			int y = (int) pointDeplacer.getY();
			int nx = e.getX()-x ;
			int ny = e.getY()-y ;
			Point np1 = new Point( xp1+nx, yp1+ny);
			Point np2 = new Point( xp2+nx, yp2+ny);
			Point np3 = new Point( xp3+nx, yp3+ny);
			triangle.setLine(np1,np2);
			triangle.setP3(np3);
			lsFigures.setFigure(index, triangle);
			pointDeplacer = e.getPoint() ;			
		}
	}
	
	public void mousePressed(MouseEvent e){
			if (indexRectEditer(e.getPoint()) != -1 && ! dessiner){
				edition = true ;
				index = indexRectEditer(e.getPoint());
				pointEditer = e.getPoint() ;
				lsFigures.getFigures().get(index).setSelectOn(true);
				if (lsFigures.getFigures().get(index) instanceof UnTriangle) 
					triangle = (UnTriangle)lsFigures.getFigures().get(index);
			}		
			else if (indexRectDeplacer(e.getPoint()) != -1 && ! dessiner){
				deplacement = true ;			
				index = indexRectDeplacer(e.getPoint());
				pointDeplacer = e.getPoint() ;
				lsFigures.getFigures().get(index).setSelectOn(true);
				if (lsFigures.getFigures().get(index) instanceof UnTriangle) 
					triangle = (UnTriangle)lsFigures.getFigures().get(index);
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
	}
		
	public int indexRectEditer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnTriangle)
				triangle = (UnTriangle)lsFigures.getFigures().get(i);
			if (triangle != null)
				if (triangle.tolerance(p))
					 index = i ;			
			}
		return index ;
	}
	
	public int indexRectDeplacer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnTriangle)
				triangle = (UnTriangle)lsFigures.getFigures().get(i);
			if (triangle != null)
				if (triangle.isInsideTriangle(p))
				 index = i ;					
		}
		return index ;
	}	
	
	public void toggleMode (boolean b){
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			if (lsFigures.getFigures().get(i) instanceof UnTriangle)
				lsFigures.getFigures().get(i).setSelectOn(b);
			lsFigures.setFigure(i,lsFigures.getFigures().get(i));					
		}
		p1 = null ;
		p2 = null ;
		p3 = null ;
	}
}