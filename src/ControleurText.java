import java.awt.Color;
import java.awt.Point;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Classe qui va permettre
 * de modifier les modèles
 * afin de tester le bon
 * fonctionnement de l'algorithme
 */

/**
 * @author Groupe 2
 * @version 1.2
 */
public class ControleurText {
	
	/**
	 * Scanner qui servira pour les diverses
	 * methodes static de la classe
	 */
	private Scanner sc;
	
	/**
	 * Liste qui contiendra les figures
	 * entrees par l utilisateur en
	 * console
	 */
	public ListFigures listFig;
	
	private final Color BORDER = Color.red;
	
	public ControleurText () {
		this.listFig = new ListFigures();
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * Methode qui va permettre de gerer
	 * les entrees de l utilisateur
	 * @param entree String Entree de
	 * l utilisateur
	 * @exception NumberFormatException
	 * En cas d entree non conforme d un
	 * utilisateur
	 */
	public void gestionInputUser(String entree) {
		int nbPoint;
		String choixNbPoint = "";
		ArrayList<Point> listPoint;
		if (entree.length() != 0) {
			switch (entree.toLowerCase()) {
			
			case "triangle":
				listPoint = creerPoints(3);
				creerTriangle(listPoint);
				break;
			case "cercle":
				listPoint = creerPoints(2);
				creerCercle(listPoint);
				break;
			case "quelconque":
				System.out.println("Vous devez à présent"
						+ " choisir le nombre de points");
				choixNbPoint = this.sc.nextLine();
				if (choixNbPoint.length() != 0) {
					try {
						nbPoint = Integer.parseInt(choixNbPoint);
						if (nbPoint >= 2) {
							listPoint = creerPoints(nbPoint);
							creerQuelconque(listPoint);
						} else {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException iae) {
						System.out.println("Le nombre de points spéficié"
								+ " n'est pas conforme");
					}
				}
				break;
			case "rectangle":
				listPoint = creerPoints(2);
				creerRectangle(listPoint);
				break;
			case "trait":
				listPoint = creerPoints(2);
				creerTrait(listPoint);
				break;
			default:
				System.out.println("La figure spécifiée ne peut être dessiner");
			}
		}
	}
	
	/**
	 * Methode qui va permettre
	 * de creer un trait
	 * @param listPoint ArrayList<Point>
	 * Liste de points pour creer une
	 * figure
	 * @exception IndexOutOfBoundsException
	 * Exception qui va detecter lorsque
	 * l on essaie d atteindre non existante
	 * d une liste ou d'un tableau
	 */
	public void creerTrait(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnTrait trait = new UnTrait(p1, p2, BORDER);
				this.listFig.addFigure(trait);
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println("Création du trait impossible");
			}
		}
	}
	
	/**
	 * Methode qui va permettre
	 * de creer un cercle
	 * @param listPoint ArrayList<Point>
	 * Liste de points pour creer une
	 * figure
	 * @exception IndexOutOfBoundsException
	 * Exception qui va detecter lorsque
	 * l on essaie d atteindre non existante
	 * d une liste ou d'un tableau
	 */
	public void creerCercle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnCercle cercle = new UnCercle(p1, p2, BORDER);
				listFig.addFigure(cercle);
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println("Création du cercle impossible");
			}
		}
	}
	
	/**
	 * Methode qui va permettre
	 * de creer un rectangle
	 * @param listPoint ArrayList<Point>
	 * Liste de points pour creer une
	 * figure
	 * @exception IndexOutOfBoundsException
	 * Exception qui va detecter lorsque
	 * l on essaie d atteindre non existante
	 * d une liste ou d'un tableau
	 */
	public void creerRectangle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnRectangle rectangle = new UnRectangle(p1, p2, BORDER);
				listFig.addFigure(rectangle);
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println("Création du rectangle impossible");
			}
		}
	}
	
	/**
	 * Methode qui va permettre
	 * de creer un triangle
	 * @param listPoint ArrayList<Point>
	 * Liste de points pour creer une
	 * figure
	 * @exception IndexOutOfBoundsException
	 * Exception qui va detecter lorsque
	 * l on essaie d atteindre non existante
	 * d une liste ou d'un tableau
	 */
	public void creerTriangle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				Point p3 = listPoint.get(2);
				UnTriangle triangle = new UnTriangle(p1, p2, p3, BORDER);
				listFig.addFigure(triangle);
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println("Création du triangle impossible");
			}
		}
	}
	
	/**
	 * Methode qui va permettre
	 * de creer une figure quelconque
	 * @param listPoint ArrayList<Point>
	 * Liste de points pour creer une
	 * figure
	 * @exception IndexOutOfBoundsException
	 * Exception qui va detecter lorsque
	 * l on essaie d atteindre non existante
	 * d une liste ou d un tableau
	 */
	public void creerQuelconque(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			/**
			 * Mettre un ArrayList
			 * dans UnQuelconque plutôt qu un tableau
			 */
			Point [] tab = new Point[listPoint.size() - 2];
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				listPoint.remove(p1);
				listPoint.remove(p2);
				if (listPoint.size() != 0) {
					int iteration = 0;
					for (Point p : listPoint) {
						tab[iteration] = p;
						iteration ++;
					}
				}
				UnQuelconque quelconque = new UnQuelconque(p1, p2, tab, BORDER);
				listFig.addFigure(quelconque);
			} catch (IndexOutOfBoundsException ioobe) {
				System.out.println("Création de la forme quelconque"
						+ " impossible");
			}
		}
	}
	
	/**
	 * Methode qui va permettre de
	 * creer les differents points
	 * necessaire a la figure voulue
	 * @param nbPoint Integer Nombre
	 * de points que l utilisateur
	 * doit creer
	 * @exception NumberFormatException
	 * En cas d entree non conforme d un
	 * utilisateur
	 * @return ArrayList<Point> Liste des
	 * points crees
	 */
	public ArrayList<Point> creerPoints (int nbPoint) {
		int x;
		int y;
		int iteration = 0;
		String coordonnee = "";
		boolean inputing = true;
		Point p;
		ArrayList<Point> listPoint = new ArrayList<Point>();
		/**
		 * Tant que les entrees sont
		 * conformes ou que l utilisateur
		 * n a pas fini de creer des points
		 */
		while (inputing && iteration < nbPoint) {
			System.out.println("Vous devez à présent"
					+ " entrer les coordonnées (x, y) de "
					+ (nbPoint - iteration) 
					+ " points");
			coordonnee = this.sc.nextLine();
			try {
				x = Integer.parseInt(coordonnee);
				coordonnee = this.sc.nextLine();
				y = Integer.parseInt(coordonnee);
				/**
				 * Construction des points
				 * de maniere successive
				 * puis construction du trait
				 * en cas de mauvaise entree
				 * on annule tout
				 */
				p = new Point(x, y);
				listPoint.add(p);
				iteration ++;
			} catch (NumberFormatException iae) {
				System.out.println("Les coordonnées du"
						+ " point ne sont pas conformes");
				inputing = false;
			}
		}
		return listPoint;
	}
	
	/**
	 * Methode qui va renvoyer
	 * la liste des figures
	 * @return ListFigures Liste
	 * des figures
	 */
	public ListFigures getListFigures () {
		return this.listFig;
	}
}
