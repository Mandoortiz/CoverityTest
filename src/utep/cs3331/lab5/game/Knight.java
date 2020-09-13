
// Armando Ortiz
// cs3331 - program 5
// Knight.java

/*
 * This program is used to allow the user to input the type, color, and X and Y-Coordinates of a chess piece.
 * Then allows the user to input a new move for each piece.
 * And then validates whether the piece can perform that movement
 */

// Change log
// [1/30/2020]	[Armando Ortiz] Created Knight class and default constructor
// [2/11/2020]  [Armando Ortiz] Moved setters, getters, print, and verification method to ChessPiece class
// [2/12/2020]  [Armando Ortiz] Updated constructors to use superclass constructor.

package utep.cs3331.lab5.game;

public class Knight extends ChessPiece{
	//Constructors
	public Knight() { // Default Constructor
		super("knight","white",'B',1, true);
	}

	public Knight(String type, String color, char x, int y, boolean play) {
		super(type,color,x,y, play);
	}
	
	//Methods
	@Override
	public boolean move(char xCoordinate, int yCoordinate) {
		// Move 2 pieces horizontally
		if(Math.abs(xCoordinate - this.x) == 2) {
			if(Math.abs(yCoordinate-this.y) == 1) {// Move 1 piece vertically
				return true;
			}
			else {
				return false;
			}
		}
		//Move 2 pieces vertically
		else if(Math.abs(yCoordinate - this.y) == 2) {
			if(Math.abs(xCoordinate- this.x) == 1) {// Move 1 piece horizontally
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			return false;
		}
	}
}
