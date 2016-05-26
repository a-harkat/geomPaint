import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		
		
		int i = 0;
		ArrayList<String> tooltip = this.getListTool();
		for (String chemin : this.getListicone()){
			this.ajouterBouttonM(new JButton());
			((AbstractButton) this.getBouttonM(i)).setIcon(new ImageIcon(getClass().getResource(chemin)));
			((AbstractButton) this.getBouttonM(i)).setToolTipText(tooltip.get(i));
			i++;
		}

		this.ajouterBouttonM(new JCheckBox("Changer boutons"));
		
		
		for(Component bouton : this.getListe_b()){
			bouton.setMinimumSize(new Dimension(130,60));
			bouton.setMaximumSize(new Dimension(130,60));
			bouton.setPreferredSize(new Dimension(130,60));
		}
		((JCheckBox)this.getBouttonM(3)).setMaximumSize(new Dimension(130,30));
		
	}
	
	/**
	 * Methode pour favoriser la lecture claire du code qui
	 * contient les chemins des icones
	 * Attention a respecter le meme nombre de tooltip et 
	 * dans le meme ordre
	 * @return liste des chemin icones
	 */
	public ArrayList<String> getListicone(){
		ArrayList <String> li = new ArrayList<String>();
		li.add("images/copy.png");
		li.add("images/poubelle2.png");
		li.add("images/rouleau.png");
		
		return li;
	}
	
	/**
	 * Methode pour favoriser la lecture claire du code qui
	 * contient les ToolTip des boutons
	 * Attention a respecter le meme nombre de tooltip et 
	 * dans le meme ordre
	 * @return liste des chemin icones
	 */
	public ArrayList<String> getListTool(){
		ArrayList <String> li = new ArrayList<String>();
		li.add("Dupliquer");
		li.add("Supprimer toutes les figures");
		li.add("Changer la couleur de fond du menu");
		
		return li;
	}

}
