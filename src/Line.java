import java.awt.Color;

public class Line extends Piece {

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	public Line() {
		super (Color.cyan, Blocks, origin);
		Block[] Blocks = {new Block(-1,0), new Block(0,0), new Block(1,0), new Block(2,0)};
		Block[] origin = {new Block(-1,0), new Block(0,0), new Block(1,0), new Block(2,0)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);
	}
	
}
