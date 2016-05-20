import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Classe qui sert de controleur graphique
 * @author Groupe 2
 * @version 1
 */
public class ControleurTrait extends MouseInputAdapter implements ControleurFigure{

	boolean select1;
	boolean select2;
	boolean selectAll;
	boolean dessiner;
	boolean selectionner;
	boolean newAdded;
	Point departDeplt;
	/**
	 * 
	 */
	public static final int TOLER = 5;
	ListFigures lsFigures;

	Point firstPt = null;
	UnTrait traitTmp;
	FigureGeom selecTmp;
	int currIndex;
	
	/**
	 * Constructeur de ControleurTrait
	 * @param trs
	 */
	public ControleurTrait(ListFigures trs) {
		lsFigures = trs;
		select1 = false;
		select2 = false;
		dessiner = true;
		selectionner = false;
		newAdded = false;
	}

	public void mouseDragged(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());

		if(select1) {
			selecTmp.selectOn = true;
			selecTmp.setLine(p, selecTmp.getP2());
			lsFigures.setFigure(currIndex, selecTmp);
		}

		if(select2) {
			selecTmp.selectOn = true;
			selecTmp.setLine(selecTmp.getP1(), p);
			lsFigures.setFigure(currIndex, selecTmp);
		}
		
		if(selectionner && !select1 && !select2 && selectAll) {
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
	
				selecTmp.setLine(p1, p2);
				selecTmp.selectOn = true;
				lsFigures.setFigure(currIndex, selecTmp);
				departDeplt = e.getPoint();
			}
		}
	}

	public void mouseMoved(MouseEvent e){
		if(dessiner && firstPt != null) {
			traitTmp = new UnTrait(firstPt, e.getPoint());
			if(!newAdded) {
				lsFigures.addFigure(traitTmp);
				newAdded = true;
			}
			else {
				lsFigures.setFigure(lsFigures.getFigures().size()-1, traitTmp);
			}
		}
	}

	public void mousePressed(MouseEvent e){

		Point ptEvt = e.getPoint();

		if(selectionner) {
			int i;
			for (i = 0; i < lsFigures.getFigures().size(); i++) {
				selecTmp = lsFigures.getFigures().get(i);
				if(traitSelect(selecTmp, e)) {
					if(!selecTmp.selectOn)
						selecTmp.selectOn = true;
					else
						selecTmp.selectOn = false;
					
					lsFigures.setFigure(i, selecTmp);
					break;
				}
			}
			if(i < lsFigures.getFigures().size())
				currIndex = i;
			
			if(lsFigures.getFigures().size() != 0) {
				if (selecTmp instanceof UnTrait) {
					if(((UnTrait) selecTmp).selectionP1(ptEvt, TOLER)) {
						select1 = true;
					}
					else if(((UnTrait) selecTmp).selectionP2(ptEvt, TOLER)) {
						select2 = true;
					}
					else if(traitSelect(selecTmp, e))
						selectAll = true;
					else {
						select1 = false;
						select2 = false;
						selectAll = false;
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e){
		select1 = false;
		select2 = false;
		selectAll = false;
		departDeplt = null;

		if(dessiner) {
			traitTmp = null;
			newAdded = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			basculerFonct();
		}

		if (SwingUtilities.isLeftMouseButton(e)) {
			if (dessiner) {	
				if(firstPt == null)
					firstPt = new Point(e.getPoint());
				else
					firstPt = null;
			}
		}

	}

	/**
	 * methode bascule entre
	 * modes dessiner et selectionner
	 */
	public void basculerFonct() {
		if (selectionner) {
			dessiner = true;
			selectionner = false;
			System.out.println("mode dessin");
		}
		else if (dessiner) {
			dessiner = false;
			selectionner = true;
			System.out.println("mode selection");
		}
	}

	/**
	 * @param t
	 * @param e
	 * @return true sinon false
	 */
	public boolean traitSelect(FigureGeom t, MouseEvent e) {		
		if(t.ptSegDist(e.getX(), e.getY()) <= TOLER)
			return true;
		else return false;
	}
}
