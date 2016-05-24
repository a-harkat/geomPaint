import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Classe qui va permettre
 * de modifier les mod�les
 * afin de tester le bon
 * fonctionnement de l'algorithme
 */

/**
 * @author Groupe 2
 * @version 1.2
 */
public class ControleurText {
	
	/**
	 * M�thode principale qui va permettre
	 * de tester l'algorithme du projet
	 * @param args String [] Arguments non
	 * utilis�s
	 */
	public static void main (String [] args) {
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
	}
}
