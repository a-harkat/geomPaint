import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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

	public Conteneur() {
		MenuGauche mg = new MenuGauche(Color.blue);
		ListFigures trs = new ListFigures();	
		VueGraphique vg = new VueGraphique();
		this.setLayout(new BorderLayout());		 
		this.add(mg,BorderLayout.WEST);
		this.add(vg,BorderLayout.CENTER);
		trs.addObserver(vg);
	
		
		/** mg.getButtonTrait().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	MouseListener[] mouseListeners = vg.getMouseListeners();
            	for (MouseListener mouseListener : mouseListeners) {
            		vg.removeMouseListener(mouseListener);
            	}
            	ControleurTrait cg = new ControleurTrait(trs);
    			vg.addMouseMotionListener(cg);
    			vg.addMouseListener(cg);         			            		
            }							
        });	
		
		mg.getButtonTriangle().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	MouseListener[] mouseListeners = vg.getMouseListeners();
            	for (MouseListener mouseListener : mouseListeners) {
            		vg.removeMouseListener(mouseListener);
            	}
            	ControleurTriangle cgt = new ControleurTriangle(trs);
    			vg.addMouseMotionListener(cgt);
    			vg.addMouseListener(cgt); 
            	       			            		
            }							
        });	*/
	}
}
