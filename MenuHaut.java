import java.awt.Color;
import java.awt.Font;
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
		JLabel statut_prog = new JLabel("Bienvenue !");
		
		statut_prog.setForeground(new Color(194, 247, 50));
		statut_prog.setFont(new Font("TimesRoman", Font.ITALIC, 16));
		this.ajouterBouttonM(statut_prog);
	}
	
	/**
	 * Methode qui change le texte a afficher
	 * @param new_texte nouveau texte
	 */
	public void changerTexteMH(String new_texte) {
		((JLabel)this.getBouttonM(0)).setText(new_texte);
	}
	
	/**
	 * Methode qui change la couleur du  texte a afficher
	 * @param new_color nouveau texte
	 */
	public void changerColorMH(Color new_color) {
		((JLabel)this.getBouttonM(0)).setForeground(new_color);
	}
}
