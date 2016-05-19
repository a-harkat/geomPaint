import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class ControleurGraph extends MouseInputAdapter{
	
	boolean select1;
	boolean select2;
	boolean dessiner;
	boolean selectionner;
	boolean newAdded;
	public static final int TOLER = 5;
	ListTrait lsTraits;
	
	Point firstPt = null;
	Trait traitTmp;
	Trait selecTmp;
	int currIndex;

	public ControleurGraph(ListTrait trs) {
		lsTraits = trs;
		select1 = false;
		select2 = false;
		dessiner = true;
		selectionner = false;
		newAdded = false;
	}

	public void mouseDragged(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		
		if(select1) {
			selecTmp.setLine(p, selecTmp.getP2());
			lsTraits.setTrait(currIndex, selecTmp);
		}
		
		if(select2) {
			selecTmp.setLine(selecTmp.getP1(), p);
			lsTraits.setTrait(currIndex, selecTmp);
		}

		if(dessiner) {
			traitTmp = new Trait(firstPt, e.getPoint());
			if(!newAdded) {
				lsTraits.addTrait(traitTmp);
				newAdded = true;
			}
			else {
				lsTraits.setTrait(lsTraits.traits.size()-1, traitTmp);
			}
		}
	}
	
	public void mousePressed(MouseEvent e){
		
		Point ptEvt = e.getPoint();
		
		if(selectionner) {
			int i;
			for (i = 0; i < lsTraits.traits.size(); i++) {
			selecTmp = lsTraits.traits.get(i);
				if(traitSelect(selecTmp, e)) {
					System.out.println("i am selected");
					break;
				}
			}
			currIndex = i;
			
			if(selecTmp.selectionP1(ptEvt, TOLER)) {
				select1 = true;
			}
			else if(selecTmp.selectionP2(ptEvt, TOLER)) {
				select2 = true;
			}
			else {
				select1 = false;
				select2 = false;
			}
		}
		
		if (dessiner) {	
			if(firstPt == null)
				firstPt = new Point(e.getPoint());
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		select1 = false;
		select2 = false;
		firstPt = null;
		
		if(dessiner) {
			traitTmp = null;
			newAdded = false;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			basculerFonct();
		}
	}
	
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
	
	public boolean traitSelect(Trait t, MouseEvent e) {		
		if(t.ptSegDist(e.getX(), e.getY()) <= TOLER)
			return true;
		else return false;
	}
}
