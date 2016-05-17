import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principale {

	public static void main(String[] args) {
		
		Trait t = new Trait();
		VueGraphique vg = new VueGraphique();
		ControleurGraph cg = new ControleurGraph(t);
		
		t.addObserver(vg);
		
		Point p1 = new Point(100, 100);
		Point p2 = new Point(400, 400);
		
		t.setPosition(p1, p2);
		
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
