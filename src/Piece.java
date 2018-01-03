import java.awt.Color;
import java.awt.Point;

public abstract class Piece {
	
	private Color color;
	private Point points[];
	private Point origin[];

	public Point[] getOrigin() {
		return origin;
	}

	public void setOrigin(Point[] origin) {
		this.origin = origin;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public Piece(Color color, Point[] points, Point[] origin) {
		this.color = color;
		this.points = points;
		this.origin = origin;
		
	}
	

	public Color getColor() {
		return color;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
}
