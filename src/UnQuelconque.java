import java.awt.Point;
import java.util.ArrayList;


public class UnQuelconque extends UnPolygone{
	ArrayList<Point> listPoints;
	int nbPoint ;
		
	public UnQuelconque(Point p1, Point p2, ArrayList<Point> lp) {
		super(p1, p2);
		this.listPoints = lp ;
	}
	


}
