import java.awt.Color;


public class LShape extends Piece{

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	
	public LShape() {
		super(Color.ORANGE, Blocks, origin);
		Block[] Blocks = {new Block(-1,0), new Block(0,0), new Block(1,0), new Block(1,-1)};
		Block[] origin = {new Block(-1,0), new Block(0,0), new Block(1,0), new Block(1,-1)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);

	}
	
}
