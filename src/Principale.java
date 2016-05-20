import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Classe Principale servant a afficher la fenetre en respectant le modele MVC
 * @author Groupe 2
 * @version 1
 */
public class Principale {
	
	/**
	 * Main du programme
	 * @param args les arguments pouvant etre recuperer 
	 */
	public static void main(String[] args) {
		JPanel conteneur = new JPanel();		
		Bouttons bt = new Bouttons () ;
		ListFigures trs = new ListFigures();	
		VueGraphique vg = new VueGraphique();
		conteneur.setLayout(new BorderLayout());		 
		conteneur.add(bt,BorderLayout.WEST);
		conteneur.add(vg,BorderLayout.CENTER);
		trs.addObserver(vg);
	
		
		bt.getButtonTrait().addActionListener(new ActionListener(){
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
		
		bt.getButtonTriangle().addActionListener(new ActionListener(){
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

		conteneur.setPreferredSize(new Dimension(500,500));
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(conteneur); 
		f.pack();
		f.setVisible(true);
	}
}
