import java.awt.Color;

import javax.swing.JButton;

/**
 * Classe qui represente le menu bas et qui contient
 * les boutons c'est un JPanel
 * @author Groupe 2
 * @version 1
 */
public class MenuBas extends Menu {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur du JPanel menu bas
	 * @param fond couleur du background
	 */
	public MenuBas(Color fond) {
		super(fond);
		this.generateBoutton();
	}
	
	/**
	 * Methode qui contient et permet de generer les
	 * boutons du menu bas
	 */
	private void generateBoutton() {
		JButton undo = new JButton("UNDO");
		JButton dupliquer = new JButton("Dup");
		
		undo.setToolTipText("revenir en arriere");
		dupliquer.setToolTipText("dupliquer un objet");
		
		this.ajouterBouttonM(undo);
		this.ajouterBouttonM(dupliquer);
	}

}
