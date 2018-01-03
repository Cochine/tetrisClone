import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TetrisBoard extends JPanel {
	
	private String[] pieces = {"Line", "T", "Square", "LShape", "LShapeReverse", "ZShape", "ZShapeReverse"};
	private ArrayList<Piece> boardPieces = new ArrayList<Piece>();
	private Point[][] boardBlocks;
	private Piece currentPiece;
	private Random pieceGenerator;
	private final int ORIGIN_X = 4;
	private final int ORIGIN_Y = 5;
	private final int PIECE_SIZE = 40;
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public TetrisBoard() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(PIECE_SIZE*10,PIECE_SIZE*22));	
		this.boardBlocks = new Point[10][22];
		this.pieceGenerator = new Random();
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.boardPieces.add(piece);
		this.currentPiece = piece;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
 
        for (Piece piece: boardPieces) {
		    for (Point p: piece.getPoints()) {
		        g2d.setColor(piece.getColor());
		        g2d.fillRect((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, PIECE_SIZE, PIECE_SIZE);
		        g2d.setColor(piece.getColor().brighter());
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE);
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        g2d.setColor(piece.getColor().darker());
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        g2d.drawLine((p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE, (p.x + ORIGIN_X)*PIECE_SIZE+PIECE_SIZE-1, (p.y + ORIGIN_Y)*PIECE_SIZE+PIECE_SIZE-1);
		        	
		        }
        }
	        
		g2d.dispose();
		
	}
	
	public void move(int xDirection, int yDirection) {
		
		for (Point p: currentPiece.getPoints()) {
			int newX = p.x+xDirection+ORIGIN_X;
			int newY = p.y+yDirection+ORIGIN_Y;
			if (newX < 0 || newX > 9 || newY > 21 || newY < 0) {
				return;
			} if (blockAt(newX, newY)) {
				return;
			}
		
		}
		
		for (Point p: currentPiece.getPoints()) {
			p.setLocation(p.getX()+xDirection, p.getY()+yDirection);	
		}
		
		repaint();
		return;
		
	}
	
	public void rotateLeft() {
		
		if (currentPiece.getClass() != Square.class) {
			for (int i = 0; i < 4; i++) {
				Point point = currentPiece.getPoints()[i];
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
				Point point = currentPiece.getPoints()[i];
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
	
	public void info() {
		for (int i = 0; i < boardBlocks.length; i ++) {
			for (int x = 0; x < boardBlocks[i].length; x++) {
				if (boardBlocks[i][x] != null) {
					System.out.println(boardBlocks[i][x]);
				}
			}
		}
	}
	
	public void newPiece() {
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.boardPieces.add(piece);
		this.currentPiece = piece;
		repaint();
		
	}
	
	public void piecePlaced() {
		for (Point p: currentPiece.getPoints()) {
			int x = p.x + ORIGIN_X;
			int y = p.y + ORIGIN_Y;
			this.boardBlocks[x][y] = p;
			
		}
		
		this.newPiece();
		System.out.println(this.boardBlocks[0][21]);
		
	}
	
	public boolean blockAt(int x, int y) {
		if (boardBlocks[x][y] == null) {
			return false;
		} return true;	
	}
	
}
