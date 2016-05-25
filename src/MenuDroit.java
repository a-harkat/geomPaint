import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

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
		JCheckBox choix_type_bouton = new JCheckBox("changer boutons");
		JButton clear = new JButton("clear all");
		
		this.ajouterBouttonM(choix_type_bouton);
		this.ajouterBouttonM(clear);
		
		clear.setMinimumSize(new Dimension(140,30));
		clear.setMaximumSize(new Dimension(140,30));
		clear.setPreferredSize(new Dimension(140,30));
		choix_type_bouton.setMinimumSize(new Dimension(140,30));
		choix_type_bouton.setMaximumSize(new Dimension(140,30));
		choix_type_bouton.setPreferredSize(new Dimension(140,30));
	}
	
	/**
	 * Methode qui permet de tester les calques
	 * boutons du menu Droit
	 */
	private void test() {
		for (int i = 0; i < 10; i++) {
			this.ajouterBouttonM(new JButton("test" + i));
		}
	}

}
