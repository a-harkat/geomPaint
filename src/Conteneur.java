import java.awt.BorderLayout;
import java.util.Observer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
	 * Couleur du menu
	 */
	private Color menu_color;

	/**
	 * Constructeur du Conteneur, il se deroule ainsi
	 * On definit le layout du conteneur
	 * On cree les differents Menus 
	 * On cree les Vues et les modeles
	 * On ajoute les Vues et Menu
	 * On lie les Modeles et les Vues (observers)
	 * On ajoute les listeneurs aux boutons dans l'ordre:
	 * Menu gauche, menu bas menu droite
	 */
	public Conteneur() {     

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
	 * @param lf ListFigures Liste de
	 * figures a associer a la vue
	 */
	public void ajoutObserver (Observer o, ListFigures lf) {
		lf.addObserver(o);
	}

	/**
	 * Methode qui va permettre
	 * de definir un controleur
	 * graphique pour le conteneur
	 * @param cf ControleurFigure
	 * Controleur graphique a definir
	 * pour le conteneur
	 * @param vg VueGraphique Vue
	 * a controler
	 */
	public void setControlGraphic (ControleurFigure cf, VueGraphique vg) {
		vg.addMouseMotionListener(cf);
		vg.addMouseListener(cf);
	}
	/**
	 * Methode qui va permettre de
	 * preparer le JPanel en fonction d'une vue
	 * graphique
	 * @param vg Vue graphique a preparer
	 * @param cf ControleurFigure Controleur
	 * qui va gerer les figures
	 */
	public void preparationPanel(VueGraphique vg, ControleurFigure cf) {
		this.setMenu_color(new Color(127, 143, 166));
		this.setLayout(new BorderLayout());

		MenuGauche mg = new MenuGauche(this.menu_color);
		MenuBas mb = new MenuBas(this.menu_color);
		MenuDroit md = new MenuDroit(this.menu_color);
		MenuHaut mh = new MenuHaut(this.menu_color);

		this.add(mg,BorderLayout.WEST);
		this.add(mb,BorderLayout.SOUTH);
		this.add(md,BorderLayout.EAST);
		this.add(mh,BorderLayout.NORTH);
		this.add(vg,BorderLayout.CENTER);
		preparationBouton(mg, md, mh, mb, cf);
	}

	/**
	 * Methode qui va preparer les
	 * differents menus et le controleur
	 * des figures
	 * @param mg MenuGauche
	 * @param md MenuDroit
	 * @param mh MenuHaut
	 * @param mb MenuBas
	 * @param cf ControleurFigure
	 * Controleur qui va gerer les
	 * figures
	 */
	public void preparationBouton(MenuGauche mg, MenuDroit md,
			MenuHaut mh, MenuBas mb, ControleurFigure cf) {
		((JButton)mg.getBouttonM(0)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				mh.changerTexteMH("Choisissez une couleur");
				Color couleur = JColorChooser.showDialog
						(null, "Choisissez la couleur souhaitee pour vos figures", mg.get_col_actuelle());
				if (! (couleur == null)){
					mg.set_col_actuelle(couleur);
					mh.changerTexteMH("couleur choisi : " + couleur);
					cf.setBorder_color(mg.get_col_actuelle());
					cf.colorFigure(mg);
				}
				else {
					mh.changerTexteMH("Couleur non changee");
				}
			}                       
		});

		((JButton)mg.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				cf.effacerPoints();
				cf.setDessiner(false);
				ControleurFigure.setPotPeintureOn(false);
				ControleurFigure.setSelectionOn(true);
				mh.changerTexteMH("Vous etes en mode selection");
			}                           
		});

		((JButton)mg.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configTraitPolygon(cf);
				cf.setTraitOn(true);

				mh.changerTexteMH("Tracez un trait");
			}							
		});

		((JButton)mg.getBouttonM(3)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configTraitPolygon(cf);
				cf.setRectangleOn(true);
				mh.changerTexteMH("Tracez un rectangle : 2 points");
			}							
		});	

		((JButton)mg.getBouttonM(4)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configTraitPolygon(cf);
				cf.setTriangleOn(true);
				mh.changerTexteMH("Tracez un triangle : 3 points");
			}							
		});	

		((JButton)mg.getBouttonM(5)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				configTraitPolygon(cf);		
				cf.setCercleOn(true);

				mh.changerTexteMH("Tracez un cercle : 2 points");
			}							
		});

		((JButton)mg.getBouttonM(6)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configTraitPolygon(cf);
				cf.setPolygoneOn(true);	
				int nb = mg.get_nb_points_poly();
				cf.setNbPointPolygone(nb);
				mh.changerTexteMH("Tracez un polygone : nombre de points choisis : " + nb);
			}				
		});

		((JButton)mb.getBouttonM(0)).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configTraitPolygon(cf);
				cf.setDessiner(false);
				ControleurFigure.setPotPeintureOn(true);
				cf.colorFigure(mg);
				mh.changerTexteMH("Colorier figure");
			}
		});
		((JButton)mb.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cf.getListFigures().deleteLast(cf.getLsFiguresDelet());
				cf.effacerPoints();	
				mh.changerTexteMH("Derniere figure effacee");
			}				
		});
		((JButton)mb.getBouttonM(3)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int reponse = question2();
				if (reponse ==0) {
					prepareSelection(cf);
					cf.getListFigures().removeSelected();
					cf.effacerPoints();	
					mh.changerTexteMH("Figure(s) effacee(s)");
				}
			}				
		});

		((JButton)mb.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cf.getListFigures().restoreLast(cf.getLsFiguresDelet());
				cf.effacerPoints();	
				mh.changerTexteMH("Figure(s) restore(s)");
			}				
		});

		((JButton)md.getBouttonM(0)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				prepareSelection(cf);
				cf.getListFigures().dupliquer(40);
				cf.effacerPoints();	
				mh.changerTexteMH("Dupliquer");
			}				
		});

		((JButton)md.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int reponse = question1();
				if (reponse ==0) {
					cf.getListFigures().removeAll();
					cf.effacerPoints();	
					mh.changerTexteMH("Suppression effectuee");
				}
			}				
		});
		
		((JButton)md.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				mh.changerTexteMH("Choisissez une couleur");
				Color couleur = JColorChooser.showDialog
						(null, "Choisissez la couleur souhaitee pour vos figures", mg.get_col_actuelle());
				if (! (couleur == null)){
					mg.setBackground(couleur);
					mb.setBackground(couleur);
					md.setBackground(couleur);
					mh.setBackground(couleur);
					mh.changerTexteMH("couleur choisi : " + couleur);
				}
				else {
					mh.changerTexteMH("Couleur non changee");
				}
			}                       
		});

		((JCheckBox)md.getBouttonM(3)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				boolean etat = ((AbstractButton) e.getSource()).getModel().isSelected();
				mg.changeicons(etat);
				mh.changerTexteMH("Menu change");
			}                
		});

		((JLabel)mh.getBouttonM(0)).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mh.changerTexteMH("choisissez votre couleur");
				Color couleur = JColorChooser.showDialog
						(null, "Quelle couleur voulez vous pour le texte ?", mg.get_col_actuelle());
				if (! (couleur == null) && ! (couleur == menu_color)){
					mh.changerColorMH(couleur);
					mh.changerTexteMH("couleur choisi : " + couleur);
				}
				else {
					mh.changerTexteMH("Couleur non changee");
				}
			}                     
		});
	}

	/**
	 * Methode qui demande si on est sur de vouloir
	 * tout supprimer
	 * @return int la reponse
	 */
	public int question1(){
		return  JOptionPane.showConfirmDialog(
				(JFrame) SwingUtilities.getWindowAncestor(this),
				"Cette action est irreversible. Etes vous sûr de vouloir tout supprimer ?",
				"",
				JOptionPane.YES_NO_OPTION);
	}

	/**
	 * Methode qui demande si on est sur de vouloir
	 * supprimer les objets selectionner
	 * @return int la reponse
	 */
	public int question2(){
		return  JOptionPane.showConfirmDialog(
				(JFrame) SwingUtilities.getWindowAncestor(this),
				"Cette action est irreversible. Etes vous sûr de supprimer les figures selectionees ?",
				"",
				JOptionPane.YES_NO_OPTION);
	}

	/**
	 * Methode qui va assouplir
	 * les configurations pour les
	 * traits et les polygones
	 * @param cf ControleurFigure
	 * Gestionnaire des figures
	 */
	public void configTraitPolygon (ControleurFigure cf) {
		cf.getListFigures().toggleMode(false);
		cf.setDessiner(true);
		cf.effacerPoints();				
		cf.setCercleOn(false);
		cf.setRectangleOn(false);
		cf.setTriangleOn(false);
		cf.setTraitOn(false);
		cf.setPolygoneOn(false);
		ControleurFigure.setSelectionOn(false);
	}

	/**
	 * Methode qui va assouplir
	 * les configurations pour les
	 * traits et les polygones
	 * @param cf ControleurFigure
	 * Gestionnaire des figures
	 */
	public void prepareSelection (ControleurFigure cf) {
		cf.setDessiner(false);				
		cf.setCercleOn(false);
		cf.setRectangleOn(false);
		cf.setTriangleOn(false);
		cf.setTraitOn(false);
		cf.setPolygoneOn(false);
		ControleurFigure.setSelectionOn(true);
		cf.effacerPoints();
		ControleurFigure.setPotPeintureOn(false);
	}
}