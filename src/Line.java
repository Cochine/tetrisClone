import java.awt.Color;
import java.awt.Point;

public class Line extends Piece {

	static Point[] points = {new Point(-1,0), new Point(0,0), new Point(1,0), new Point(2,0)};
	
	public Line() {
		super (Color.cyan, points);
		
	}
	
}
