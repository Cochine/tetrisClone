import javax.swing.JFrame;


@SuppressWarnings("serial")
public class TetrisGUI extends JFrame {

	private TetrisBoard board;
	private TetrisController controller;
	
	public TetrisGUI() {
		super("Tetris");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board = new TetrisBoard();
		this.controller = new TetrisController(this.board);
		this.addKeyListener(controller);
		
		super.add(this.board);
		
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	

	
}
	
