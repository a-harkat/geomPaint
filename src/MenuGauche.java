import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Classe qui represente le menu Gauche et qui contient
 * les boutons c'est un JPanel
 * @author Groupe 2
 * @version 1
 */
public class MenuGauche extends Menu {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur du JPanel menu Gauche
	 * @param fond couleur du background
	 */
	public MenuGauche(Color fond) {
		super(fond);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.generateBoutton();
	}
	
	/**
	 * Methode qui contient et permet de generer les
	 * boutons du menu Gauche
	 */
	private void generateBoutton() {
		JButton trait = new JButton("Trait");
		JButton cercle = new JButton("Cercle");
		JButton rectangle = new JButton("Rectangle");
		JButton triangle = new JButton("Triangle");
		JButton quelconque = new JButton("Quelconque");
		JButton edition = new JButton("Edition");
		
		this.ajouterBouttonM(trait);
		this.ajouterBouttonM(cercle);
		this.ajouterBouttonM(rectangle);
		this.ajouterBouttonM(triangle);
		this.ajouterBouttonM(quelconque);
		this.ajouterBouttonM(edition);
	}

}
