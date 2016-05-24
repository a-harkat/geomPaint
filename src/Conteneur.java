import java.awt.BorderLayout;
import java.util.Observer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
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
	 * Liste des figures
	 */
	private ListFigures listFig;
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
		this.setLayout(new BorderLayout());
		
		MenuGauche mg = new MenuGauche(Color.black);
		MenuBas mb = new MenuBas(Color.black);
		MenuDroit md = new MenuDroit(Color.black);
		MenuHaut mh = new MenuHaut(Color.black);
		this.listFig = new ListFigures();	
		VueGraphique vg = new VueGraphique();
		
		this.add(mg,BorderLayout.WEST);
		this.add(mb,BorderLayout.SOUTH);
		this.add(md,BorderLayout.EAST);
		this.add(mh,BorderLayout.NORTH);
		this.add(vg,BorderLayout.CENTER);
		this.listFig.addObserver(vg);
		
		ControleurFigure cgt = new ControleurFigure(this.listFig);
		vg.addMouseMotionListener(cgt);
		vg.addMouseListener(cgt); 
		 
		((JButton)mg.getBouttonM(0)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				mh.changerTexteMH("choisissez votre couleur");
				Color couleur = JColorChooser.showDialog
						(null, "choisissez votre couleur", Color.WHITE);
						mg.set_col_actuelle(couleur);
						mh.changerTexteMH("couleur choisi : " + couleur);
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
				cgt.effacerPoints();
				cgt.toggleMode (false);
				cgt.setDessiner(true);
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
				cgt.toggleMode (false);
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
				cgt.setDessiner(true);
				cgt.toggleMode (false);
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
				cgt.setDessiner(true);
				cgt.toggleMode (false);
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
				cgt.setDessiner(true);
				cgt.toggleMode (false);
				cgt.setCercleOn(false);
				cgt.setRectangleOn(false);
				cgt.setTriangleOn(false);
				cgt.setTraitOn(false); 
				cgt.setPolygoneOn(true);
				int nb = mg.get_nb_points_poly();
				mh.changerTexteMH("Tracez un polygone : nombre de points choisis : " + nb);
			}				
		});
		
		((JButton)mb.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cgt.deleteLast ();
				mh.changerTexteMH("Figure effacee");
			}				
		});
	}
	
	/**
	 * M�thode qui va permettre
	 * de lier une vue textuelle � notre
	 * liste de figures
	 * @param o Observer Vue �
	 * associer
	 */
	public void ajoutObserver (Observer o) {
		this.listFig.addObserver(o);
	}
}
