import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TetrisBoard extends JPanel implements ActionListener {
	
	private String[] pieces = {"Line", "T", "Square", "LShape", "LShapeReverse", "ZShape", "ZShapeReverse"};
	private Block[][] boardBlocks;
	private Piece currentPiece;
	private Random pieceGenerator;
	private int linesCleared = 0;
	private final int ORIGIN_X = 4;
	private final int ORIGIN_Y = 1;
	private final int PIECE_SIZE = 40;
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;
	private final int DROP_SPEED = 400; // Drop every x milliseconds.
	
	public TetrisBoard() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(PIECE_SIZE*BOARD_WIDTH,PIECE_SIZE*BOARD_HEIGHT));	
		this.boardBlocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
		this.pieceGenerator = new Random();
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.currentPiece = piece;
		Timer timer = new Timer(DROP_SPEED, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		dropDown();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
 
		for (Block p: currentPiece.getBlocks()) {
			g2d.setColor(currentPiece.getColor());
	        g2d.fillRect((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
	        g2d.setColor(currentPiece.getColor().brighter());
	        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE);
	        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
	        g2d.setColor(currentPiece.getColor().darker());
	        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
	        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
	    }
		
		for (int i = 0;  i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (boardBlocks[i][j] != null) {
					g2d.setColor(boardBlocks[i][j].getColor());
			        g2d.fillRect((j)*PIECE_SIZE, (i)*PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
			        g2d.setColor(boardBlocks[i][j].getColor().brighter());
			        g2d.drawLine((j)*PIECE_SIZE, (i)*PIECE_SIZE, (j)*PIECE_SIZE+PIECE_SIZE-1, (i)*PIECE_SIZE);
			        g2d.drawLine((j)*PIECE_SIZE, (i)*PIECE_SIZE, (j)*PIECE_SIZE, (i)*PIECE_SIZE+PIECE_SIZE-1);
			        g2d.setColor(boardBlocks[i][j].getColor().darker());
			        g2d.drawLine((j)*PIECE_SIZE, (i)*PIECE_SIZE+PIECE_SIZE-1, (j)*PIECE_SIZE+PIECE_SIZE-1, (i)*PIECE_SIZE+PIECE_SIZE-1);
			        g2d.drawLine((j)*PIECE_SIZE+PIECE_SIZE-1, (i)*PIECE_SIZE, (j)*PIECE_SIZE+PIECE_SIZE-1, (i)*PIECE_SIZE+PIECE_SIZE-1);
				}
				
			}
		}
		
		g2d.dispose();
		
	}
	
	public boolean movePiece(int xDirection, int yDirection) {
		
		for (Block p: currentPiece.getBlocks()) {
			int newX = p.x+xDirection+ORIGIN_X;
			int newY = p.y+yDirection+ORIGIN_Y;
			if (newX < 0 || newX > BOARD_WIDTH-1 || newY > BOARD_HEIGHT-1 || newY < 0) {
				return false;
			} if (blockAt(newX, newY)) {
				return false;
			}
		
		}
		
		for (Point p: currentPiece.getBlocks()) {
			p.setLocation(p.getX()+xDirection, p.getY()+yDirection);	
		}
		
		repaint();
		return true;
		
	}
	
	public boolean dropDown() {
		if (!movePiece(0,1)) {
			piecePlaced();
			return true;
		} return false;
		
	}
	
	public void quickDrop() {
		while(!dropDown()) {}
	}
	
	public void rotateLeft() {
		
		if (currentPiece.getClass() != Square.class) {
			for (int i = 0; i < 4; i++) {
				Point point = currentPiece.getBlocks()[i];
				Point origin = currentPiece.getOrigin()[i];
				int newx = point.x - origin.x;
				int newy = point.y - origin.y;
				if (origin.y+newx+ORIGIN_X < 0 || origin.y+newx+ORIGIN_X > 9 || -origin.x+newy+ORIGIN_Y > 21 || -origin.x+newy+ORIGIN_Y < 0) {
					return;
				} if (blockAt(origin.y+newx+ORIGIN_X, -origin.x+newy+ORIGIN_Y)) {
					return;
				}
			}
			
			for (int i = 0; i < 4; i++) {
				Point point = currentPiece.getBlocks()[i];
				Point origin = currentPiece.getOrigin()[i];
				int newx = point.x - origin.x;
				int newy = point.y - origin.y;
				point.setLocation(origin);
				point.setLocation(point.y, -point.x);
				origin.setLocation(point);
				point.setLocation(point.x+newx, point.y+newy);
			}
		}
		
		repaint();
		
	}
	
	public void rotateRight() {
		
		if (currentPiece.getClass() != Square.class) {
			for (int i = 0; i < 4; i++) {
				Point point = currentPiece.getBlocks()[i];
				Point origin = currentPiece.getOrigin()[i];
				int newx = point.x - origin.x;
				int newy = point.y - origin.y;
				if (-origin.y+newx+ORIGIN_X < 0 || -origin.y+newx+ORIGIN_X > 9 || origin.x+newy+ORIGIN_Y > 21 || origin.x+newy+ORIGIN_Y < 0) {
					return;
				} if (blockAt(-origin.y+newx+ORIGIN_X, origin.x+newy+ORIGIN_Y)) {
					return;
					
				}
			}
			
			for (int i = 0; i <4; i++) {
				Point point = currentPiece.getBlocks()[i];
				Point origin = currentPiece.getOrigin()[i];
				int newx = point.x - origin.x;
				int newy = point.y - origin.y;
				point.setLocation(origin);
				point.setLocation(-point.y, point.x);
				origin.setLocation(point);
				point.setLocation(point.x+newx, point.y+newy);
				
			}
		}
		
		repaint();
		
	}
	
	public void newPiece() {
		
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.currentPiece = piece;
		repaint();
		
	}
	
	public void piecePlaced() {
		
		for (Block p: currentPiece.getBlocks()) {
			int x = p.x + ORIGIN_X;
			int y = p.y + ORIGIN_Y;
			p.setColor(currentPiece.getColor());
			this.boardBlocks[y][x] = p;
			
		}
		
		clearLines();
		this.newPiece();
		
	}
	
	public boolean blockAt(int x, int y) {
		if (boardBlocks[y][x] == null) {
			return false;
		} return true;	
	}
	
	public void clearLines() {
		
		for (int i = 0; i < BOARD_HEIGHT; i++ ) {
			
			boolean fullLine = true;
			
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if(boardBlocks[i][j] == null) {
					fullLine = false;
					break;
				}
			}

			if (fullLine) {
				this.linesCleared++;
				for (int j = i; j >= 1; j -- ) {
					for (int k = 0; k < BOARD_WIDTH; k++) {
						boardBlocks[j][k] = boardBlocks[j-1][k];
					}
				}
			}
		}
		
		System.out.println(this.linesCleared);
		repaint();
	}
	
	public void clearBoard() {
		this.boardBlocks = new Block[BOARD_HEIGHT][BOARD_WIDTH];
		newPiece();
		repaint();
	}
}
