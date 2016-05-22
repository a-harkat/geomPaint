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
public class ControleurEdition extends MouseInputAdapter implements ControleurFigure{

	boolean select1;
	boolean select2;
	boolean selectAll;
	Point departDeplt;
	/**
	 * 
	 */
	public static final int TOLER = 5;
	ListFigures lsFigures;

	UnTrait traitTmp;
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

		if(select2) {
			selecTmp.selectOn = true;
			selecTmp.setLine(selecTmp.getP1(), e.getPoint());
			lsFigures.setFigure(currIndex, selecTmp);
		}
		
		if(!select1 && !select2 && selectAll) {
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

	}

	public void mousePressed(MouseEvent e){

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
				if(((UnTrait) selecTmp).selectionP1(e.getPoint(), TOLER)) {
					select1 = true;
				}
				else if(((UnTrait) selecTmp).selectionP2(e.getPoint(), TOLER)) {
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

	public void mouseReleased(MouseEvent e){
		select1 = false;
		select2 = false;
		selectAll = false;
		departDeplt = null;
	}

	public void mouseClicked(MouseEvent e) {


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
