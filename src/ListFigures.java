import java.util.ArrayList;
import java.util.Observable;

public class ListFigures extends Observable{
	ArrayList<FigureGeom> traits;
	
	public ListFigures() {
		traits = new ArrayList<>();
	}
	
	public void addFigure(FigureGeom t) {
		traits.add(t);
	    setChanged();
	    notifyObservers();
	}
	
	public void removeFigure(int i) {
		traits.remove(i);
	    setChanged();
	    notifyObservers();
	}
	
	public void setFigure(int i, FigureGeom t) {
		traits.set(i, t);
	    setChanged();
	    notifyObservers();
	}
}
