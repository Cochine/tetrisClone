import java.awt.Color;


public class T extends Piece {

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	public T() {
		super(Color.MAGENTA, Blocks, origin);
		Block[] Blocks = {new Block(-1,0), new Block(0,0), new Block(0,-1), new Block(1,0)};
		Block[] origin = {new Block(-1,0), new Block(0,0), new Block(0,-1), new Block(1,0)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);


	}

}
