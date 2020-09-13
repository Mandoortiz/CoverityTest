
// Armando Ortiz
// cs3331 - program 5
// Controller.java

/*
 * This program is used to create a Chess Game and allows user to decide what they want to do
 *  - Create New User or Load Existing User
 *  - Create New Game or Load Existing Game using unique ID
 *  - Save Game and Save User information
 */

// Change log
// [3/10/2020]  [Armando Ortiz] Created Controller Class
// [3/12/2020]  [Armando Ortiz] Created menus and uniqueID()
// [3/15/2020]	[Armando Ortiz] Created createUser() and createUsers() method
// [3/20/2020]  [Armando Ortiz] Created user and game methods
// [3/29/2020]  [Armando Ortiz] Finished adding try catches to  methods
// [3/30/2020]  [Armando Ortiz] Finished functionality. Updated comments.
// [5/11/2020]  [Armando Ortiz] Updated collection types

package utep.cs3331.lab5.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import utep.cs3331.lab5.game.*;

public class Controller {
	private ChessXMLParser chessXMLParser;
	private Set<User> users;
	private User user;
	private Game game;
	
	public Controller() {
			
	}
	
	public void setUpController() {
		chessXMLParser = new ChessXMLParser();
		users = new HashSet<>();
		user = userMenu();
		game = optionMenu();
		playMenu();
	}
	/**********************************************************
	 * Menus
	 **********************************************************/
	
	// menu to have user choose between new user or existing one
	private User userMenu() {
		try {
			chessXMLParser.readFile("Users.xml");
			Queue<String[]> usersInput = chessXMLParser.readUsers();
			users = createUsers(usersInput);
		}catch(NullPointerException nullPointer) {
			System.out.println();
			chessXMLParser.writeUsers(users);
			chessXMLParser.readFile("Users.xml");
		}
		
		Scanner scan = new Scanner(System.in);
		User user = null;;
		boolean truth = false;
		while(!truth) {
			try {
				System.out.println("User Options");
				System.out.println("1: New User \n2: Existing User \n3: Exit");
				System.out.print("Make Selection (Enter number): ");
				int selection = scan.nextInt();
				switch(selection) {
					case 1:
						System.out.println("New User");
						user = newUser(users);
						if(user == null) {
							truth = false;
						}
						else {
							truth = true;
							chessXMLParser.writeUsers(users);
						}
						break;
						
					case 2:
						System.out.println("Load User");
	 					user = loadUser(users);
	 					if(user == null) {
	 						truth = false;
	 					}
	 					else {
	 						truth = true;
	 					}
						break;
						
					case 3:
						System.out.println("Exiting");
						truth = true;
						System.exit(0);
					default:
						System.out.println("Incorrect Selection");
						truth = false;
						break;
				}
			}catch(InputMismatchException inputMismatchException) {
				System.out.println("Input needs to be number");
				scan.nextLine();
			}
			System.out.println();
		}
		return user;
	}
	
	//Option Menu
	private Game optionMenu() {
		Scanner scan = new Scanner(System.in);
		Game game = new Game();
		boolean truth = false;
		while(!truth) {
			try {
				System.out.println(user.getName() + " select an option (by entering number):");
				System.out.println("Warning starting new game will remove previous game");
				System.out.println("1: Start new game \n2: Load Game \n3:Exit");
				System.out.print("Make Selection (Enter number): ");
				int selection = scan.nextInt();
				switch(selection) {
				case 1:
					System.out.println("New Game");
					game = newGame();
					truth = true;	
					break;
					
				case 2:
					System.out.println("Load Game");
					game = loadGame();
					if(game == null) {
						truth = false;
					}
					else {
						truth = true;
					}
					break;
					
				case 3:
					System.out.println("Exiting");
					System.exit(0);
				default:
					System.out.println("Incorrect Selection");
					truth = false;
					break;
				}
				
			}catch(InputMismatchException inputMismatchException) {
				System.out.println("Input needs to be number");
				scan.nextLine();
			}
			System.out.println();
		}
		return game;	
	}
	
