
// Armando Ortiz
// cs3331 - program 5
// Rook.java

/*
 * This program is used to allow the user to input the type, color, and X and Y-Coordinates of a chess piece.
 * Then allows the user to input a new move for each piece.
 * And then validates whether the piece can perform that movement
 */

// Change log
// [1/30/2020]	[Armando Ortiz] Created Rook class and default constructor
// [2/11/2020]  [Armando Ortiz] Moved setters, getters, print, and verification method to ChessPiece class
// [2/12/2020]  [Armando Ortiz] Updated constructors to use superclass constructor.

package utep.cs3331.lab5.game;

public class Rook extends ChessPiece{
	
	//Constructors
	public Rook() { // Default Constructor
		super("rook","white",'A',1,true);
	}
	
	public Rook(String type, String color, char x, int y, boolean play) {
		super(type,color,x,y,play);
	}
	
	//Methods
	@Override
	public boolean move(char xCoordinate, int yCoordinate) {
		//Move Vertically
		if(xCoordinate == x && yCoordinate != y){
			return true;
		}
		//Move Horizontally
		else if(xCoordinate != x && yCoordinate == y) {
			return true;
		}
		else {
			return false;
		}
	}
}
