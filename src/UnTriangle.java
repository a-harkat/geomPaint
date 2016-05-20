import java.awt.Point;


public class UnTriangle extends UnPolygone{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point p3 ;
	
	public UnTriangle(Point p1, Point p2, Point p3) {
		super(p1, p2);
		this.setP3(p3) ;
	}

	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
	}

}
