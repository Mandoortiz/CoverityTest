
// Armando Ortiz
// cs3331 - program 5
// TestCompareUsernames.java

/*
 * This program is used to test the compareUsernames method
 */

// Change log
// [5/12/2020]  [Armando Ortiz] Created test cases
package utep.cs3331.lab5.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import utep.cs3331.lab5.controller.Controller;
import utep.cs3331.lab5.game.User;

public class TestCompareUsernames {
	Controller controller;
	User user;
	Set<User> users;

	@Before
	public void setUp() throws Exception {
		controller = new Controller();
		user = new User();
		users = new HashSet<>();
		users.add(new User("Jonathan"));
		users.add(new User("Joanna"));
		
	}
	
	@Test
	public void testReturnsUser() {
		assertEquals(user.getClass(),controller.compareUsernames("Joanna",users).getClass());
	}
	
	@Test
	public void testNameExists() {
		assertEquals("Joanna",controller.compareUsernames("Joanna",users).getName());
	}
	
	@Test
	public void testNameIncorrect() {
		assertNull(controller.compareUsernames("Terra",users));
	}

}


