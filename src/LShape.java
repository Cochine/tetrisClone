import java.awt.Color;
import java.awt.Point;

public class LShape extends Piece{

	private static Point[] points = {};
	private static Point[] origin;
	
	
	public LShape() {
		super(Color.ORANGE, points, origin);
		Point[] points = {new Point(-1,0), new Point(0,0), new Point(1,0), new Point(1,-1)};
		Point[] origin = {new Point(-1,0), new Point(0,0), new Point(1,0), new Point(1,-1)};
		this.setPoints(points);
		this.setOrigin(origin);

	}
	
}
