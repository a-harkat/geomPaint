import java.awt.Dimension;
import javax.swing.JFrame;

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

		ListFigures trs = new ListFigures();
		VueGraphique vg = new VueGraphique();
		ControleurGraph cg = new ControleurGraph(trs);

		trs.addObserver(vg);
		vg.addMouseMotionListener(cg);
		vg.addMouseListener(cg);

		vg.setPreferredSize(new Dimension(500,500));
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(vg); 
		f.pack();
		f.setVisible(true);
	}
}
