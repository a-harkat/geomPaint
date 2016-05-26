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
	private static boolean potPeinture;
	private static boolean select;
	int index ;
	private int nbPointPolygone ;
	private Color border_color;
	private Color backgroundFigColor;
	Point p1, p2, p3, p4, p5, p6, p7, p8, pointEditer;	
	FigureGeom figure ;
	ListFigures lsFigures;
	ListFigures lsFiguresDelet;
	
	/**
	 * Constructeur de ControleurFigure
	 */
	public ControleurFigure() {
		lsFigures = new ListFigures() ;	
		lsFiguresDelet = new ListFigures() ;	
		setDessiner(false);
	}
	
	/**
	 * Listener souris Dragged
	 */
	public void mouseDragged(MouseEvent e){	
		if (!isDessiner() && lsFigures.getFigures().size() != 0
				&& figure != null){
			if (edition ) {
				figure.editerFigure(e.getPoint(), pointEditer, lsFigures, index);
			}
			else if (deplacement) {
				figure.deplacerFigure(e.getPoint(), pointEditer, lsFigures, index);
			}
			pointEditer = e.getPoint();
			if (ControleurFigure.select) {
					figure.setSelectOn(true);
			}
				
				
		}
		else {
			gestionDessin(e);
		}
	}
	
	/**
	 * Listener souris Moved
	 * @param e MouseEvent Evenement
	 * de la souris
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
			gestionDessin(e);
	}

	/**
	 * Listener souris Pressed
	 * @param e MouseEvent Evenement
	 * de la souris
	 *
	 */
	@Override
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
			if (lsFigures.indexEditer(e.getPoint()) != -1 ){
				edition = true ;
				index = lsFigures.indexEditer(e.getPoint());
			}		
			else if (lsFigures.indexDeplacer(e.getPoint()) != -1){
				deplacement = true ;			
				index = lsFigures.indexDeplacer(e.getPoint());
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
						gestionCouleur(trait);
					}
				} else if (fg instanceof UnCercle) {
					UnCercle cercle = (UnCercle) fg;
					if (cercle.insideCercle(cible)) {
						gestionCouleur(cercle);
					}
				} else if (fg instanceof UnTriangle) {
					UnTriangle triangle = (UnTriangle) fg;
					if (triangle.isInsideTriangle(cible)) {
						gestionCouleur(triangle);
					}
				} else if (fg instanceof UnRectangle) {
					UnRectangle rectangle = (UnRectangle) fg;
					if (rectangle.insideRectangle(cible)) {
						gestionCouleur(rectangle);
					}
				} else if (fg instanceof UnQuelconque) {
					UnQuelconque quelconque = (UnQuelconque) fg;
					if (quelconque.insidePolygone(cible)) {
						gestionCouleur(quelconque);
					}
				}
			}
		}
	}
	
	/**
	 * Listener souris Released
	 * @param e MouseEvent Evenement
	 * de la souris
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		edition = false ;
		deplacement = false;
		pointEditer = null;	
	}
	
	/**
	 * Listener souris Clicked
	 * @param e MouseEvent Evenement
	 * de la souris
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((!isDessiner()) && (lsFigures.indexDeplacer(e.getPoint()) != -1)){
			index = lsFigures.indexDeplacer(e.getPoint());
			figure = lsFigures.getFigures().get(index);		
			if (ControleurFigure.select)
				if(!figure.isSelectOn())
					figure.setSelectOn(true);
				else
					figure.setSelectOn(false);
			else figure.setSelectOn(false);
			lsFigures.setFigure(index, figure) ;				
		}
	}
	
	
	
	/**
	 * Methode qui va gerer les couleurs
	 * des figures
	 * @param fg FigureGeom Figure dont on
	 * doit gerer la couleur
	 */
	public void gestionCouleur (FigureGeom fg) {
		if (!fg.getFigureFilled()) {
			fg.setFigureFilled(true);
			fg.setFull_color(backgroundFigColor);
			fg.setBorder_color(backgroundFigColor);
		} else {
			fg.setFigureFilled(false);
		}
		lsFigures.notif();
	}
	
	/**
	 * Methode qui va s occuper
	 * de la gestion du dessin
	 * des figures
	 * @param e MouseEvent
	 * Evenement de la souris
	 */
	public void gestionDessin (MouseEvent e) {
		if (isDessiner())  {
			if (p1 != null){
				if (p2 == null) {
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
				else if (p3 == null) {
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
			if(getNbPointPolygone() == 4) {
				pl = new UnQuelconque(p1,p2, new Point[]{p3,p4}, border_color);
			}
				
		}
		else if(p5 == null && getNbPointPolygone() > 4) {
			p5 = new Point (p) ;	
			if(getNbPointPolygone() == 5)
				pl = new UnQuelconque(p1,p2, new Point[]{p3,p4,p5}, border_color) ;
		}
		else if(p6 == null && getNbPointPolygone() > 5) {
			p6 = new Point (p) ;	
			if(getNbPointPolygone() == 6)
				pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6}, border_color) ;	
		}
		else if(p7 == null && getNbPointPolygone() > 6) {
			p7 = new Point (p) ;	
			if(getNbPointPolygone() == 7)
				pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6,p7}, border_color) ;
		}
		else if(p8 == null && getNbPointPolygone() > 7) {
			p8 = new Point (p) ;
			if(getNbPointPolygone() == 8)
				pl = new UnQuelconque(p1,p2, new Point[]{p3,p3,p4,p5,p6,p7,p8}, border_color) ;
		}
		if (pl != null) {
			lsFigures.addFigure(pl);
			effacerPoints();
		}		
	}
	
	
	
	
	/**
	 * methode qui efface les point lors du clique sur un bouton
	 */
	public void effacerPoints() {
		if (p1 != null) 
			p1 = null;
		if (p2 != null) 
			p2 = null;
		if (p3 != null) 
			p3 = null;
		if (p4 != null) 
			p4 = null;
		if (p5 != null) 
			p5 = null;
		if (p6 != null) 
			p6 = null;
		if (p7 != null) 
			p7 = null;
		if (p8 != null) 
			p8 = null;
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
	 * Methode qui va permettre
	 * de recuperer l etat du pot
	 * de peinture
	 * @return Boolean true si le
	 * pot de peinture est en utilisation
	 * ou false sinon
	 */
	public static boolean getPotPeinture() {
		return ControleurFigure.potPeinture;
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
	/**
	 * Methode qui va renvoyer la
	 * liste de figures supprimes
	 * @return ListFiguresDelet Liste des
	 * figures
	 */
	public ListFigures getLsFiguresDelet() {
		return lsFiguresDelet;
	}
	
	public static void setSelectionOn(boolean bool) {
		ControleurFigure.select = bool;
	}
	public static boolean getSelectionOn() {
		return ControleurFigure.select;
	}
}