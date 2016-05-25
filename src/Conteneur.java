import java.awt.BorderLayout;
import java.util.Observer;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 * Classe Conteneur, contient les differents JPanel ainsi que les 
 * le modele MVC et les listeneurs 
 * @author Groupe 2
 * @version 1
 */
public class Conteneur extends JPanel{

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des figures
	 */
	private ListFigures listFig;
	
	/**
	 * Couleur du menu
	 */
	private Color menu_color;
	
	/**
	 * Constructeur du Conteneur, il se deroule ainsi
	 * On definit le layout du conteneur
	 * On creer les differents Menus 
	 * On creer les Vues et les modeles
	 * On ajoute les Vues et Menu
	 * On lie les Modeles et les Vues (observers)
	 * On ajoute les listeneurs aux boutons dans l'ordre:
	 * Menu gauche, menu bas menu droite
	 */
	public Conteneur() {
		this.setMenu_color(new Color(127, 143, 166));
		this.setLayout(new BorderLayout());
		
		MenuGauche mg = new MenuGauche(this.menu_color);
		for(Component bouton : mg.getListe_b()){
			bouton.setBackground(this.menu_color);
			if (bouton instanceof JButton)
			((AbstractButton) bouton).setBorderPainted(false);
		}
		MenuBas mb = new MenuBas(this.menu_color);
		MenuDroit md = new MenuDroit(this.menu_color);
		MenuHaut mh = new MenuHaut(this.menu_color);
		this.listFig = new ListFigures();	
		VueGraphique vg = new VueGraphique();
		
		this.add(mg,BorderLayout.WEST);
		this.add(mb,BorderLayout.SOUTH);
		this.add(md,BorderLayout.EAST);
		this.add(mh,BorderLayout.NORTH);
		this.add(vg,BorderLayout.CENTER);
		this.listFig.addObserver(vg);
		ControleurText.listFig.addObserver(vg);
		
		ControleurFigure cgt = new ControleurFigure(this.listFig);
		vg.addMouseMotionListener(cgt);
		vg.addMouseListener(cgt);
		 
		((JButton)mg.getBouttonM(0)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				mh.changerTexteMH("choisissez votre couleur");
				Color couleur = JColorChooser.showDialog
						(null, "Choisissez votre couleur", menu_color);
						mg.set_col_actuelle(couleur);
						mh.changerTexteMH("couleur choisi : " + couleur);
						cgt.setBorder_color(mg.get_col_actuelle());
			}						
		});
		
		((JButton)mg.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				cgt.effacerPoints();
				cgt.setDessiner(false);	
				mh.changerTexteMH("Vous etes en mode selection");
			}							
		});
		
		((JButton)mg.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.toggleMode (false);
				cgt.setDessiner(true);
				cgt.effacerPoints();			
				cgt.setCercleOn(false);
				cgt.setRectangleOn(false);
				cgt.setTriangleOn(false);
				cgt.setTraitOn(true);
				cgt.setPolygoneOn(false);
				mh.changerTexteMH("Tracez un trait");
			}							
		});
		
		((JButton)mg.getBouttonM(3)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.toggleMode(false);
				cgt.setDessiner(true);
				cgt.effacerPoints();				
				cgt.setCercleOn(false);
				cgt.setRectangleOn(true);
				cgt.setTriangleOn(false);
				cgt.setTraitOn(false);
				cgt.setPolygoneOn(false);
				mh.changerTexteMH("Tracez un rectangle : 2 points");
			}							
		});	
		
		((JButton)mg.getBouttonM(4)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.toggleMode (false);
				cgt.setDessiner(true);
				cgt.effacerPoints();				
				cgt.setCercleOn(false);
				cgt.setRectangleOn(false);
				cgt.setTriangleOn(true);
				cgt.setTraitOn(false);
				cgt.setPolygoneOn(false);
				mh.changerTexteMH("Tracez un triangle : 3 points");
			}							
		});	
		
		((JButton)mg.getBouttonM(5)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				cgt.toggleMode (false);
				cgt.setDessiner(true);
				cgt.effacerPoints();				
				cgt.setCercleOn(true);
				cgt.setRectangleOn(false);
				cgt.setTriangleOn(false);
				cgt.setTraitOn(false);
				cgt.setPolygoneOn(false);
				mh.changerTexteMH("Tracez un cercle : 2 points");
			}							
		});
		
		((JButton)mg.getBouttonM(6)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.toggleMode (false);
				cgt.setDessiner(true);
				cgt.effacerPoints();				
				cgt.setCercleOn(false);
				cgt.setRectangleOn(false);
				cgt.setTriangleOn(false);
				cgt.setTraitOn(false); 
				cgt.setPolygoneOn(true);				
				int nb = mg.get_nb_points_poly();
				cgt.setNbPointPolygone(nb);
				mh.changerTexteMH("Tracez un polygone : nombre de points choisis : " + nb);
			}				
		});
		
		((JButton)mb.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.deleteLast ();
				mh.changerTexteMH("Derniere figure effacee");
			}				
		});
		((JButton)mb.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.deleteAll ();
				mh.changerTexteMH("Figure(s) effacee");
			}				
		});
		
		((JCheckBox)mh.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				boolean etat = ((AbstractButton) e.getSource()).getModel().isSelected();
				mg.changeicons(etat);
			}				
		});
	}
	/**
	 * Getteur de la couleur du menu
	 * @return couleur du menu
	 */
	public Color getMenu_color() {
		return this.menu_color;
	}
	
	/**
	 * Setteur de la couleur du menu
	 * @param new_menu_color
	 */
	public void setMenu_color(Color new_menu_color) {
		this.menu_color = new_menu_color;
	}

	/**
	 * Methode qui va permettre
	 * de lier une vue textuelle a notre
	 * liste de figures
	 * @param o Observer Vue a
	 * associer
	 */
	public void ajoutObserver (Observer o) {
		this.listFig.addObserver(o);
	}
}
