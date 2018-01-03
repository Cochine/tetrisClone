import java.awt.Color;
import java.awt.Point;

public class ZShape extends Piece {

	private static Point[] points = {};
	
	public ZShape() {
		super(Color.GREEN, points);
		Point[] points = {new Point(0,0), new Point(1,0), new Point(1,-1), new Point(2,-1)};
		this.setPoints(points);
	}

}
