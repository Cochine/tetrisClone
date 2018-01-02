import java.awt.Color;
import java.awt.Point;

public class ZShapeReverse extends Piece {

	private static Point[] points = {new Point(-1,-1), new Point(0,-1), new Point(0,0), new Point(1,0)};

	public ZShapeReverse() {
		super(Color.RED, points);
	}

}
