
// Armando Ortiz
// cs3331 - program 5
// Bishop.java

/*
 * This program is used to allow the user to input the type, color, and X and Y-Coordinates of a chess piece.
 * Then allows the user to input a new move for each piece.
 * And then validates whether the piece can perform that movement
 */

// Change log
// [1/30/2020]	[Armando Ortiz] Created Bishop class and default constructor
// [2/11/2020]  [Armando Ortiz] Moved setters, getters, print, and verification method to ChessPiece class
// [2/12/2020]  [Armando Ortiz] Updated constructors to use superclass constructor.

package utep.cs3331.lab5.game;

public class Bishop extends ChessPiece{
	
	//Constructors
	public Bishop() { // Default Constructor
		super("bishop","white",'C',1,true);
	}

	public Bishop(String type, String color, char x, int y, boolean play) {
		super(type,color,x,y,play);
	}
	
	//Methods
	@Override		
	public boolean move(char xCoordinate, int yCoordinate) {
		//Move diagonally
		if((Math.abs(xCoordinate - x)) == (Math.abs(yCoordinate - y))){
			return true;
		}
		else {
			return false;
		}
	}
}
