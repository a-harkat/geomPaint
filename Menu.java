import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 * Classe abstraite Menu qui sert de base a la creation
 * des differents menu du Jpanel
 * @author Groupe 2
 * @version 1
 */

public abstract class Menu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *Contient la liste des boutons du menu
	 */
	private ArrayList<Component> liste_b;	
	
	/**
	 *Constructeur du JPanel menu
	 *@param fond couleur du background
	 */
	public Menu(Color fond) {
		this.liste_b = new ArrayList<Component>();
		this.setBackground(fond);
	}
	
	/**
	 * Getteur de la liste des boutons du menu
	 * @return liste_b
	 */
	public ArrayList<Component> getListe_b() {
		return liste_b;
	}
	
	/**
	 * Setteur de la liste des boutons du menu
	 * @param new_liste_b la nouvelle liste
	 */
	public void setListe_bd(ArrayList<Component> new_liste_b) {
		this.liste_b = new_liste_b;
	}
	
	/**
	 * Getteur d'un boutton du menu
	 * @param numero Integer, numero du bouton voulu
	 * @return le boutton voulu
	 */
	public Component getBouttonM(int numero) {
		return this.liste_b.get(numero);
	}
	
	/**
	 * Setteur d'un boutton du menu
	 * @param numero le numero du boutton a remplacer
	 * @param new_boutton le boutton a ajouter
	 */
	public void setBouttonM(int numero, Component new_boutton) {
		this.liste_b.set(numero, new_boutton);
	}
	
	/**
	 * Methode qui permet de changer la couleur de fond du menu
	 * @param new_col la nouvelle couleur 
	 */
	public void setColFondM(Color new_col) {
		this.setBackground(new_col);
	}
	
	/**
	 * Methode qui permet d'ajouter un boutton au menu
	 * @param new_boutton le boutton a ajouter
	 */
	public void ajouterBouttonM(Component new_boutton) {
		this.liste_b.add(new_boutton);
		this.add(new_boutton);
	}
	
	/**
	 * Methode qui permet de supprimer un boutton au menu
	 * @param num_boutton le numero du boutton a supprimer
	 */
	public void supprimerBouttonM(int num_boutton) {
		this.remove(this.liste_b.get(num_boutton));
		this.liste_b.remove(num_boutton);
	}
	
	/**
	 * Methode qui permet d'ajouter au JPanel 
	 * la liste des boutons
	 */
	public void ajouterBouttonPanelM() {
		for (Component bout : this.liste_b) {
			this.add(bout);
		}
	}
}
