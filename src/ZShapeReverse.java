import java.awt.Color;
import java.awt.Point;

public class ZShapeReverse extends Piece {

	private static Point[] points = {};

	public ZShapeReverse() {
		super(Color.RED, points);
		Point[] points = {new Point(0,-1), new Point(1,-1), new Point(1,0), new Point(2,0)};
		this.setPoints(points);
	}

}
