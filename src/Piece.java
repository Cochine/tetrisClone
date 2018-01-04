import java.awt.Color;

public abstract class Piece {
	
	private Color color;
	private Block Blocks[];
	private Block origin[];

	public Block[] getOrigin() {
		return origin;
	}

	public void setOrigin(Block[] origin) {
		this.origin = origin;
	}

	public void setBlocks(Block[] Blocks) {
		this.Blocks = Blocks;
	}

	public Piece(Color color, Block[] Blocks, Block[] origin) {
		this.color = color;
		this.Blocks = Blocks;
		this.origin = origin;
		
	}
	

	public Color getColor() {
		return color;
	}
	
	public Block[] getBlocks() {
		return Blocks;
	}
	
}
