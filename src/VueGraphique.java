import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueGraphique extends JPanel implements Observer {

	//public static final int TAILLE = 80;
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
			g.drawLine((int)trait.getPoint1().getX(), (int)trait.getPoint1().getY(),
					   (int)trait.getPoint2().getX(), (int)trait.getPoint2().getY()); 
		}
	}

}
