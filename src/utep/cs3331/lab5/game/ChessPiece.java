
// Armando Ortiz
// cs3331 - program 5
// ChessPiece.java

/*
 * This is an abstract class meant to be used as a Superclass for the ChessPiece classes
 * Includes attributes, setters, getters, print and verification methods 
 * including: 
 * 		- Check if piece is on board using x and y coordinates
 * 		- Check if piece actually moved
 * 		- move method that should be overriden by subclasses
 * 		- method that prints the verification of the move
 */

// Change log
// [2/10/2020]	[Armando Ortiz] Created ChessPiece class
// [2/11/2020]  [Armando Ortiz] Moved in setters, getters, print, and verification methods
// [2/12/2020]  [Armando Ortiz] Created checkSameSpot() method and updated verify method
// [3/27/2020]  [Armando Ortiz] Updated verify() method to boolean

package utep.cs3331.lab5.game;

public abstract class ChessPiece {
	protected String type;
	protected String color;
	protected char x;
	protected int y;
	protected boolean play;
	
	public ChessPiece() {
	}
	
	public ChessPiece(String type, String color, char x, int y, boolean play) {
		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
		this.play = play;
	}
	
	//Setters
	public void setPieceName(String type) {
		this.type = type;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setXCoordinate(char xCoordinate) {
		this.x = xCoordinate;
	}
	
	public void setYCoordinate(int yCoordinate) {
		this.y= yCoordinate;
	}
	
	//Getters
	public String getType() {
		return type;
	}
	
	public String getColor() {
		return color;
	}
	
	public char getXCoordinate() {
		return x;
	}
	
	public int getYCoordinate() {
		return y;
	}
	
	public boolean getPlay() {
		return play;
	}
	public void printPieceInfo() {
		System.out.println(type+" "+color+" "+Character.toUpperCase(x)+" "+y);
	}
	
	//Verification Methods
	//Used to check whether the X-Coordinate is within the limits of the chess board.
	public boolean checkXLimit(char xCoordinate) {
		switch (xCoordinate) {
			case 'A':
				return true;
			case 'B':
				return true;
			case 'C':
				return true;
			case 'D':
				return true;
			case 'E':
				return true;
			case 'F':
				return true;
			case 'G':
				return true;
			case 'H':
				return true;
			default:
				return false;
		}
	}

	//Used to check whether the Y-Coordinate is within the chess board limits.
	public boolean checkYLimit(int yCoordinate) {
		int chessBoardMin = 1;
		int chessBoardMax = 8;
		if(yCoordinate < chessBoardMin || yCoordinate > chessBoardMax) {//
			return false;
		}
		else {
			return true;
		}
	}
	
	//Checks if piece actually moved
	public boolean checkSameSpot(char x, int y) {
		if (x == this.x && y == this.y) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean verify(char newX, int newY) {
		if((checkXLimit(newX) && checkYLimit(newY)) && checkSameSpot(newX,newY) && move(newX,newY)) {
			return true;
		}
		else {
			System.out.println(this.type+ " at " + this.x + ", " + this.y + " can NOT move to " + newX + ", " + newY);
			return false;
		}
	}
	
	public abstract boolean move(char x, int y); //should be overriden by subclasses
	
	
}