	//Menu meant for play options
	private void playMenu() {
		Scanner scan = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			try {
				System.out.println("1: Play Game\n2: Save Game\n3: Exit Game");
				System.out.print("Make Selection (Enter Number): ");
				int input = scan.nextInt();
				if(input == 1) {
					game.play();
				}
				else if(input == 2) {
					saveGame();
				}
				else if(input == 3) {
					System.out.println("Would you like to save? 1: Yes 2: No");
					System.out.print("Make Selection (Enter Number): ");
					input = scan.nextInt();
					if(input == 1) {
						saveGame();
						System.out.println("Exiting");
						exit = true;
					}
					else if(input == 2) {
						System.out.println("Exiting");
						exit = true;
					}
				}
				else {
					System.out.println("Why are we here");
				}
			}catch(InputMismatchException inputMismatchException) {
				System.out.println("Input needs to be number");
				scan.nextLine();
			}
			
		}
	}
	
	
	/**********************************************************
	 * Object Creation Tools
	 **********************************************************/
	
	//Iterates through list to create users
	private Set<User> createUsers(Queue<String[]> usersInput) {
		Set<User> users = new HashSet<>();
		for(String[] input: usersInput)
			users.add(createUser(input));
		return users;
	}
	
	//Creates a user
	private User createUser(String[] userInput) {
		User user = new User(userInput[0]);
		if(userInput[1] != null) {
			String expertiseLevel = userInput[1];
			user.setExpertiseLevel(expertiseLevel);
		}
		if(userInput[2] != null) {
			Game game = new Game(userInput[2]);
			user.setGame(game);
		}
		return user;
	}
	
	//Iterates through list to create list of Chess Pieces
	private List<ChessPiece> createPieces(List<String[]> piecesInput){
		List<ChessPiece> pieces = new ArrayList<>();
		for(String[] tempPiece: piecesInput) {
			String name = tempPiece[0];
			String color = tempPiece[1];
			char x = Character.toUpperCase(tempPiece[2].charAt(0));
			int y = Integer.parseInt(tempPiece[3]);
			boolean play = Boolean.parseBoolean(tempPiece[4]);
			ChessPiece piece = createPiece(name,color,x,y, play);
			pieces.add(piece);
		}
		return pieces;
	}
	
	// Used to create the chess piece depending on the user's input
	public ChessPiece createPiece(String type, String color, char x, int y, boolean play) {
		if(color.toLowerCase().equals("black") || color.toLowerCase().equals("white")) {
			if (type.toLowerCase().contains("pawn")) {// Creates pawn piece
				return new Pawn(type,color,x,y,play);
			}	
			else if(type.toLowerCase().contains("rook")) {// Creates rook piece
				return new Rook(type,color,x,y,play);
			}	
			else if(type.toLowerCase().contains("bishop")) {// Creates bishop piece
				return  new Bishop(type,color,x,y,play);
			}	
			else if(type.toLowerCase().contains("knight")){// Creates knight piece
				return  new Knight(type,color,x,y,play);
			}	
			else if(type.toLowerCase().contains("king")) {// Creates king piece
				return new King(type,color,x,y,play);
			}	
			else if(type.toLowerCase().contains("queen")) {//Creates queen piece
				return new Queen(type,color,x,y,play);
			}	
			else{//Notifies user that the piece they entered is invalid 
				System.out.println("Error: " +type+ " not a valid piece");
				return null;
			}
		}
		else {
			System.out.println("Error: " +type+ " not a valid piece");
			return null;
		}
	}
	
	//Creates a unique ID for each game
	private String uniqueID() {
		String ID = "";
		Random rand = new Random();
		char c = ' ';
		for(int i = 0; i < 6; i++) {
			if (i < 2) {
				c = (char)(rand.nextInt(26)+'a');
			}
			else {
				c = (char)(rand.nextInt(9)+'0');
				
			}
			ID = ID + c;
		}	
		return ID;
	}
	
	/**********************************************************
	 * User Functions
	 **********************************************************/
	private User newUser(Set<User> users) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scan.next().trim();
		if(compareUsernames(username, users) == null) {//Checks that user doesn't already exist
			System.out.print("Enter experience level (Novice, Medium, Advanced, Master): ");
			String experience = scan.next().trim();
			if(experience.equalsIgnoreCase("Novice") || experience.equalsIgnoreCase("Medium") // Checks if user types correct experience level
				|| experience.equalsIgnoreCase("Advanced") || experience.equalsIgnoreCase("Master")) {
				User user = new User(username,experience);
				users.add(user);
				return user;
			}
			else {
				System.out.println("Incorrect Experience Level");
				return null;
			}
		}
		else {
			System.out.println("User already exists");
			return null;
		}
	}
	
	//Used to load a user from a list of users
	private User loadUser(Set<User> users) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scan.next().trim();
		User usernameIteration = compareUsernames(username,users);
		if(usernameIteration != null){
			User user = usernameIteration;
			return user;
		}
		else {
			System.out.println("User not found");
			return null;
		}
	}
	
	//Used to compare usernames and return the location of the user in the list
	public User compareUsernames(String username, Set<User> users) {
		User userInput;
		try {
			userInput = users.stream()
					.filter(u -> u.getName().equals(username)).findFirst().get();
		} catch(NoSuchElementException noSuchElement) {
			userInput = null;
		}
		return userInput;
	}
	
	/**********************************************************
	 * Game Functions
	 **********************************************************/
	
	//Creates a new game
	private Game newGame() {
		String id = uniqueID();
		Game game = new Game(id);
		user.setGame(game);
		System.out.println("Reading Config File");
		chessXMLParser.readFile("NewGame.xml");//NewGame config file needed
		List<String[]> piecesInput = chessXMLParser.readPieces();
		List<ChessPiece> pieces = createPieces(piecesInput);
		game.setPieces(pieces);
		game.setBoard(pieces);
		return game;
	}
	
	//Used to load a game
	private Game loadGame() {
		Game game = new Game();
		System.out.println("Loading Most Recent Game");
		try {
			System.out.println(user.getGame().getId());//Uses user's most recent game id
			chessXMLParser.readFile(user.getGame().getId()+".xml");
			List<String[]> piecesInput = chessXMLParser.readPieces();
			List<ChessPiece> pieces = createPieces(piecesInput);
			game.setPieces(pieces);
			game.setBoard(pieces);
		}catch(NullPointerException nullPointer) {
			System.out.println("No games found");
			return null;
		}
		return game;
	}
	
	//Used to save list of user information, Game Pieces, and Game State
	private void saveGame() {
		System.out.println("Saving");
		List<ChessPiece> pieces = game.getPieces();
		chessXMLParser.writeUsers(users);
		chessXMLParser.writePieces(pieces, game.getId());
		chessXMLParser.writeGame(user, game);
	}
	
	/**********************************************************
	 * Main
	 **********************************************************/
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.setUpController();
	}
	
	

}
