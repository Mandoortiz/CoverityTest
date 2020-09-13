
// Armando Ortiz
// cs3331 - program 5
// Board.java

/*
 * This program is used to represent a Chess Board. it creates a 2D array that contains ChessPieces
 */

// Change log
// [3/15/2020]  [Armando Ortiz] Created ChessXMLParser class
// [3/20/2020]  [Armando Ortiz] Created read and write methods
// [3/30/2020]  [Armando Ortiz] Fixed read and write methods
// [3/30/2020]  [Armando Ortiz] Updated comments.

package utep.cs3331.lab5.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import utep.cs3331.lab5.game.ChessPiece;
import utep.cs3331.lab5.game.Game;
import utep.cs3331.lab5.game.User;


public class ChessXMLParser {
	private File file;
	private Element root;
	
	public ChessXMLParser() {
		
	}
	
	//Used to create file and pull root from xml
	public void readFile(String input) {
		try {
            File inputFile = new File(input);

            //Create a document builder
            SAXBuilder saxBuilder = new SAXBuilder();

            //Create a DOM tree Obj
            Document configFile = saxBuilder.build(inputFile);

            //get the root element
            root = configFile.getRootElement();
            
        } catch(FileNotFoundException fileNotFound) {
        	System.out.println("File not found");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//Read list of pieces from the config file
	public List<String[]> readPieces() {
		List<Element> piecesElement = root.getChildren();
		List<String[]> piecesArray = new ArrayList<>();
		for (Element piece: piecesElement) {
			String tempPiece[] = new String[5];			
			tempPiece[0] = piece.getValue().trim();
			tempPiece[1] = piece.getAttributeValue("color").trim();
            tempPiece[2] = piece.getAttributeValue("locationX").trim();
            tempPiece[3] = piece.getAttributeValue("locationY").trim();
            tempPiece[4] = piece.getAttributeValue("play").trim();
            piecesArray.add(tempPiece);
        }
		return piecesArray;
	}
	
	//Read list of users from User file
	public Queue<String[]> readUsers() {
		Queue<String[]> users = new LinkedList<>();
		try {
			List<Element> usersElement = root.getChildren("user");
			//usersElement.stream().filter(e -> e.getChild("name").getValu)
			for (Element user :usersElement) {
				String tempUser[] = new String[3];
				if(user.getChild("name") != null) {
					tempUser[0] = user.getChild("name").getValue().trim();
				}
	            if(user.getChild("expertise_level") != null) {
	            	tempUser[1] = user.getChild("expertise_level").getValue().trim();
	            }
	            if(user.getChild("id")!= null) {
	            	tempUser[2] = user.getChild("id").getValue().trim();
	            }
	            
	            users.add(tempUser);
	        }
		}catch(NullPointerException nullPointer) {
			return null;
		}
		return users;
	}
	
	
	//Used to write the list of users in an XML file
	public void writeUsers(Set<User> users) {
		try {
			Element usersElement = new Element("users");
			Document doc = new Document(usersElement);
			
			//Users
			if(users.size() > 0) {
				for(User user:users) {
					Element userElement = new Element("user");
					if(user.getName() != null) {
						userElement.addContent(new Element("name").setText(user.getName()));
					}
					if(user.getExpertiseLevel() != null) {
						userElement.addContent(new Element("expertise_level").setText(user.getExpertiseLevel()));
					}
					if(user.getUserColor() != null) {
						userElement.addContent(new Element("user_color").setText(user.getUserColor()));
					}
					if(user.getGame() != null) {
						userElement.addContent(new Element("id").setText(user.getGame().getId()));
					}
					doc.getRootElement().addContent(userElement);	
				}
			}
			
			//Output
			XMLOutputter output = new XMLOutputter();
			output.setFormat(Format.getPrettyFormat());
			output.output(doc, new FileWriter("Users.xml"));
		}catch(IOException io) {
			System.out.println("Error writing file");
		}	
	}
	
	// Writes the id file containing the location of the chess pieces
	public void writePieces(List<ChessPiece> Pieces, String id) {
		try {
			Element pieces = new Element("pieces");
			Document doc = new Document(pieces);
			
			//Pieces
			for(ChessPiece piece: Pieces) {
				Element pieceElement = new Element("piece").setText(piece.getType());
				pieceElement.setAttribute(new Attribute("color",piece.getColor()));
				pieceElement.setAttribute(new Attribute("locationX",Character.toString(piece.getXCoordinate())));
				pieceElement.setAttribute(new Attribute("locationY",Integer.toString(piece.getYCoordinate())));
				pieceElement.setAttribute(new Attribute("play",Boolean.toString(piece.getPlay())));
				doc.getRootElement().addContent(pieceElement);	
			}
			
			//Output file
			XMLOutputter output = new XMLOutputter();
			output.setFormat(Format.getPrettyFormat());
			output.output(doc, new FileWriter(id+".xml"));
		}catch(IOException io) {
			System.out.println("Error writing file");
		}	
	}
	
	//Writes the file containing the state of a Chess Game
	public void writeGame(User user, Game game) {
		try {
			Element gameElement = new Element("Game_"+game.getId());
			Document doc = new Document(gameElement);
			
			//Chess Pieces
			for(ChessPiece piece: game.getPieces()) {
				Element pieceElement = new Element("piece").setText(piece.getType());
				pieceElement.setAttribute(new Attribute("color",piece.getColor()));
				pieceElement.setAttribute(new Attribute("locationX",Character.toString(piece.getXCoordinate())));
				pieceElement.setAttribute(new Attribute("locationY",Integer.toString(piece.getYCoordinate())));
				pieceElement.setAttribute(new Attribute("play",Boolean.toString(piece.getPlay())));
				doc.getRootElement().addContent(pieceElement);	
			}
			
			//User
			Element userElement = new Element("User");
			if(user.getName() != null) {
				userElement.addContent(new Element("name").setText(user.getName()));
			}
			if(user.getExpertiseLevel() != null) {
				userElement.addContent(new Element("expertise_level").setText(user.getExpertiseLevel()));
			}
			if(user.getUserColor() != null) {
				userElement.addContent(new Element("user_color").setText(user.getUserColor()));
			}
			doc.getRootElement().addContent(userElement);	
			
			//Game Status
			Element gameChildElement = new Element("Game");
			gameChildElement.addContent(new Element("id").setText(game.getId()));
			gameChildElement.addContent(new Element("Max_time").setText(Integer.toString(game.getMaxTime())));
			gameChildElement.addContent(new Element("chat").setText(Boolean.toString(game.getChat())));
			gameChildElement.addContent(new Element("auto_save").setText(Boolean.toString(game.isAutoSave())));
			doc.getRootElement().addContent(gameChildElement);
			
			//Output
			XMLOutputter output = new XMLOutputter();
			output.setFormat(Format.getPrettyFormat());
			output.output(doc, new FileWriter(game.getId()+"GameState.xml"));
		}catch(IOException io) {
			System.out.println("Error writing file");
		}	
	}
	
	
}
