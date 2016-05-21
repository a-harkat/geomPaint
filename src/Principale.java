import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Classe Principale servant a afficher la fenetre en respectant le modele MVC
 * @author Groupe 2
 * @version 1
 */
public class Principale {
	
	/**
	 * Main du programme
	 * @param args les arguments pouvant etre recuperer 
	 */
	public static void main(String[] args) {
		Conteneur conteneur = new Conteneur();
		conteneur.setPreferredSize(new Dimension(500,500));
		JFrame fenetre=new JFrame("GeomPaint :D");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setContentPane(conteneur); 
		fenetre.pack();
		fenetre.setVisible(true);
	}
}
