import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class ControleurGraph extends MouseInputAdapter{
	
	Trait modele;
	boolean select1;
	boolean select2;
	public static final int TOLER = 4;

	public ControleurGraph(Trait t) {
		modele = t;
		select1 = false;
		select2 = false;
	}

	public void mouseDragged(MouseEvent e){
		Point p = e.getPoint();
		
		if(select1)
			modele.setPosition(p, modele.getPoint2());
		
		if(select2)
			modele.setPosition(modele.getPoint1(), p);
	}
	
	public void mousePressed(MouseEvent e){
		
		Point p = e.getPoint();
		if(selectionP1(p, TOLER))
			select1 = true;
		else if(selectionP2(p, TOLER))
			select2 = true;
		else {
			select1 = false;
			select2 = false;
		}
	}
	
	public void mouseReleased(MouseEvent e){
		select1 = false;
		select2 = false;
	}
	
	public boolean selectionP1(Point p, int tolerance) {
		
		int distance = distance(modele.getPoint1(), p);
		if (distance < tolerance) return true;
		else return false;
	}
	
	public boolean selectionP2(Point p, int tolerance) {
		
		int distance = distance(modele.getPoint2(), p);
		if (distance < tolerance) return true;
		else return false;
	}
	
	public void setModele(Point p1, Point p2) {
		modele.setPosition(p1, p2);;
	}
	
	public int distance(Point p1, Point p2){
		return (int)(Math.sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + 
								((p1.y - p2.y)*(p1.y - p2.y)))); 
	}
}
