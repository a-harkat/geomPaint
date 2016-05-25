import java.awt.Color;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
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
		
		int i = 0;
		ArrayList<String> tooltip = this.getListTool();
		for (String chemin : this.getListicone()){
			this.ajouterBouttonM(new JButton());
			((AbstractButton) this.getBouttonM(i)).setIcon(new ImageIcon(chemin));
			((AbstractButton) this.getBouttonM(i)).setToolTipText(tooltip.get(i));
			i++;
		}
	}
	
	public ArrayList<String> getListicone(){
		ArrayList <String> li = new ArrayList<String>();
		li.add("src/images/potPaint.png");
		li.add("src/images/undo2.png");
		li.add("src/images/redo2.png");
		li.add("src/images/poubelle2.png");
		
		return li;
	}
	
	public ArrayList<String> getListTool(){
		ArrayList <String> li = new ArrayList<String>();
		li.add("colorier un objet");
		li.add("revenir en arriere");
		li.add("aller en avant ");
		li.add("suprimer un objet");
		
		return li;
	}

}
