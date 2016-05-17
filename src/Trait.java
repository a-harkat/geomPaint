import java.awt.Point;
import java.util.Observable;

public class Trait extends Observable{
	
	private Point point1;
	private Point point2;
	
	public Trait() {
		point1 = new Point(100, 100);
		point2 = new Point(400, 400);
	}
	
	public Point getPoint1() {
		return point1;
	}
	
	public Point getPoint2() {
		return point2;
	}
	
	public void setPosition(Point p1, Point p2) {
		this.point1 = p1;
		this.point2 = p2;
		
	    setChanged();
	    notifyObservers();
	}
}
