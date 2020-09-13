
// Armando Ortiz
// cs3331 - program 5
// Pawn.java

/*
 * This program is used to allow the user to input the type, color, and X and Y-Coordinates of a chess piece.
 * Then allows the user to input a new move for each piece.
 * And then validates whether the piece can perform that movement
 */

// Change log
// [1/30/2020]	[Armando Ortiz] Created Pawn class and default constructor
// [1/31/2020]  [Armando Ortiz] Fixed to allow validation of Black pieces as well as White.
// [2/11/2020]  [Armando Ortiz] Moved setters, getters, print, and verification method to ChessPiece class
// [2/12/2020]  [Armando Ortiz] Updated constructors to use superclass constructor.

package utep.cs3331.lab5.game;

public class Pawn extends ChessPiece{
	
	//Constructors
	public Pawn() { // Default Constructor
		super("pawn","white",'A',2, true);
	}
	
	public Pawn(String type, String color, char x, int y, boolean play) {
		super(type,color,x,y, play);
	}
	
	//Methods
	@Override
	public boolean move(char xCoordinate, int yCoordinate) {
		if(xCoordinate != x) {
			return false;
		}
		else if(color.equals("black")) {// black pieces move down
			//Check if moves by 1 or 2 pieces vertically back
			if((yCoordinate - this.y >=-2) && (yCoordinate - this.y <1)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (color.equals("white")) {// White pieces move up
			//Check if moves by 1 or 2 pieces vertically forward
			if((yCoordinate - this.y <=2) && (yCoordinate - this.y >-1)) {
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
