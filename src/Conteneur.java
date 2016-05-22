import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
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
	 * Constructeur du Conteneur, il se deroule ainsi
	 * On definit le layout du conteneur
	 * On creer les differents Menus 
	 * On creer les Vues et les modeles
	 * On ajoute les Vues et Menu
	 * On lie les Modeles et les Vues (observers)
	 * On ajoute les listeneurs aux boutons
	 */
	public Conteneur() {
		this.setLayout(new BorderLayout());
		
		MenuGauche mg = new MenuGauche(Color.black);
		MenuBas mb = new MenuBas(Color.black);
		MenuDroit md = new MenuDroit(Color.black);
		MenuHaut mh = new MenuHaut(Color.black);
		ListFigures trs = new ListFigures();	
		VueGraphique vg = new VueGraphique();
		
		this.add(mg,BorderLayout.WEST);
		this.add(mb,BorderLayout.SOUTH);
		this.add(md,BorderLayout.EAST);
		this.add(mh,BorderLayout.NORTH);
		this.add(vg,BorderLayout.CENTER);
		trs.addObserver(vg);
		 
	
		((JButton)mg.getBouttonM(0)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MouseListener[] mouseListeners = vg.getMouseListeners();
				for (MouseListener mouseListener : mouseListeners) {
					vg.removeMouseListener(mouseListener);
				}
				ControleurTrait ctr = new ControleurTrait(trs);
				vg.addMouseMotionListener(ctr);
				vg.addMouseListener(ctr);
			}							
		});
		
		((JButton)mg.getBouttonM(1)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MouseListener[] mouseListeners = vg.getMouseListeners();
				for (MouseListener mouseListener : mouseListeners) {
					vg.removeMouseListener(mouseListener);
				}
				ControleurCercle cg = new ControleurCercle(trs);
				vg.addMouseMotionListener(cg);
				vg.addMouseListener(cg);
			}							
		});
		
		((JButton)mg.getBouttonM(2)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MouseListener[] mouseListeners = vg.getMouseListeners();
				for (MouseListener mouseListener : mouseListeners) {
					vg.removeMouseListener(mouseListener);
				}
				ControleurRectangle cgt = new ControleurRectangle(trs);
				vg.addMouseMotionListener(cgt);
				vg.addMouseListener(cgt); 
			}							
		});	
		
		((JButton)mg.getBouttonM(3)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MouseListener[] mouseListeners = vg.getMouseListeners();
				for (MouseListener mouseListener : mouseListeners) {
					vg.removeMouseListener(mouseListener);
				}
				ControleurTriangle cgt = new ControleurTriangle(trs);
				vg.addMouseMotionListener(cgt);
				vg.addMouseListener(cgt); 
			}							
		});	
		
		((JButton)mg.getBouttonM(4)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}				
		});
		
		((JButton)mg.getBouttonM(5)).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MouseListener[] mouseListeners = vg.getMouseListeners();
				for (MouseListener mouseListener : mouseListeners) {
					vg.removeMouseListener(mouseListener);
				}
				ControleurEdition ced = new ControleurEdition(trs);
				vg.addMouseMotionListener(ced);
				vg.addMouseListener(ced);		            		
			}							
		});
	}
}
