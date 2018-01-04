import java.awt.Color;


public class Square extends Piece {

	private static Block[] Blocks = {};
	private static Block[] origin;
	
	public Square() {
		
		super(Color.yellow, Blocks, origin);
		Block[] Blocks = {new Block(0,0), new Block(1,0), new Block(0,-1), new Block(1,-1)};
		Block[] origin = {new Block(0,0), new Block(1,0), new Block(0,-1), new Block(1,-1)};
		this.setBlocks(Blocks);
		this.setOrigin(origin);

		
	}

}
