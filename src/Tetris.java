import javax.swing.SwingUtilities;

public class Tetris {
	
	private TetrisBoard board;
	private TetrisGUI gui;
	
	public Tetris() {
		this.board = new TetrisBoard();
		this.gui = new TetrisGUI();
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Tetris();
				
			}
		}
		);
	}
}
