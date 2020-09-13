
// Armando Ortiz
// cs3331 - program 5
// TestCreatePiece.java

/*
 * This program is used to represent a Chess Board. it creates a 2D array that contains ChessPieces
 */

// Change log
// [5/12/2020]  [Armando Ortiz] Made test cases
package utep.cs3331.lab5.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utep.cs3331.lab5.controller.Controller;
import utep.cs3331.lab5.game.ChessPiece;
import utep.cs3331.lab5.game.Game;
import utep.cs3331.lab5.game.Pawn;
import utep.cs3331.lab5.game.Queen;

public class TestCreatePiece {
	Controller controller;
	Queen queen;
	ChessPiece pawn;
	Game game;


	@Before
	public void setUp() throws Exception {
		controller = new Controller();
		queen = new Queen();
		pawn = new Pawn("pawn","white",'A',2,true);
	}

	@Test
	public void testSameClass() {
		assertEquals(pawn.getClass(),controller.createPiece("pawn","white",'A',2,true).getClass());//Test that it creates correct object
	}
	
	@Test
	public void testSameType() {
		assertEquals(queen.getType(),controller.createPiece("queen", "White", 'E', 1, true).getType()); // Test that it creates correct
		assertEquals(queen.getColor(),controller.createPiece("queen", "white", 'E', 1, true).getColor());
		assertEquals(queen.getXCoordinate(),controller.createPiece("queen", "White", 'E', 1, true).getXCoordinate());
		assertEquals(queen.getYCoordinate(),controller.createPiece("queen", "White", 'E', 1, true).getYCoordinate());
		assertEquals(queen.getPlay(),controller.createPiece("queen", "White", 'E', 1, true).getPlay());
	}
	
	@Test
	public void testIncorrectColor() {
		assertNull(controller.createPiece("pawn", "brun", 'B', 2, true)); // Test incorrect piece color
	}
	
	@Test
	public void testIncorrectPieceType() {
		assertNull(controller.createPiece("tower", "black", 'B', 2, true)); // Test incorrect piece name
	}
}
