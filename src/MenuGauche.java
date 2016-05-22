import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Classe qui represente le menu Gauche et qui contient
 * les boutons c'est un JPanel
 * @author Groupe 2
 * @version 1
 */
public class MenuGauche extends Menu {

	/**
	 *Sert a la serialisation
	 */
	private static final long serialVersionUID = 1L;
	private JButton cercle;
	private JButton rectangle;
	private JButton triangle;
	private JButton quelconque;
	
	/**
	 * Constructeur du JPanel menu Gauche
	 * @param fond couleur du background
	 */
	public MenuGauche(Color fond) {
		super(fond);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.generateBoutton();
	}
	
	/**
	 * Methode qui contient et permet de generer les
	 * boutons du menu Gauche
	 */
	private void generateBoutton() {
		setCercle(new JButton("Cercle"));
		setRectangle(new JButton("Rectangle"));
		setTriangle(new JButton("Triangle"));
		setQuelconque(new JButton("Quelconque"));
		
		this.ajouterBouttonM(getCercle());
		this.ajouterBouttonM(getRectangle());
		this.ajouterBouttonM(getTriangle());
		this.ajouterBouttonM(getQuelconque());
	}

	public JButton getCercle() {
		return cercle;
	}

	public void setCercle(JButton cercle) {
		this.cercle = cercle;
	}

	public JButton getRectangle() {
		return rectangle;
	}

	public void setRectangle(JButton carre) {
		this.rectangle = carre;
	}

	public JButton getTriangle() {
		return triangle;
	}

	public void setTriangle(JButton triangle) {
		this.triangle = triangle;
	}

	public JButton getQuelconque() {
		return quelconque;
	}

	public void setQuelconque(JButton quelconque) {
		this.quelconque = quelconque;
	}

}
