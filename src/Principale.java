import java.awt.Dimension;
import javax.swing.JFrame;

public class Principale {
	
	public static void main(String[] args) {
		
		ListTrait trs = new ListTrait();
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
