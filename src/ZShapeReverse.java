import java.awt.Color;


public class ZShapeReverse extends Piece {

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	public ZShapeReverse() {
		super(Color.RED, Blocks, origin);
		Block[] Blocks = {new Block(-1,-1), new Block(0,-1), new Block(0,0), new Block(1,0)};
		Block[] origin = {new Block(-1,-1), new Block(0,-1), new Block(0,0), new Block(1,0)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);

	}

}
