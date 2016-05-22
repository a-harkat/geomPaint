import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.event.MouseInputAdapter;

/**
 * Classe qui sert de controleur graphique
 * @author Groupe 2
 * @version 1
 */
public class ControleurEdition extends MouseInputAdapter implements ControleurFigure{

	boolean select1;
	boolean select2;
	boolean select3;
	boolean selectAll;
	Point departDeplt;
	ListFigures lsFigures;
	FigureGeom selecTmp;
	int currIndex;	

	/**
	 * Constructeur de ControleurTrait
	 * @param lstFgrs
	 */
	public ControleurEdition(ListFigures lstFgrs) {
		lsFigures = lstFgrs;
		select1 = false;
		select2 = false;
		
	}

	public void mouseDragged(MouseEvent e){
		if(select1) {
			selecTmp.selectOn = true;
			selecTmp.setLine(e.getPoint(), selecTmp.getP2());
			lsFigures.setFigure(currIndex, selecTmp);
		}
		else if(select2) {
			selecTmp.selectOn = true;
			selecTmp.setLine(selecTmp.getP1(), e.getPoint());
			lsFigures.setFigure(currIndex, selecTmp);
		}
		else if(selecTmp instanceof UnTriangle && select3) {
			selecTmp.selectOn = true;
			((UnTriangle)selecTmp).setP3(e.getPoint());
			lsFigures.setFigure(currIndex, selecTmp);
		}
		else if(selectAll) {
			selecTmp.selectOn = true;
			if(departDeplt == null)
				departDeplt = e.getPoint();
			
			if(selecTmp != null) {
			
				int diffP1X = (int)(e.getX() - departDeplt.getX()) + (int)selecTmp.getX1();
				int diffP1Y = (int)(e.getY() - departDeplt.getY()) + (int)selecTmp.getY1();
				Point2D p1 = new Point(diffP1X, diffP1Y);
				
				int diffP2X = (int)(e.getX() - departDeplt.getX()) + (int)selecTmp.getX2();
				int diffP2Y = (int)(e.getY() - departDeplt.getY()) + (int)selecTmp.getY2();
				Point2D p2 = new Point(diffP2X, diffP2Y);
				
				if(selecTmp instanceof UnTriangle) {
					int diffP3X = (int)(e.getX() - departDeplt.getX()) + (int)((UnTriangle)selecTmp).getP3().x;
					int diffP3Y = (int)(e.getY() - departDeplt.getY()) + (int)((UnTriangle)selecTmp).getP3().y;
					Point p3 = new Point(diffP3X, diffP3Y);
					((UnTriangle)selecTmp).setP3(p3);
				}
	
				selecTmp.setLine(p1, p2);
				selecTmp.selectOn = true;
				lsFigures.setFigure(currIndex, selecTmp);
				departDeplt = e.getPoint();
			}
		}
	}

	public void mouseMoved(MouseEvent e){

	}

