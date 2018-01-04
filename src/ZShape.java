import java.awt.Color;


public class ZShape extends Piece {

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	public ZShape() {
		super(Color.GREEN, Blocks, origin);
		Block[] Blocks = {new Block(-1,0), new Block(0,0), new Block(0,-1), new Block(1,-1)};
		Block[] origin = {new Block(-1,0), new Block(0,0), new Block(0,-1), new Block(1,-1)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);
	}

}
