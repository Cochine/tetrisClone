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
	private Piece currentPiece;
	private Random pieceGenerator;
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public TetrisBoard() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(500,800));	
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
		        g2d.fillRect((p.x+5)*50, (p.y+5)*50, 50, 50);
		        g2d.setColor(piece.getColor().darker());
		        g2d.drawLine((p.x+5)*50, (p.y+5)*50+49, (p.x+5)*50+50-1, (p.y+5)*50+49);
		        	
		        }
        }
	        
		g2d.dispose();
		
	}
	
	public void move(int xDirection, int yDirection) {
		for (Point p: currentPiece.getPoints()) {
			p.setLocation(p.getX()+xDirection, p.getY()+yDirection);
			repaint();
		}
	}
	
	public void newPiece() {
		Piece piece = PieceFactory.create(pieces[pieceGenerator.nextInt(pieces.length)]);
		this.boardPieces.add(piece);
		this.currentPiece = piece;
		repaint();
		
	}
	
	
}
