import javax.swing.SwingUtilities;

public class Tetris {
	
	public Tetris() {
		new TetrisBoard();
		new TetrisGUI();
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
