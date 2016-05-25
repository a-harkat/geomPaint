import java.awt.Point;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import java.util.ArrayList;
/**
 * Classe qui sert de controleur graphique pour les rectangle
 * @author Groupe 2
 * @version 1.2
 */
public class ControleurFigure extends MouseInputAdapter {
	boolean edition, deplacement ;
	private boolean dessiner;
	private boolean rectangleOn ;
	private boolean cercleOn;
	private boolean triangleOn;
	private boolean traitOn;
	private boolean polygoneOn;
	private boolean newfigure;
	public static boolean potPeinture;
	int index ;
	private int nbPointPolygone ;
	private Color border_color;
	private Color backgroundFigColor;
	Point p1, p2, p3, p4, p5, p6, p7, p8, pointEditer;	
	FigureGeom figure ;
	ListFigures lsFigures;
	
	/**
	 * Constructeur de ControleurFigure
	 */
	public ControleurFigure() {
		lsFigures = new ListFigures() ;	
		setDessiner(false);
	}
	
	/**
	 * Listener souris Dragged
	 */
	public void mouseDragged(MouseEvent e){	
		if (!isDessiner() && lsFigures.getFigures().size() != 0){
			if (edition ) {
				editerFigure(e.getPoint());
			}			
			else if (deplacement) {								
				deplacerFigure(e.getPoint());
			}
			pointEditer = e.getPoint();
			if (! figure.isSelectOn())  figure.setSelectOn(true);
		}
		else if (isDessiner())  {
			if (p1 != null && p2 == null){
				if (isRectangleOn()){
					UnRectangle rt = new UnRectangle(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(rt);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, rt);
				}
				else if (isCercleOn()){
					UnCercle cl = new UnCercle(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(cl);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, cl);
				}
				else if (isTraitOn()){
					UnTrait tr = new UnTrait(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(tr);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, tr);
				}
				else if (isTriangleOn()){
					UnTrait tr = new UnTrait(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(tr);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, tr);
				}
			}
			else if ((p1 != null && p2 != null) && p3 == null){		
				if (isTriangleOn()) {
					UnTriangle triangle = new UnTriangle(p1,p2,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.removeFigure(lsFigures.getFigures().size()-1);
						lsFigures.addFigure(triangle);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, triangle);
				}
			}
		}
	}
	
	
	public void mouseMoved(MouseEvent e) {
		if (isDessiner())  {
			if (p1 != null && p2 == null){
				if (isRectangleOn()){
					UnRectangle rt = new UnRectangle(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(rt);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, rt);
				}
				else if (isCercleOn()){
					UnCercle cl = new UnCercle(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(cl);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, cl);
				}
				else if (isTraitOn()){
					UnTrait tr = new UnTrait(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(tr);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, tr);
				}
				else if (isTriangleOn()){
					UnTrait tr = new UnTrait(p1,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.addFigure(tr);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, tr);
				}
			}
			else if (p1 != null && p2 != null && p3 == null){		
				if (isTriangleOn()) {
					UnTriangle triangle = new UnTriangle(p1,p2,e.getPoint(), border_color);
					if(!newfigure) {
						lsFigures.removeFigure(lsFigures.getFigures().size()-1);
						lsFigures.addFigure(triangle);
						newfigure = true;
					}
					lsFigures.setFigure(lsFigures.getFigures().size()-1, triangle);
				}
			}
		}
	}

