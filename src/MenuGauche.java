import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		ImageIcon img_trait = new ImageIcon("src/images/trait.png", "trait");
		ImageIcon img_cercle = new ImageIcon("src/images/cercle.png", "cercle");
		ImageIcon img_rectangle = new ImageIcon("src/images/rectangle.png", "rectangle");
		ImageIcon img_triangle = new ImageIcon("src/images/triangle.png", "triangle");
		ImageIcon img_quelconque = new ImageIcon("src/images/quelconque.png", "quelconque");
		ImageIcon img_edition = new ImageIcon("src/images/edition.png", "edition");
		
		
		JButton trait = new JButton(img_trait);
		JButton cercle = new JButton(img_cercle);
		JButton rectangle = new JButton(img_rectangle);
		JButton triangle = new JButton(img_triangle);
		JButton quelconque = new JButton(img_quelconque);
		JButton edition = new JButton(img_edition);
		
		this.ajouterBouttonM(trait);
		this.ajouterBouttonM(cercle);
		this.ajouterBouttonM(rectangle);
		this.ajouterBouttonM(triangle);
		this.ajouterBouttonM(quelconque);
		this.ajouterBouttonM(edition);
	}

}
