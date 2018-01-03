import java.awt.Color;
import java.awt.Point;

public class T extends Piece {

	private static Point[] points = {};
	private static Point[] origin;
	
	public T() {
		super(Color.MAGENTA, points, origin);
		Point[] points = {new Point(-1,0), new Point(0,0), new Point(0,-1), new Point(1,0)};
		Point[] origin = {new Point(-1,0), new Point(0,0), new Point(0,-1), new Point(1,0)};
		this.setPoints(points);
		this.setOrigin(origin);


	}

}
