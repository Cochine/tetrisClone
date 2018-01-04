import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TetrisBoard extends JPanel {
	
	private String[] pieces = {"Line", "T", "Square", "LShape", "LShapeReverse", "ZShape", "ZShapeReverse"};
	private Block[][] boardBlocks;
	private Piece currentPiece;
	private Random pieceGenerator;
	private final int ORIGIN_X = 4;
	private final int ORIGIN_Y = 1;
	private final int PIECE_SIZE = 40;
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public TetrisBoard() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(PIECE_SIZE*10,PIECE_SIZE*22));	
		this.boardBlocks = new Block[22][10];
		this.pieceGenerator = new Random();
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.currentPiece = piece;
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
		
		for (int i = 0;  i < boardBlocks.length; i++) {
			for (Block block: boardBlocks[i]) {
				if (block != null) {
					g2d.setColor(block.getColor());
			        g2d.fillRect((block.x + ORIGIN_X)*PIECE_SIZE, (block.y + ORIGIN_Y)*PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
			        g2d.setColor(block.getColor().brighter());
			        g2d.drawLine((block.x + ORIGIN_X)*PIECE_SIZE, (block.y + ORIGIN_Y)*PIECE_SIZE, (block.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (block.y + ORIGIN_Y)*PIECE_SIZE);
			        g2d.drawLine((block.x + ORIGIN_X)*PIECE_SIZE, (block.y + ORIGIN_Y)*PIECE_SIZE, (block.x + ORIGIN_X)*PIECE_SIZE, (block.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
			        g2d.setColor(block.getColor().darker());
			        g2d.drawLine((block.x + ORIGIN_X)*PIECE_SIZE, (block.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1, (block.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (block.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
			        g2d.drawLine((block.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (block.y + ORIGIN_Y)*PIECE_SIZE, (block.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (block.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
				}
				
			}
		}
        
		g2d.dispose();
		
	}
	
	public boolean movePiece(int xDirection, int yDirection) {
		
		for (Block p: currentPiece.getBlocks()) {
			int newX = p.x+xDirection+ORIGIN_X;
			int newY = p.y+yDirection+ORIGIN_Y;
			if (newX < 0 || newX > 9 || newY > 21 || newY < 0) {
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
		
		for (int i = 0; i < boardBlocks.length; i ++) {
			
			boolean fullLine = true;
			
			for (int j = 0; j < boardBlocks[i].length; j++) {
				if(boardBlocks[i][j] == null) {
					fullLine = false;
					break;
				}
			}
			
			//System.out.println(fullLine);
			
			if (fullLine) {
				for (int j = i; j > 0; j--) {
					for (int k = 0; k < boardBlocks[j].length; k++) {
						this.boardBlocks[i][k] = null;
						repaint();
						
					}
				}
						
			}
			
		}
		
		
		
	}
}
