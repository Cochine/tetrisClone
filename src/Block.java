import java.awt.Color;
import java.awt.Point;

public class Block extends Point {
	private Color color;
	
	Block(int x, int y){
		super(x,y);
		this.color = Color.black;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
		
	}
	
}