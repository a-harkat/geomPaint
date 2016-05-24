import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;

/**
 * Classe qui represente le menu Gauche et qui contient
 * les boutons c'est un JPanel
 * @author Groupe 2
 * @version 1
 */
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
		ImageIcon img_trait = new ImageIcon("src/images/trait.png", "trait");
		ImageIcon img_cercle = new ImageIcon("src/images/cercle.png", "cercle");
		ImageIcon img_rectangle = new ImageIcon("src/images/rectangle.png", "rectangle");
		ImageIcon img_triangle = new ImageIcon("src/images/triangle.png", "triangle");
		ImageIcon img_quelconque = new ImageIcon("src/images/quelconque.png", "quelconque");
		ImageIcon img_edition = new ImageIcon("src/images/edition.png", "edition");
		
		JButton couleur = new JButton("Couleur actuelle");
		couleur.setAlignmentX(Component.CENTER_ALIGNMENT);
		couleur.setMinimumSize(new Dimension(154,30));
		couleur.setMaximumSize(new Dimension(154,30));
		couleur.setPreferredSize(new Dimension(154,30));
		JButton trait = new JButton(img_trait);
		trait.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton cercle = new JButton(img_cercle);
		cercle.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton rectangle = new JButton(img_rectangle);
		rectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton triangle = new JButton(img_triangle);
		triangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton quelconque = new JButton(img_quelconque);
		quelconque.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton edition = new JButton(img_edition);
		edition.setAlignmentX(Component.CENTER_ALIGNMENT);
		JSlider nb_poly = this.generate_mg_jsli();
		
		this.ajouterBouttonM(couleur);
		this.ajouterBouttonM(edition);
		this.ajouterBouttonM(trait);
		this.ajouterBouttonM(rectangle);
		this.ajouterBouttonM(triangle);
		this.ajouterBouttonM(cercle);
		this.ajouterBouttonM(quelconque);
		this.ajouterBouttonM(nb_poly);
		
	}
	
	/**
	 *Methode qui genere un Jslider, creer pour allerger le code 
	 * @return b_type le bouton type
	 */
	private JSlider generate_mg_jsli() {
		JSlider b_type = new JSlider();
		b_type.setMaximum(8);
	    b_type.setMinimum(3);
	    b_type.setValue(1);
	    b_type.setPaintTicks(true);
	    b_type.setPaintLabels(true);
	    b_type.setMajorTickSpacing(1);
	    b_type.setFont(new Font("TimesRoman", Font.BOLD, 12));
	    b_type.setMinimumSize(new Dimension(154,40));
	    b_type.setMaximumSize(new Dimension(154,40));
	    b_type.setPreferredSize(new Dimension(154,40));
	    b_type.setBackground(new Color(96, 96, 96));
	    b_type.setAlignmentX(Component.CENTER_ALIGNMENT);
	    b_type.setToolTipText("nombre de points pour le polygone");
	    
	    return b_type;
	}
	
	/**
	 *Methode qui retourne le nombre de points du Slider
	 * @return nombre de points selectionner
	 */
	public int get_nb_points_poly(){
		return ((JSlider) this.getBouttonM(7)).getValue();
	}
	
	/**
	 *Methode pour definir la nouvelle couleur actuelle 
	 * @param new_col
	 */
	public void set_col_actuelle(Color new_col){
		this.getBouttonM(0).setBackground(new_col);
	}
	
	/**
	 *Methode pour savoir la couleur selectionnee
	 * @return couleur actuelle
	 */
	public Color get_col_actuelle(){
		return this.getBouttonM(0).getBackground();
	}
	
	/**
	 * Methode qui gere le changement des icones pour l'interface
	 * @param etat 
	 */
	public void changeicons(boolean etat) {
		if (!etat) {
			ImageIcon img_edition = new ImageIcon("src/images/edition.png", "edition");
			ImageIcon img_trait = new ImageIcon("src/images/trait.png", "trait");
			ImageIcon img_cercle = new ImageIcon("src/images/cercle.png", "cercle");
			ImageIcon img_rectangle = new ImageIcon("src/images/rectangle.png", "rectangle");
			ImageIcon img_triangle = new ImageIcon("src/images/triangle.png", "triangle");
			ImageIcon img_quelconque = new ImageIcon("src/images/quelconque.png", "quelconque");
			
			((AbstractButton) this.getBouttonM(1)).setIcon(img_edition);
			((AbstractButton) this.getBouttonM(2)).setIcon(img_trait);
			((AbstractButton) this.getBouttonM(3)).setIcon(img_rectangle);
			((AbstractButton) this.getBouttonM(4)).setIcon(img_triangle);
			((AbstractButton) this.getBouttonM(5)).setIcon(img_cercle);
			((AbstractButton) this.getBouttonM(6)).setIcon(img_quelconque);

		}
		else {
			ImageIcon img_edition = new ImageIcon("src/images/edition2.png", "edition");
			ImageIcon img_trait = new ImageIcon("src/images/trait2.png", "trait");
			ImageIcon img_cercle = new ImageIcon("src/images/cercle2.png", "cercle");
			ImageIcon img_rectangle = new ImageIcon("src/images/rectangle2.png", "rectangle");
			ImageIcon img_triangle = new ImageIcon("src/images/triangle2.png", "triangle");
			ImageIcon img_quelconque = new ImageIcon("src/images/quelconque2.png", "quelconque");
		
			((AbstractButton) this.getBouttonM(1)).setIcon(img_edition);
			((AbstractButton) this.getBouttonM(2)).setIcon(img_trait);
			((AbstractButton) this.getBouttonM(3)).setIcon(img_rectangle);
			((AbstractButton) this.getBouttonM(4)).setIcon(img_triangle);
			((AbstractButton) this.getBouttonM(5)).setIcon(img_cercle);
			((AbstractButton) this.getBouttonM(6)).setIcon(img_quelconque);
		}
	}
}