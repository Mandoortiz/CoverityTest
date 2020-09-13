
// Armando Ortiz
// cs3331 - program 5
// Board.java

/*
 * This program is used to represent a Chess Board. it creates a 2D array that contains ChessPieces
 */

// Change log
// [3/15/2020]  [Armando Ortiz] Created Board class
// [3/20/2020]	[Armando Ortiz] Created placePiece() method
// [3/27/2020]  [Armando Ortiz] Added movePiece()
// [3/30/2020]  [Armando Ortiz] Updated comments.
// [5/11/2020]  [Armando Ortiz] Made constructor private. Updated to Singleton

package utep.cs3331.lab5.game;


public class Board {
	private ChessPiece board[][];
	
	// Used to create average sized 8x8 chess board
	private Board() {
		this.board = new ChessPiece[8][8];
	}
	
	private static class BoardHolder {
		private static final Board INSTANCE = new Board();
	}
	
	public static Board getInstance() {
		return BoardHolder.INSTANCE;
	}
	
	// Places a chess piece on the board
	public void placePiece(ChessPiece piece, char x, int y) {
		int xLoc = xLocation(x); //xLoc is placed on 
		y = board.length -y;
		this.board[y][xLoc] = piece;
	}
	
	//Places the piece on the correct column of the 2D array;
	public int xLocation(char x) {
		switch (x) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		default:
			return -1;
		}
	}
	
	public boolean checkEmpty(ChessPiece piece) {
		if (this.board[piece.getXCoordinate()][piece.getYCoordinate()] == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Returns a chess piece
	public ChessPiece getPiece(char x, int y) {
		int returnX = xLocation(x);
		return board[board.length-y][returnX];
	}
	
	//Prints a representation of the current state of the chess board
	public void printBoard() {
		System.out.print("  ");
		for(char i = 'A'; i <= 'H'; i++) {
			System.out.print(i+"\t");
		}
		System.out.println();
		for(int i = 0; i < board.length;i++) {
			System.out.print((board.length-i) + " "); 
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null) {
					System.out.print(board[i][j].getType()+"\t");
				}
				else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}
	
	// Used to move chess piece to new location
	public void movePiece(char newX, int newY, char oldX, int oldY) {
		int newXLocation = xLocation(newX);
		int oldXLocation = xLocation(oldX);
		int newYLocation = board.length-newY;
		int oldYLocation = board.length-oldY;
		
		//Checks if piece is making valid move and being placed on an empty spot on the board
		if(board[newYLocation][newXLocation] == null && board[oldYLocation][oldXLocation].verify(newX, newY)) {
			System.out.println(board[oldYLocation][oldXLocation].getType() +" can move to " + newX + ", " + newY);
			placePiece(board[oldYLocation][oldXLocation],newX,newY);
			board[newYLocation][newXLocation].setXCoordinate(newX);
			board[newYLocation][newXLocation].setYCoordinate(newY);
			board[oldYLocation][oldXLocation] = null;
		}
		else {
			System.out.println(board[oldYLocation][oldXLocation].getType() +" can NOT move to " + newX + ", " + newY);
		}
	}
}
