import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Classe qui represente la liste des figures du painComponent, composer de traits
 * @author Groupe 2
 * @version 1
 */
public class ListFigures extends Observable{

	private ArrayList<FigureGeom> figures;
	
	/**
	 * Constructeur de ListeFigures
	 */
	public ListFigures() {
		figures = new ArrayList<>();
	}
	
	/**
	 * Methode pour ajouter une figure a la liste
	 * @param figure a ajouter
	 */
	public void addFigure(FigureGeom figure) {
		figures.add(figure);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode pour supprimer une figure de la liste 
	 * @param numfigure , numero de la figure a supprimer
	 */
	public void removeFigure(int numfigure) {
		figures.remove(numfigure);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode pour supprimer toutes les figures de la liste 
	 */
	public void removeAll() {
		figures.clear();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode pour supprimer les figures selectionées de la liste 
	 */
	public void removeSelected() {
		Iterator<FigureGeom> iterator = this.getFigures().iterator();
		while ( iterator.hasNext() ) {
			FigureGeom figure = iterator.next();
			if (figure.isSelectOn()) 
		        iterator.remove();
		    }
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Setter de figure, remplace une figure dans la liste
	 * @param numfigure numero de la figure a remplacer
	 * @param figure qui va remplacer l'ancienne
	 */
	public void setFigure(int numfigure, FigureGeom figure) {
		figures.set(numfigure, figure);
		setChanged();
		notifyObservers();
	}
	/**
	 * Methode qui va renvoyer la
	 * liste de figures
	 * @return figure arraylist
	 * figures
	 */
	public ArrayList<FigureGeom> getFigures() {
		return figures;
	}	
	/**
	 * Retourne l'index de la figure selectionee pour editer
	 * @param p le point ou s'applique l'edition
	 * @return index de la figure
	 */
	public int indexEditer (Point p){
		int index = -1 ;
		for (int i = 0; i < this.getFigures().size(); i++) {
			FigureGeom figure = this.getFigures().get(i);
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
		for (int i = 0; i < this.getFigures().size(); i++) {
			FigureGeom figure= this.getFigures().get(i);	
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
	 * méthode qui duplique une figure selectionnee
	 * @param j le nombre de pixels de décalage de la figure à dupliquee
	 */	
	public void dupliquer(int j) {
		for (int i = 0; i < this.getFigures().size(); i++) {	
			if (this.getFigures().get(i).isSelectOn()){
				FigureGeom figure = this.getFigures().get(i) ;			
				Point p1 = new Point ((int)(figure.getP1().getX()+j),(int)(figure.getP1().getY()+j));
				Point p2 = new Point ((int)(figure.getP2().getX()+j),(int)(figure.getP2().getY()+j));
				if (figure instanceof UnRectangle){					
					UnRectangle rt = new UnRectangle(p1,p2, figure.getBorder_color());
					this.addFigure(rt);
				}
				else if (figure instanceof UnCercle){		
					UnCercle cl = new UnCercle(p1,p2, figure.getBorder_color());
					this.addFigure(cl);
				}	
				else if (figure instanceof UnTrait){		
					UnTrait tr = new UnTrait(p1,p2, figure.getBorder_color());
					this.addFigure(tr);
				}
				else if (figure instanceof UnTriangle) {
					Point p3 = new Point ((int)(((UnTriangle)figure).getP3().getX()+j),(int)(((UnTriangle)figure).getP3().getY()+j));
					UnTriangle triangle = new UnTriangle(p1,p2,p3, figure.getBorder_color());
					this.addFigure(triangle);	
				}
				else if (figure instanceof UnQuelconque){
					Point [] ptPoly = ((UnQuelconque)figure).getListPoints();
						for (int t =0; t<ptPoly.length; t++ ){
							ptPoly[t] = new Point ((int)(ptPoly[t].getX()+j), (int)(ptPoly[t].getY()+j));
						}
					UnQuelconque ql = new UnQuelconque(p1,p2,ptPoly, figure.getBorder_color());
					this.addFigure(ql);			
				}
			}
		}
		}
		/**
		 * active ou desactive la selection de toutes les figures
		 * @param b l'etat du nouveau mode
		 */	
		public void toggleMode (boolean b){
			for (int i = 0; i < this.getFigures().size(); i++) {			
				this.getFigures().get(i).setSelectOn(b);
				this.setFigure(i,this.getFigures().get(i));					
			}
		}	
		/**
		 * Suprime le dernier element ajoute
		 */	
		public void deleteLast (ListFigures lsFiguresDelet){
			int taille = this.getFigures().size() ;	
			if (taille > 0) {
				lsFiguresDelet.addFigure(this.getFigures().get(taille-1) );		
				this.removeFigure (taille-1);				
			}
		}
		
		/**
		 * restore le dernier element efface
		 */	
		public void RestoreLast (ListFigures lsFiguresDelet){
			int taille = lsFiguresDelet.getFigures().size() ;	
			if (taille > 0) {
			this.addFigure(lsFiguresDelet.getFigures().get(taille - 1) );		
			lsFiguresDelet.removeFigure(taille - 1);		
			}
		}
	}

