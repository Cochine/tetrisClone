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
		} if (e.getKeyCode() == KeyEvent.VK_Z) {
			board.rotateLeft();
		} if (e.getKeyCode() == KeyEvent.VK_X) {
			board.rotateRight();
		} if (e.getKeyCode() == KeyEvent.VK_D) {
			board.info();
		} if (e.getKeyCode() == KeyEvent.VK_A) {
			board.piecePlaced();
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
