import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Classe qui sert de vue Graphique
 * @author Groupe 2
 * @version 1
 */
public class VueGraphique extends JPanel implements Observer {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *Liste des figures
	 */
	private ListFigures listeFigures;
	public static final int VERTEX_SIZE = 6;

	/**
	 *Constructeur vide pour l'instant, sert a l'instanciation
	 */
	public VueGraphique() {
		setBackground(new Color(245,255,250));
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		this.listeFigures = (ListFigures)arg0;
		repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(this.listeFigures != null)
			for (int i = 0; i < this.listeFigures.getFigures().size(); i++) {
				FigureGeom tr = this.listeFigures.getFigures().get(i);
				if (!ControleurFigure.potPeinture) {
					g.setColor(tr.getFull_color());
					dessinerFigurePleine(g, tr);
				} else {
					g.setColor(tr.getBorder_color());
					dessinerFigureVide(g, tr);
				}
			}
	}
	
	public void drawVertex(Graphics g, FigureGeom fg) {		
		int vrtx = VERTEX_SIZE;		
		g.fillRect((int)fg.getP1().getX()-vrtx/2, (int)fg.getP1().getY()-vrtx/2, vrtx, vrtx);
		g.fillRect((int)fg.getP2().getX()-vrtx/2, (int)fg.getP2().getY()-vrtx/2, vrtx, vrtx);
		
		if (fg instanceof UnTriangle ){ 
			UnTriangle triangle = (UnTriangle) fg ;
			g.fillRect((int)triangle.getP3().getX()-vrtx/2, (int)triangle.getP3().getY()-vrtx/2, vrtx, vrtx);
		}
		if (fg instanceof UnQuelconque ){ 
			UnQuelconque quelconque = (UnQuelconque) fg ;
			for (int j = 0; j< quelconque.getListPoints().length;j++){
				g.fillRect((int)quelconque.getListPoints()[j].getX()-vrtx/2, (int)quelconque.getListPoints()[j].getY()-vrtx/2, vrtx, vrtx);
			}
		}
	}
	
	/**
	 * Methode qui va dessiner une figure
	 * pleine
	 * @param g Graphics Graphique devant
	 * contenir la figure
	 * @param fg FigureGeom Figure a
	 * dessiner
	 */
	public void dessinerFigurePleine (Graphics g, FigureGeom fg) {
		if (fg instanceof UnTrait){
			g.drawLine((int)fg.getP1().getX(), (int)fg.getP1().getY(),
					(int)fg.getP2().getX(), (int)fg.getP2().getY()); 
			
		}
		if (fg instanceof UnTriangle){
			UnTriangle triangle = (UnTriangle) fg;					
			int [] X = {(int)triangle.getP1().getX(), (int)triangle.getP2().getX(), (int)triangle.getP3().getX()};
			int [] Y = {(int)triangle.getP1().getY(), (int)triangle.getP2().getY(), (int)triangle.getP3().getY()};
			g.drawPolygon(X, Y, 3);	
		}
		if (fg instanceof UnCercle){
			UnCercle Cercle = (UnCercle) fg;
			int r = (int) (Cercle.distance(Cercle.getP1(),Cercle.getP2())) ;
			int x =(int) Cercle.getP1().getX();
			int y =(int) Cercle.getP1().getY();
			g.drawOval(x-r,y-r,r*2,r*2);
		}
		if (fg instanceof UnRectangle){
			UnRectangle rectangle = (UnRectangle) fg;
			int x = (int) rectangle.getP1().getX() ;
			int y = (int) rectangle.getP1().getY() ;
			int width = (int) Math.abs(rectangle.getP1().getX() - rectangle.getP2().getX());
			int height = (int) Math.abs(rectangle.getP1().getY() - rectangle.getP2().getY());
			if (y>rectangle.getP2().getY()) y = y - height;
			if (x>rectangle.getP2().getX())	x = x - width ;		
			g.drawRect(x, y, width, height );
		}
		if (fg instanceof UnQuelconque ){
			UnQuelconque quelconque = (UnQuelconque) fg;
			int taille = quelconque.getListPoints().length ;
			int [] X = new int [taille+2];
			X[0] = (int)quelconque.getP1().getX() ;
			X[1] = (int)quelconque.getP2().getX();
			int [] Y = new int [taille+2] ;
			Y[0] = (int)quelconque.getP1().getY() ; 
			Y[1] = (int)quelconque.getP2().getY();
			
			for (int j = 0; j< taille;j++){
				X[j+2] = (int) quelconque.getListPoints()[j].getX();
				Y[j+2] = (int) quelconque.getListPoints()[j].getY();
			}
			g.drawPolygon(X, Y, taille+2);	
		}
		if(fg.isSelectOn())
			drawVertex(g, fg);
	}
	
	/**
	 * Methode qui va dessiner une figure
	 * vide
	 * @param g Graphics Graphique devant
	 * contenir la figure
	 * @param fg FigureGeom Figure a
	 * dessiner
	 */
	public void dessinerFigureVide (Graphics g, FigureGeom fg) {
		if (fg instanceof UnTrait){
			g.drawLine((int)fg.getP1().getX(), (int)fg.getP1().getY(),
					(int)fg.getP2().getX(), (int)fg.getP2().getY()); 
			
		}
		if (fg instanceof UnTriangle){
			UnTriangle triangle = (UnTriangle) fg;					
			int [] X = {(int)triangle.getP1().getX(), (int)triangle.getP2().getX(), (int)triangle.getP3().getX()};
			int [] Y = {(int)triangle.getP1().getY(), (int)triangle.getP2().getY(), (int)triangle.getP3().getY()};
			g.fillPolygon(X, Y, 3);	
		}
		if (fg instanceof UnCercle){
			UnCercle Cercle = (UnCercle) fg;
			int r = (int) (Cercle.distance(Cercle.getP1(),Cercle.getP2())) ;
			int x =(int) Cercle.getP1().getX();
			int y =(int) Cercle.getP1().getY();
			g.fillOval(x-r,y-r,r*2,r*2);
		}
		if (fg instanceof UnRectangle){
			UnRectangle rectangle = (UnRectangle) fg;
			int x = (int) rectangle.getP1().getX() ;
			int y = (int) rectangle.getP1().getY() ;
			int width = (int) Math.abs(rectangle.getP1().getX() - rectangle.getP2().getX());
			int height = (int) Math.abs(rectangle.getP1().getY() - rectangle.getP2().getY());
			if (y>rectangle.getP2().getY()) y = y - height;
			if (x>rectangle.getP2().getX())	x = x - width ;		
			g.fillRect(x, y, width, height );
		}
		if (fg instanceof UnQuelconque ){
			UnQuelconque quelconque = (UnQuelconque) fg;
			int taille = quelconque.getListPoints().length ;
			int [] X = new int [taille+2];
			X[0] = (int)quelconque.getP1().getX() ;
			X[1] = (int)quelconque.getP2().getX();
			int [] Y = new int [taille+2] ;
			Y[0] = (int)quelconque.getP1().getY() ; 
			Y[1] = (int)quelconque.getP2().getY();
			
			for (int j = 0; j< taille;j++){
				X[j+2] = (int) quelconque.getListPoints()[j].getX();
				Y[j+2] = (int) quelconque.getListPoints()[j].getY();
			}
			g.fillPolygon(X, Y, taille+2);	
		}
		if(fg.isSelectOn())
			drawVertex(g, fg);
	}
}