	/**
	 * Listener souris Pressed
	 */
	public void mousePressed(MouseEvent e){
		newfigure = false;
		if (isDessiner()) {			
			if (p1 == null) 
				p1 = new Point (e.getPoint());
			else if (p2 == null){ 
				p2 = new Point (e.getPoint());	
				ajouterFig2point();
			}
			else if (p3 == null){				
				p3 = new Point (e.getPoint()) ;			
				if (isTriangleOn()) {
					UnTriangle triangle = new UnTriangle(p1,p2,p3, border_color);
					lsFigures.setFigure(lsFigures.getFigures().size()-1, triangle);	
					effacerPoints();
				}
			}
			else if (polygoneOn){				
				ajouterQuelconque(e.getPoint());				
			}
		} 
		else if (!isDessiner() &&
				lsFigures.getFigures().size() != 0 && !potPeinture){
			if (indexEditer(e.getPoint()) != -1 ){
				edition = true ;
				index = indexEditer(e.getPoint());
			}		
			else if (indexDeplacer(e.getPoint()) != -1){
				deplacement = true ;			
				index = indexDeplacer(e.getPoint());
			}
			pointEditer = e.getPoint() ;
			figure = lsFigures.getFigures().get(index);
		}
		/**
		 * Mode pot de peinture
		 */
		else if (!isDessiner() &&
				lsFigures.getFigures().size() != 0 && potPeinture) {
			if (backgroundFigColor == null) {
				backgroundFigColor = border_color;
			}
			int abscisse = e.getX();
			int ordonnee = e.getY();
			Point cible = new Point(abscisse, ordonnee);
			ArrayList<FigureGeom> list = lsFigures.getFigures();
			for (FigureGeom fg : list) {
				if (fg instanceof UnTrait) {
					UnTrait trait = (UnTrait) fg;
					if (trait.insideTrait(cible)) {
						if (!trait.getFigureFilled()) {
							trait.setFigureFilled(true);
							trait.setFull_color(backgroundFigColor);
							trait.setBorder_color(backgroundFigColor);
						} else {
							trait.setFigureFilled(false);
						}
					}
				} else if (fg instanceof UnCercle) {
					UnCercle cercle = (UnCercle) fg;
					if (cercle.insideCercle(cible)) {
						if (!cercle.getFigureFilled()) {
							cercle.setFigureFilled(true);
							cercle.setFull_color(backgroundFigColor);
							cercle.setBorder_color(backgroundFigColor);
						} else {
							cercle.setFigureFilled(false);
						}
					}
				} else if (fg instanceof UnTriangle) {
					UnTriangle triangle = (UnTriangle) fg;
					if (triangle.isInsideTriangle(cible)) {
						if (!triangle.getFigureFilled()) {
							triangle.setFigureFilled(true);
							triangle.setFull_color(backgroundFigColor);
							triangle.setBorder_color(backgroundFigColor);
						} else {
							triangle.setFigureFilled(false);
						}
					}
				} else if (fg instanceof UnRectangle) {
					UnRectangle rectangle = (UnRectangle) fg;
					if (rectangle.insideRectangle(cible)) {
						if (!rectangle.getFigureFilled()) {
							rectangle.setFigureFilled(true);
							rectangle.setFull_color(backgroundFigColor);
							rectangle.setBorder_color(backgroundFigColor);
						} else {
							rectangle.setFigureFilled(false);
						}
					}
				} else if (fg instanceof UnQuelconque) {
					UnQuelconque quelconque = (UnQuelconque) fg;
					if (quelconque.insidePolygone(cible)) {
						if (!quelconque.getFigureFilled()) {
							quelconque.setFigureFilled(true);
							quelconque.setFull_color(backgroundFigColor);
							quelconque.setBorder_color(backgroundFigColor);
						} else {
							quelconque.setFigureFilled(false);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Listener souris Released
	 */
	public void mouseReleased(MouseEvent e){
		edition = false ;
		deplacement = false;
		pointEditer = null;	
	}
	
	/**
	 * Listener souris Clicked
	 */
	public void mouseClicked(MouseEvent e) {
		if ((!isDessiner()) && (indexDeplacer(e.getPoint()) != -1)){			
			index = indexDeplacer(e.getPoint());
			figure = lsFigures.getFigures().get(index);		
			if (figure.isSelectOn())
					figure.setSelectOn(false);				
			else figure.setSelectOn(true);
			lsFigures.setFigure(index, figure) ;				
		}
	}
	
	/**
	 * methode qui deplace n'importe quelle figure par le mousseDragegd
	 */
	private void deplacerFigure(Point p) {
		if (figure instanceof UnRectangle) {
			UnRectangle rectangle = (UnRectangle) lsFigures.getFigures().get(index);
			figure.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}
		if (figure instanceof UnTrait) {
			UnTrait trait = (UnTrait) lsFigures.getFigures().get(index);
			trait.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, trait);
		}
		if (figure instanceof UnCercle) {
			UnCercle rectangle = (UnCercle) lsFigures.getFigures().get(index);
			figure.deplacerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}				
		if (figure instanceof UnTriangle) {	
			UnTriangle triangle = (UnTriangle) lsFigures.getFigures().get(index);
			triangle.deplacerTriangle(pointEditer, p) ;
			lsFigures.setFigure(index, triangle);
		}
		if (figure instanceof UnQuelconque) {	
			UnQuelconque quelconque = (UnQuelconque) lsFigures.getFigures().get(index);
			quelconque.deplacerQuelconque(pointEditer,  p);
			lsFigures.setFigure(index, quelconque);
		}		
	}
	
	/**
	 * methode qui edite n'importe quelle figure par le mousseDragegd
	 */
	private void editerFigure(Point p) {
		if (figure instanceof UnRectangle) {
			UnRectangle rectangle = (UnRectangle) lsFigures.getFigures().get(index);
			rectangle.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, rectangle);
		}
		if (figure instanceof UnTrait) {
			UnTrait trait = (UnTrait) lsFigures.getFigures().get(index);
			trait.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, trait);
		}
		if (figure instanceof UnCercle) {
			UnCercle cercle = (UnCercle) lsFigures.getFigures().get(index);
			cercle.editerFigure2p(pointEditer, p) ;
			lsFigures.setFigure(index, cercle);
		}				
		if (figure instanceof UnTriangle) {	
			UnTriangle triangle = (UnTriangle) lsFigures.getFigures().get(index);
			triangle.editerTriangle(pointEditer, p) ;
			lsFigures.setFigure(index, triangle);
		}
		if (figure instanceof UnQuelconque) {	
			UnQuelconque quelconque = (UnQuelconque) lsFigures.getFigures().get(index);
			quelconque.editerQuelconque(pointEditer,  p);
			lsFigures.setFigure(index, quelconque);
		}		
	}
	
	/**
	 * Ajoute une figure a deux point dans la liste de figures
	 */		
	private void ajouterFig2point() {				
		if (isRectangleOn()){					
			UnRectangle rt = new UnRectangle(p1,p2, border_color);
			lsFigures.setFigure(lsFigures.getFigures().size()-1, rt);
			effacerPoints();
		}
		else if (isCercleOn()){		
			UnCercle cl = new UnCercle(p1,p2, border_color);
			lsFigures.setFigure(lsFigures.getFigures().size()-1, cl);
			effacerPoints();
		}	
		else if (isTraitOn()){		
			UnTrait tr = new UnTrait(p1,p2, border_color);
			lsFigures.setFigure(lsFigures.getFigures().size()-1, tr);
			effacerPoints();
		}			
	}
	
	/**
	 * Ajoute un quelconque dans la liste de figures
	 */	
	private void ajouterQuelconque(Point p) {
		UnQuelconque pl = null;
		if(p4 == null) {
			p4 = new Point (p) ;
			if(getNbPointPolygone() == 4) pl = new UnQuelconque(p1,p2, new Point[]{p3,p4}, border_color) ;
		}
		else if(p5 == null && getNbPointPolygone() > 4) {
			p5 = new Point (p) ;	
			if(getNbPointPolygone() == 5) pl = new UnQuelconque(p1,p2, new Point[]{p3,p4,p5}, border_color) ;
		}
		else if(p6 == null && getNbPointPolygone() > 5) {
			p6 = new Point (p) ;	
			if(getNbPointPolygone() == 6) pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6}, border_color) ;	
		}
		else if(p7 == null && getNbPointPolygone() > 6) {
			p7 = new Point (p) ;	
			if(getNbPointPolygone() == 7) pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6,p7}, border_color) ;
		}
		else if(p8 == null && getNbPointPolygone() > 7) {
			p8 = new Point (p) ;
			if(getNbPointPolygone() == 8) pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6,p7,p8}, border_color) ;
		}
		if (pl != null) {
			lsFigures.addFigure(pl);
			effacerPoints();
		}		
	}
	
	/**
	 * Retourne l'index de la figure selectionee pour editer
	 * @param p le point ou s'applique l'edition
	 * @return index de la figure
	 */
	public int indexEditer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			figure = lsFigures.getFigures().get(i);
			if ( figure instanceof UnRectangle || figure instanceof UnCercle || figure instanceof UnTrait)
				if (figure.tolerance(p))
					 index = i ;						
			if (figure instanceof UnTriangle)
				if (((UnTriangle)figure).toleranceTriangle(p))
					 index = i ;
			if (figure instanceof UnQuelconque)
				if (((UnQuelconque)figure).toleranceQuelconque(p))
					 index = i ;
		}
		return index ;
	}
	
	/**
	 * Retourne l'index de la figure selectionee pour deplacer
	 * @param p le point ou s'applique l'edition 
	 * @return index de la figure
	 */	
	public int indexDeplacer (Point p){
		int index = -1 ;
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {
			figure = lsFigures.getFigures().get(i);	
			if ( figure instanceof UnRectangle)
				if (((UnRectangle)figure).insideRectangle(p))
					index = i ;	
			if ( figure instanceof UnTriangle)
				if (((UnTriangle)figure).isInsideTriangle(p))
					index = i ;	
			if ( figure instanceof UnCercle)
				if (((UnCercle)figure).insideCercle(p))
					index = i ;	
			if ( figure instanceof UnTrait)
				if (((UnTrait)figure).insideTrait(p))
					index = i ;	
			if ( figure instanceof UnQuelconque)
				if (((UnQuelconque)figure).insidePolygone(p))
					index = i ;
		}
		return index ;
	}
	
	/**
	 * active ou desactive la selection de toutes les figures
	 * @param b l'etat du nouveau mode
	 */	
	public void toggleMode (boolean b){
		for (int i = 0; i < lsFigures.getFigures().size(); i++) {			
			lsFigures.getFigures().get(i).setSelectOn(b);
			lsFigures.setFigure(i,lsFigures.getFigures().get(i));					
		}
		effacerPoints();
	}
	
	/**
	 * Suprime le dernier element ajoute
	 */	
	public void deleteLast (){
		if (lsFigures.getFigures().size() > 0) 
			lsFigures.removeFigure (lsFigures.getFigures().size() - 1);		
		effacerPoints();
	}
	
	/**
	 * methode qui efface les point lors du clique sur un bouton
	 */
	public void effacerPoints() {
		if (p1 != null) p1 = null;
		if (p2 != null) p2 = null;
		if (p3 != null) p3 = null;
		if (p4 != null) p4 = null;
		if (p5 != null) p5 = null;
		if (p6 != null) p6 = null;
		if (p7 != null) p7 = null;
		if (p8 != null) p8 = null;
	}
	
	/**
	 *Supprime la figure selectionnee
	 */	
	public void deleteSelected (){
		lsFigures.removeSelected();
		effacerPoints();
	}
	
	/**
	 *Supprime toutes les figures
	 */	
	public void deleteAll (){			
		lsFigures.removeAll();	
		effacerPoints();
	}

	/**
	 * Methode qui va colorier
	 * avec le pot de peinture
	 * une figure
	 * @param mg MenuGauche Menu qui contient
	 * la couleur actuelle
	 */
	public void colorFigure (MenuGauche mg) {
		backgroundFigColor = mg.get_col_actuelle();
		
	}
	
	/**
	 * @return the rectangleOn
	 */
	public boolean isRectangleOn() {
		return rectangleOn;
	}

	/**
	 * @param rectangleOn the rectangleOn to set
	 */
	public void setRectangleOn(boolean rectangleOn) {
		this.rectangleOn = rectangleOn;
	}

	/**
	 * @return the traitOn
	 */
	public boolean isTraitOn() {
		return traitOn;
	}

	/**
	 * @param traitOn the traitOn to set
	 */
	public void setTraitOn(boolean traitOn) {
		this.traitOn = traitOn;
	}

	/**
	 * @return the triangleOn
	 */
	public boolean isTriangleOn() {
		return triangleOn;
	}

	/**
	 * @param triangleOn the triangleOn to set
	 */
	public void setTriangleOn(boolean triangleOn) {
		this.triangleOn = triangleOn;
	}
	
	/**
	 * @return the cercleOn
	 */
	public boolean isCercleOn() {
		return cercleOn;
	}
	
	/**
	 * @param cercleOn the cercleOn to set
	 */
	public void setCercleOn(boolean cercleOn) {
		this.cercleOn = cercleOn;
	}
	
	/**
	 * @return the dessiner
	 */
	public boolean isDessiner() {
		return dessiner;
	}
	
	/**
	 * @param dessiner the dessiner to set
	 */
	public void setDessiner(boolean dessiner) {
		this.dessiner = dessiner;
	}
	
	/**
	 * @return the polygoneOn
	 */
	public boolean isPolygoneOn() {
		return polygoneOn;
	}
	/**
	 * @param polygoneOn the polygoneOn to set
	 */
	
	public void setPolygoneOn(boolean polygoneOn) {
		this.polygoneOn = polygoneOn;
	}
	
	/**
	 * @return the nbPointPolygone
	 */
	public int getNbPointPolygone() {
		return nbPointPolygone;
	}
	
	/**
	 * @param nbPointPolygone the nbPointPolygone to set
	 */
	public void setNbPointPolygone(int nbPointPolygone) {
		this.nbPointPolygone = nbPointPolygone;
	}
	
	/**
	 * Setter pour la couleur courante
	 * @param border_color la couleur affectee
	 */
	public void setBorder_color(Color border_color) {
		this.border_color = border_color;
	}
	
	/**
	 * Methode qui va permettre de definir
	 * si on est en mode pot de peinture
	 * ou non
	 * @param bool Boolean true si on est
	 * en mode pot de peinture false sinon
	 */
	public static void setPotPeintureOn(boolean bool) {
		ControleurFigure.potPeinture = bool;
	}
	
	/**
	 * Methode qui va renvoyer la
	 * liste de figures
	 * @return ListFigures Liste des
	 * figures
	 */
	public ListFigures getListFigures() {
		return lsFigures;
	}
}