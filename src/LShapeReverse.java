import java.awt.Color;
import java.awt.Point;

public class LShapeReverse extends Piece{

	private static Point[] points = {};
	
	public LShapeReverse() {
		super(Color.BLUE, points);
		Point[] points = {new Point(0,-1), new Point(0,0), new Point(1,0), new Point(2,0)};
		this.setPoints(points);
	}
	
}
