import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueGraphique extends JPanel implements Observer {

	private Trait trait;
	@Override
	public void update(Observable arg0, Object arg1) {

		trait = (Trait)arg0;
		repaint();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);

		if (trait != null) {
			g.drawLine(trait.getPoint1().x, trait.getPoint1().y,
					   trait.getPoint2().x, trait.getPoint2().y); 
		}
	}

}
