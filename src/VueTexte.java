import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;
import java.awt.Point;
/**
 * Classe qui va permettre vérifier
 * le bon fonctionnement
 * algorithmique du projet
 */

/**
 * @author Groupe 2
 * @version 1.2
 */
public class VueTexte implements Observer {
	
	/**
	 * Constructeur permettant
	 * d instancier la classe VueTexte
	 */
	public VueTexte () {
		
	}
	
	/**
	 * Methode qui va permettre de mettre
	 * a jour la vue en fonction des
	 * modifications des modeles par
	 * le controleur
	 * @param o Observable Modele que l on observe
	 * @param arg Object Eventuel object a surveiller
	 * egalement
	 */
	public void update (Observable o, Object arg) {
		ListFigures lf = (ListFigures) o;
		gestionChangement(lf);
	}
	
	/**
	 * Methode qui va permettre de determiner
	 * la nature de l element observe
	 * @param list ListFigures Liste contenant
	 * les elements que l on observe
	 */
	public void gestionChangement(ListFigures list) {
		if (list != null) {
			ArrayList<FigureGeom> listFig = list.getFigures();
			for (FigureGeom fg : listFig) {
				if (fg instanceof UnCercle) {
					int rayon = (int) (Math.abs(fg.getP1().getX()
							- fg.getP2().getX()));
					System.out.println("Cercle de centre x = "
							+ (int) fg.getP1().getX()
							+ ", y = "
							+ (int) fg.getP1().getY()
							+ " et de rayon "
							+ rayon);
				} else if (fg instanceof UnTrait) {
					System.out.println("Trait x1 = "
							+ (int) fg.getP1().getX()
							+ ", x2 = "
							+ (int) fg.getP2().getX()
							+ ", y1 = "
							+ (int) fg.getP1().getY()
							+ ", y2 = "
							+ (int) fg.getP2().getY());
				} else if (fg instanceof UnRectangle) {
					System.out.println("Rectangle xPtHautGauche = "
							+ (int) fg.getP1().getX()
							+ ", xPtBasDroite = "
							+ (int) fg.getP2().getX()
							+ ", yPtHautGauche = "
							+ (int) fg.getP1().getY()
							+ ", yPtBasDroit = "
							+ (int) fg.getP2().getY());
				} else if (fg instanceof UnTriangle) {
					UnTriangle tr = (UnTriangle) fg;
					System.out.println("Triangle x1 = "
							+ (int) tr.getP1().getX()
							+ ", x2 = "
							+ (int) tr.getP2().getX()
							+ ", x3 = "
							+ (int) tr.getP3().getX()
							+ ", y1 = "
							+ (int) tr.getP1().getY()
							+ ", y2 = "
							+ (int) tr.getP2().getY()
							+ ", y3 = "
							+ (int) tr.getP3().getY());
				} else if (fg instanceof UnQuelconque) {
					UnQuelconque qc = (UnQuelconque) fg;
					System.out.print("Polygone : x1 = "
							+ (int) qc.getP1().getX()
							+ ", y1 = "
							+ (int) qc.getP1().getY()
							+ ", x2 = "
							+ (int) qc.getP2().getX()
							+ ", y2 = "
							+ (int) qc.getP2().getY());
					Point [] listPt = qc.getListPoints();
					if (listPt.length != 0) {
						/**
						 * Représente le ième point
						 */
						int iteration = 3;
						for (Point pt : listPt) {
							System.out.print(", x"
									+ iteration
									+ " = "
									+ pt.getX()
									+ ", y"
									+ iteration
									+ " = "
									+ pt.getY());
							iteration += 1;
						}
						System.out.println("\nLe polygone a "
								+ (iteration - 1)
								+ " points");
					} else {
						System.out.println("Vous avez sélectionné seulement "
								+ "2 côtés !");
					}
				}
			}
		} 
	}
}
