
// Armando Ortiz
// cs3331 - program 5
// User.java

/*
 * This class is used to contain information for each User object
 */

// Change log
// [3/03/2020]	[Armando Ortiz] Created User class, Constructors, setters, and getters
// [3/30/2020]  [Armando Ortiz] Updated Comments

package utep.cs3331.lab5.game;
import java.util.ArrayList;
import java.util.List;

//import utep.cs3331.lab4.chess.Game;
public class User {
	private String name;
	private String expertiseLevel;
	private String userColor;
	private Game game;
	
	// Constructors 
	public User() {
		
	}
	public User(String name) {
		this.name = name;
	}
	public User(String name,String expertiseLevel) {
		this.name = name;
		this.expertiseLevel = expertiseLevel;
	}
	
	public User(String name,String expertiseLevel, Game game) {
		this.name = name;
		this.expertiseLevel = expertiseLevel;
		this.game = game;
	}
	
	public User(String name, String expertiseLevel, String userColor) {
		this.name = name;
		this.expertiseLevel = expertiseLevel;
		this.userColor = userColor;
	}
	
	// Setters
	public void setExpertiseLevel(String expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}
	public void setColor(String color) {
		this.userColor = color;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public String getExpertiseLevel() {
		return expertiseLevel;
	}
	public String getUserColor() {
		return userColor;
	}
	
	public Game getGame(){
		return game;
	}
	
	//Print userInformation
	public void printUserInfo() {
		System.out.println(name + " "+ expertiseLevel + " " + userColor + " " );
	}

}
