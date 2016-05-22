import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Classe qui represente le menu Droit et qui contient
 * les boutons c'est un JPanel destine a recevoir les
 * differents calques des figures
 * @author Groupe 2
 * @version 1
 */
public class MenuDroit extends Menu {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur du JPanel menu Droit
	 * @param fond couleur du background
	 */
	public MenuDroit(Color fond) {
		super(fond);
		
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.generateBoutton();
	}
	
	/**
	 * Methode qui contient et permet de generer les
	 * boutons du menu Droit
	 */
	private void generateBoutton() {
		for (int i = 0; i < 10; i++) {
			this.ajouterBouttonM(new JButton("test" + i));
		}
	}

}
