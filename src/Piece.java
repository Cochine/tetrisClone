import java.awt.Color;
import java.awt.Point;

public abstract class Piece {
	
	private Color color;
	private Point points[];

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public Piece(Color color, Point[] points) {
		this.color = color;
		this.points = points;
		
	}
	
	public Color getColor() {
		return color;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
}
