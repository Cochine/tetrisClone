import java.awt.Color;
import java.awt.Point;

public class T extends Piece {

	private static Point[] points = {};
	
	public T() {
		super(Color.MAGENTA, points);
		Point[] points = {new Point(0,0), new Point(-1,0), new Point(1,0), new Point(0,-1)};
		this.setPoints(points);

	}

}
