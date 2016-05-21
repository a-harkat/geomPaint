import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

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
		JButton cercle = new JButton("Cercle");
		JButton carre = new JButton("Carre");
		JButton triangle = new JButton("Triangle");
		JButton quelconque = new JButton("Quelconque");
		
		this.ajouterBouttonM(cercle);
		this.ajouterBouttonM(carre);
		this.ajouterBouttonM(triangle);
		this.ajouterBouttonM(quelconque);
	}

}
