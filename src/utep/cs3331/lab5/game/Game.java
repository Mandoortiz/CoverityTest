
// Armando Ortiz
// cs3331 - program 5
// Game.java

/*
 * The purpose of this program is to allow the user to perform the functions of a Chess Game
 * 	- Contains information for game
 *  - Allows user to select a piece and move it
 *  - Allows user to input x and y for piece they want and location they'll place it
 */

// Change log
// [3/16/2020]	[Armando Ortiz] Created Game Class, Constructors, Setters, Getters
// [3/29/2020]  [Armando Ortiz] Made the inputs keep looping until they worked
// [3/30/2020]  [Armando Ortiz] Updated comments

package utep.cs3331.lab5.game;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Game {
	private String id;
	private int maxTime;
	private boolean chat;
	private boolean autoSave;
	private Board board;
	private List<ChessPiece> pieces;
	
	//Constructors
	public Game() {
		
	}
	
	public Game(String id) {
		this.id = id;
		this.maxTime = 90;
		this.chat = false;
	}
	
	public Game(String id, int maxTime, boolean chat, boolean autoSave) {
		this.setId(id);
		this.setMaxTime(maxTime);
		this.setChat(chat);
		this.setAutoSave(autoSave);
	}
	
	//Setters
	public void setId(String id) {
		this.id = id;
	}
	
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public void setChat(boolean chat) {
		this.chat = chat;
	}

	public void setBoard(List<ChessPiece> pieces) {
		this.board = Board.getInstance();
		Iterator<ChessPiece> iterator = pieces.iterator();
		while(iterator.hasNext()) {
			ChessPiece piece = iterator.next();
			board.placePiece(piece,piece.getXCoordinate(),piece.getYCoordinate());
		}
	}
	
	public void setPieces(List<ChessPiece> pieces) {
		this.pieces = pieces;
	}
	
	//Getters
	public String getId() {
		return id;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public boolean getChat() {
		return chat;
	}

	public boolean isAutoSave() {
		return autoSave;
	}

	public void setAutoSave(boolean autoSave) {
		this.autoSave = autoSave;
	}

	public Board getBoard() {
		return board;
	}
	
	public List<ChessPiece> getPieces(){
		return pieces;
	}
	
	//Methods
	//Menu that allows the user to either make a move or stop playing
	public void play() {
		System.out.println("Playing game");
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		boolean exit = false;
		while(!exit) {
			try {
				board.printBoard();
				System.out.println("\n1: Make Move\n2: Stop Playing");
				System.out.print("Make Selection(Enter number): ");
				input = scanner.nextInt();
				switch(input) {
				case 1:
					selectPiece();
					exit = false;
					break;
				case 2:
					exit = true;
					break;
				default:
					exit = false;
					System.out.println("Invalid Entry");
					break;
				}
			}catch(InputMismatchException inputMismatchException) {
				exit = false;
				System.out.println("Invalid Entry");
				scanner.nextLine();
			}
			System.out.println();
		}
	}
	
	// User selects piece on board they want to move
	public void selectPiece() {
		System.out.println("Select Piece to Move");
		char x = inputX();
		int y = inputY();
		if(board.getPiece(x, y) != null) {
			makeMove(x,y);
		}
		else {
			System.out.println("No Piece Selected");
		}
	}
	
	// User selects new location for Chess Piece
	public void makeMove(char oldX, int oldY) {
		System.out.println("Select New Location");
		char x = inputX();
		int y = inputY();
		board.movePiece(x, y, oldX, oldY);
	}
	
	// Have user input X-Coordinate for Chess Piece
	public char inputX() {
		Scanner scanner = new Scanner(System.in);
		char x = 'a';
		boolean truth = false;
		while(!truth) {
			System.out.print("Input X (Letter A to H): ");
			x = Character.toUpperCase(scanner.next().charAt(0));
			if(x < 'A' && x <= 'H') {
				System.out.println("Incorrect input");
				truth = false;
			}
			else {
				truth = true;
			}
		}
		return x;
	}
	
	// Have user input Y-Coordinate for chess board
	public int inputY() {
		Scanner scanner = new Scanner(System.in);
		int y = 0;
		boolean truth = false;
		while(!truth) {
			try {
				System.out.print("Input Y (Number from 1 to 8): ");
				y = scanner.nextInt();
				if(y < 0 && y <= 8) {
					System.out.println("Incorrect input");
					truth = false;
				}
				else {
					truth = true;
				}
			}catch(InputMismatchException inputMismatchException) {
				System.out.println("Incorrect input");
				scanner.nextLine();
				truth = false;
			}
		}
		return y;
	}
}
