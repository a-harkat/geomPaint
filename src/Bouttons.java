
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Bouttons extends JPanel {
   
	private static final long serialVersionUID = 187878L;
	private JButton buttonTrait, buttonTriangle ;
	private boolean trait ;
	private boolean triangle;

	public Bouttons () {
		super();
		setButtonTrait(new JButton("Trait"));
		setButtonTriangle(new JButton("Triangle"));
		Box box = Box.createVerticalBox();    
		box.add(getButtonTrait());
		box.add(Box.createVerticalStrut(20));
		box.add(getButtonTriangle());
		box.add(Box.createVerticalStrut(20));
		add(box);
	}

	public boolean isTrait() {
		return trait;
	}

	public void setTrait(boolean trait) {
		this.trait = trait;
	}

	public boolean isTriangle() {
		return triangle;
	}

	public void setTriangle(boolean triangle) {
		this.triangle = triangle;
	}

	public JButton getButtonTrait() {
		return buttonTrait;
	}

	public void setButtonTrait(JButton buttonTrait) {
		this.buttonTrait = buttonTrait;
	}

	public JButton getButtonTriangle() {
		return buttonTriangle;
	}

	public void setButtonTriangle(JButton buttonTriangle) {
		this.buttonTriangle = buttonTriangle;
	}
		
	
	
}



