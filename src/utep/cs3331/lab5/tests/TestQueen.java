
// Armando Ortiz
// cs3331 - program 5
// TestQueen.java

/*
 * This program is used to represent a Chess Board. it creates a 2D array that contains ChessPieces
 */

// Change log
// [5/12/2020]  [Armando Ortiz] Made test cases
package utep.cs3331.lab5.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import utep.cs3331.lab5.game.Queen;

public class TestQueen {
	Queen queen;


	@Before
	public void setUp() throws Exception {
		queen = new Queen();	
	}

	@Test
	public void testVertical() {
		assertTrue("Testing Vertical",queen.move('E', 2));// Test Vertical
	}
	
	@Test
	public void testDiagonal() {
		assertTrue("Testing Diagonal",queen.move('C', 3)); // Test Diagonal
	}
	
	@Test
	public void testHorizontal() {
		assertTrue("Testing Horizontal",queen.move('B', 1)); // Test Horizontal
	}
	
	@Test
	public void testIncorrectMove() {
		assertFalse("Testing Incorrect move",queen.move('B', 7)); // Test an incorrect move
	}
}
