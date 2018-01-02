
public class PieceFactory {

	public static Piece create(String pieceName) {
		Piece piece = null;
		if(pieceName == "Line") {
			piece = new Line();
		} else if (pieceName == "T") {
			piece = new T();
		} else if (pieceName == "Square") {
			piece = new Square();
		} else if (pieceName == "LShape") {
			piece = new LShape();
		} else if (pieceName == "LShapeReverse") {
			piece = new LShapeReverse();
		} else if (pieceName == "ZShape") {
			piece = new ZShape();
		} else if (pieceName == "ZShapeReverse") {
			piece = new ZShapeReverse();
		} 
		
		return piece;
		
	}
	
}
