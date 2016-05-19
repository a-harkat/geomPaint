import java.util.ArrayList;
import java.util.Observable;

public class ListTrait extends Observable{
	ArrayList<Trait> traits;
	
	public ListTrait() {
		traits = new ArrayList<>();
	}
	
	public void addTrait(Trait t) {
		traits.add(t);
	    setChanged();
	    notifyObservers();
	}
	
	public void removeTrait(int i) {
		traits.remove(i);
	    setChanged();
	    notifyObservers();
	}
	
	public void setTrait(int i, Trait t) {
		traits.set(i, t);
	    setChanged();
	    notifyObservers();
	}
}
