import java.awt.Color;
import java.awt.Component;
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
		
		JButton dup = new JButton("Duplication");
		JButton clear = new JButton("clear all");
		JCheckBox choix_type_bouton = new JCheckBox("changer boutons");
		
		this.ajouterBouttonM(dup);
		this.ajouterBouttonM(clear);
		this.ajouterBouttonM(choix_type_bouton);
		
		for(Component bouton : this.getListe_b()){
			bouton.setMinimumSize(new Dimension(140,30));
			bouton.setMaximumSize(new Dimension(140,30));
			bouton.setPreferredSize(new Dimension(140,30));
		}
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
