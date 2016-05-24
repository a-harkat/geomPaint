import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

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
	private static Scanner sc;
	
	/**
	 * Liste qui contiendra les figures
	 * entrees par l utilisateur en
	 * console
	 */
	private static ListFigures listFig;
	
	/**
	 * Méthode principale qui va permettre
	 * de tester l'algorithme du projet
	 * @param args String [] Arguments non
	 * utilisés
	 */
	public static void main (String [] args) {
		ControleurText.sc = new Scanner (System.in);
		ControleurText.listFig = new ListFigures();
		VueTexte vt = new VueTexte();
		Conteneur conteneur = new Conteneur();
		conteneur.setPreferredSize(new Dimension(1500,800));
		conteneur.ajoutObserver(vt);
		JFrame fenetre=new JFrame("GeomPaint :D");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setContentPane(conteneur); 
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		String figure = "";
		while (true) {
			System.out.println("Entrez dans la console le type de figure souhaité"
					+ " parmi triangle, cercle, quelconque, rectangle, trait");
			figure = ControleurText.sc.nextLine();
			ControleurText.gestionInputUser(figure);
		}
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
	public static void gestionInputUser(String entree) {
		int nbPoint;
		String choixNbPoint = "";
		ArrayList<Point> listPoint;
		if (entree.length() != 0) {
			switch (entree.toLowerCase()) {
			
			case "triangle":
				listPoint = ControleurText.creerPoints(3);
				ControleurText.creerTriangle(listPoint);
				break;
			case "cercle":
				listPoint = ControleurText.creerPoints(2);
				ControleurText.creerCercle(listPoint);
				break;
			case "quelconque":
				System.out.println("Vous devez à présent"
						+ " choisir le nombre de points");
				choixNbPoint = ControleurText.sc.nextLine();
				if (choixNbPoint.length() != 0) {
					try {
						nbPoint = Integer.parseInt(choixNbPoint);
						if (nbPoint >= 2) {
							listPoint = ControleurText.creerPoints(nbPoint);
							ControleurText.creerQuelconque(listPoint);
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
				listPoint = ControleurText.creerPoints(2);
				ControleurText.creerRectangle(listPoint);
				break;
			case "trait":
				listPoint = ControleurText.creerPoints(2);
				ControleurText.creerTrait(listPoint);
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
	public static void creerTrait(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnTrait trait = new UnTrait(p1, p2);
				ControleurText.listFig.addFigure(trait);
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
	public static void creerCercle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnCercle cercle = new UnCercle(p1, p2);
				ControleurText.listFig.addFigure(cercle);
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
	public static void creerRectangle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				UnRectangle rectangle = new UnRectangle(p1, p2);
				ControleurText.listFig.addFigure(rectangle);
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
	public static void creerTriangle(ArrayList<Point> listPoint) {
		if (listPoint.size() != 0) {
			try {
				Point p1 = listPoint.get(0);
				Point p2 = listPoint.get(1);
				Point p3 = listPoint.get(2);
				UnTriangle triangle = new UnTriangle(p1, p2, p3);
				ControleurText.listFig.addFigure(triangle);
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
	public static void creerQuelconque(ArrayList<Point> listPoint) {
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
				UnQuelconque quelconque = new UnQuelconque(p1, p2, tab);
				ControleurText.listFig.addFigure(quelconque);
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
	public static ArrayList<Point> creerPoints (int nbPoint) {
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
			coordonnee = ControleurText.sc.nextLine();
			try {
				x = Integer.parseInt(coordonnee);
				coordonnee = ControleurText.sc.nextLine();
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
}
