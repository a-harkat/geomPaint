import java.awt.Color;
import javax.swing.JLabel;

/**
 * Classe qui represente le menu Haut et qui contient
 * les boutons c'est un JPanel
 * @author Groupe 2
 * @version 1
 */
public class MenuHaut extends Menu {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur du JPanel menu haut
	 * @param fond couleur du background
	 */
	public MenuHaut(Color fond) {
		super(fond);
		this.generateBoutton();
	}
	
	/**
	 * Methode qui contient et permet de generer les
	 * boutons du menu haut
	 */
	private void generateBoutton() {
		JLabel statut_prog = new JLabel("les messages a afficher");
		this.ajouterBouttonM(statut_prog);
	}

}
