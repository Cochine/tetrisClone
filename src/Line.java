import java.awt.Color;
import java.awt.Point;

public class Line extends Piece {

	private static Point[] points = {};
	private static Point[] origin;
	
	public Line() {
		super (Color.cyan, points, origin);
		Point[] points = {new Point(-1,0), new Point(0,0), new Point(1,0), new Point(2,0)};
		Point[] origin = {new Point(-1,0), new Point(0,0), new Point(1,0), new Point(2,0)};
		this.setPoints(points);
		this.setOrigin(origin);
	}
	
}
