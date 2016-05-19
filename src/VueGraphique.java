import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueGraphique extends JPanel implements Observer {
	
	ListTrait lsTraits;
	
	public VueGraphique() {
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		lsTraits = (ListTrait)arg0;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		
		if(lsTraits != null)
		for (int i = 0; i < lsTraits.traits.size(); i++) {
			Trait tr = lsTraits.traits.get(i);
			
			if (tr != null) {
				g.drawLine((int)tr.getP1().getX(), (int)tr.getP1().getY(),
						   (int)tr.getP2().getX(), (int)tr.getP2().getY()); 
			}
		}
	}
}