	public void mousePressed(MouseEvent e){
		int i;
		for (i = 0; i < lsFigures.getFigures().size(); i++) {
			
			selecTmp = lsFigures.getFigures().get(i);
			if(selecTmp instanceof UnTrait) {
				if(traitSelect(selecTmp, e)) {
					basculerSelection();
					lsFigures.setFigure(i, selecTmp);
					break;
				}
			}
			else if(selecTmp instanceof UnCercle){
				if(cercleSelect(selecTmp, e)) {
					basculerSelection();
					lsFigures.setFigure(i, selecTmp);
					break;
				}
			}
			else if(selecTmp instanceof UnRectangle){
				if(rectangleSelect(selecTmp, e)) {
					basculerSelection();
					lsFigures.setFigure(i, selecTmp);
					break;
				}	
			}
			else if(selecTmp instanceof UnTriangle){
				if(triangleSelect(selecTmp, e)) {
					basculerSelection();
					lsFigures.setFigure(i, selecTmp);
					break;
				}
				
			}
			else if(selecTmp instanceof UnQuelconque){
				
			}
		}
		
		if(i < lsFigures.getFigures().size())
			currIndex = i;
		
		if(lsFigures.getFigures().size() != 0) { // ce bloque est a ameliorer
			if (selecTmp instanceof UnTrait || 
				selecTmp instanceof UnRectangle ||
				selecTmp instanceof UnCercle ) {
				if(selecTmp.selectionP1(e.getPoint(), FigureGeom.TOLERANCE)) {
					select1 = true;
				}
				else if(selecTmp.selectionP2(e.getPoint(), FigureGeom.TOLERANCE)) {
					select2 = true;
				}
				else if(selecTmp instanceof UnTrait && traitSelect(selecTmp, e))
					selectAll = true;
				else if(selecTmp instanceof UnRectangle && rectangleSelect(selecTmp, e))
					selectAll = true;
				else if(selecTmp instanceof UnCercle && cercleSelect(selecTmp, e))
					selectAll = true;
				else {
					select1 = false;
					select2 = false;
					selectAll = false;
				}
			}
			else if(selecTmp instanceof UnTriangle) {
				if(selecTmp.selectionP1(e.getPoint(), FigureGeom.TOLERANCE)) {
					select1 = true;
				}
				else if(selecTmp.selectionP2(e.getPoint(), FigureGeom.TOLERANCE)) {
					select2 = true;
				}
				else if(((UnTriangle)selecTmp).selectionP3(e.getPoint(), FigureGeom.TOLERANCE)) {
					select3 = true;
				}
				else if(triangleSelect(selecTmp, e))
					selectAll = true;
			}
		}
	}

	public void mouseReleased(MouseEvent e){
		select1 = false;
		select2 = false;
		select3 = false;
		selectAll = false;
		departDeplt = null;
	}

	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
	 * @param t trait a tester
	 * @param e l'evenement de selection
	 * @return slct true sinon false
	 */
	public boolean traitSelect(FigureGeom t, MouseEvent e) {		
		boolean slct = false;
		if(t.ptSegDist(e.getX(), e.getY()) <= FigureGeom.TOLERANCE)
			slct = true;
		return slct;
	}
	
	/**
	 * @param c cercle a tester
	 * @param e l'evenement de selection
	 * @return slct true sinon false
	 */
	public boolean cercleSelect(FigureGeom c, MouseEvent e) {
		boolean slct = false;
		if(((UnCercle)c).insideCercle(e.getPoint()) ||
			c.selectionP1(e.getPoint(), FigureGeom.TOLERANCE) ||
			c.selectionP2(e.getPoint(), FigureGeom.TOLERANCE))
			slct = true;
		return slct;
	}
	
	/**
	 * @param r rectangle a tester
	 * @param e l'evenement de selection
	 * @return slct true sinon false
	 */
	public boolean rectangleSelect(FigureGeom r, MouseEvent e) {
		boolean slct = false;
		if(((UnRectangle)r).insideRectangle(e.getPoint()) ||
			r.selectionP1(e.getPoint(), FigureGeom.TOLERANCE) ||
			r.selectionP2(e.getPoint(), FigureGeom.TOLERANCE))
			slct = true;
		return slct;
	}
	
	/**
	 * @param t triangle a tester
	 * @param e l'evenement de selection
	 * @return slct true sinon false
	 */
	public boolean triangleSelect(FigureGeom t, MouseEvent e) {
		boolean slct = false;
		if(((UnTriangle)t).isInsideTriangle(e.getPoint()) ||
			t.selectionP1(e.getPoint(), FigureGeom.TOLERANCE) ||
			t.selectionP2(e.getPoint(), FigureGeom.TOLERANCE) ||
			((UnTriangle)t).selectionP3(e.getPoint(), FigureGeom.TOLERANCE))
			slct = true;
		return slct;
	}

	
	/**
	 * methode bascule la selection
	 * de la figure
	 */
	public void basculerSelection() {
		if(!selecTmp.selectOn)
			selecTmp.selectOn = true;
		else
			selecTmp.selectOn = false;
	}
}
