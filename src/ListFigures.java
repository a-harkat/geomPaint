import java.util.ArrayList;
import java.util.Observable;

/**
 * Classe qui represente la liste des figures du painComponent, composer de traits
 * @author Groupe 2
 * @version 1
 */
public class ListFigures extends Observable{
	
	/**
	 * nom bizarre
	 */
	ArrayList<FigureGeom> traits;
	
	/**
	 * Constructeur de Liste Figures
	 */
	public ListFigures() {
		traits = new ArrayList<>();
	}
	
	/**
	 * Methode pour ajouter une figure a la liste
	 * @param figure a ajouter
	 */
	public void addFigure(FigureGeom figure) {
		traits.add(figure);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Methode pour supprimer une figure de la liste 
	 * @param numfigure , numero de la figure a supprimer
	 */
	public void removeFigure(int numfigure) {
		traits.remove(numfigure);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Setter de figure, remplace une figure dans la liste
	 * @param numfigure, numero de la figure a remplacer
	 * @param figure qui va remplacer l'ancienne
	 */
	public void setFigure(int numfigure, FigureGeom figure) {
		traits.set(numfigure, figure);
		setChanged();
		notifyObservers();
	}
}
