import java.awt.Dimension;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Classe de test d algorithme
 */

/**
 * @author Groupe 2
 * @version 1.2
 */
public class TestAlgoPrincipal {

	/**
     * Attribut nous permettant d'utiliser un log du 
     * message d'erreur pour la Fenetre
     */
    private static final Logger LOGGER = 
        Logger.getLogger(TestAlgoPrincipal.class.getName());
    
	/**
	 * Methode principale qui va permettre
	 * de tester le fonctionnement de
	 * l algorithme en lui meme via console
	 * @param args String [] Arguments non
	 * necessaires ici
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {
			LOGGER.log(Level.INFO, "Erreur lors de la recuperation de l'UI", e);
		}
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		VueTexte vt = new VueTexte();
		VueGraphique vg = new VueGraphique();
		ControleurFigure cf = new ControleurFigure();
		ControleurText ct = new ControleurText();
		Conteneur conteneur = new Conteneur();
		conteneur.setPreferredSize(new Dimension(1500,800));
		conteneur.ajoutObserver(vt, ct.getListFigures());
		conteneur.ajoutObserver(vg, ct.getListFigures());
		conteneur.preparationPanel(vg, cf);
		JFrame fenetre=new JFrame("GeomPaint :D");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setContentPane(conteneur); 
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		String figure = "";
		while (true) {
			System.out.println("Entrez dans la console le type de figure souhaitée\n"
					+ "parmi triangle, cercle, quelconque, rectangle, trait");
			figure = sc.nextLine();
			ct.gestionInputUser(figure);
		}
	}

}
