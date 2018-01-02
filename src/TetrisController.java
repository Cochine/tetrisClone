import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisController implements KeyListener {

	private TetrisBoard board;
		
	public TetrisController(TetrisBoard board) {
		this.board = board;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			board.move(-1,0);
			
		} if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			board.move(1,0);
			
		} if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			board.move(0, 1);
			
		} if (e.getKeyCode() == KeyEvent.VK_UP) {
			board.move(0, -1);
			
		} if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			board.newPiece();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
