package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.awt.Point;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import nz.ac.vuw.ecs.swen225.gp20.application.GuiWindow;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key.Colour;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy.Type;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Treasure;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful.DangerType;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;
import nz.ac.vuw.ecs.swen225.gp20.persistence.level2.BugEntity;

/**
 * Contains methods and static variables required for reading levels from json
 * files Contains methods that return a maze which will be parsed maze module.
 *
 * @author Daniel Neville
 */
public class LevelReader {

	/** The level num. */
	final int levelNum;

	/** The maze obj in json form. */
	JsonObject mazeObj;
	
	/**  List of the bugs added to the level*. */
	public ArrayList<BugEntity> bugs = new ArrayList<BugEntity>();
	
	/**  Reference to the games maze class*. */
	public Maze maze;	//maybe protected
	
	/** The gw. */
	public GuiWindow gw;

	/**
	 * Instantiates a new level reader.
	 *
	 * @param levelNum the level num
	 * @throws IOException Signals that level num is invalid
	 */
	public LevelReader(int levelNum) throws IOException {
		this.levelNum = levelNum;
		mazeObj = loadJson();
	}

	/**
	 * Deserialize level.
	 *
	 * @return Tile[][] the board for the maze
	 */
	public Tile[][] loadBoard() {
		Player p = loadPlayer();
		int target = loadTarget();
		JsonArray jList = mazeObj.getJsonArray("board");
		return makeBoard(jList, target, p);
	}

	/**
	 * Load json.
	 *
	 * @return the json object
	 * @throws IOException Signals that level num is invalid
	 */
	public JsonObject loadJson() throws IOException {
		// invalid number check, i assume that int primitive cannot be null
		if (levelNum <= 0 || levelNum > 2) {
			throw new IOException("Not a valid level Number");
		}
		Reader reader = Files.newBufferedReader(Paths.get("levels//level" + levelNum + ".json"));
		JsonReader jReader = Json.createReader(reader);
		JsonObject mazeObj = jReader.readObject();
		reader.close();
		return mazeObj;
	}

	/**
	 * Load player.
	 *
	 * @return the player
	 */
	public Player loadPlayer() {
		// create player object from JSON
		int x = mazeObj.getJsonObject("player").getInt("x");
		int y = mazeObj.getJsonObject("player").getInt("y");
		Player p = new Player(new Point(x, y));
		return p;
	}
	
	/**
	 * Sets the maze. and gives the bugs references
	 *
	 * @param m the new maze
	 */
	public void setMaze(Maze m) {
		this.maze = m;
		//after the board is made we want to begin the bugs movements
		for(BugEntity e: bugs) {
			e.setMaze(maze);
		}
	}
	
	/**
	 * Sets the application. and gives the bugs references
	 *
	 * @param gw the new application
	 */
	//Camila needs to call this after the maze is created or at any point before the player moves
	public void setApplication(GuiWindow gw) {
		this.gw = gw;
		for(BugEntity e: bugs) {
			e.setApplication(gw);
		}
	}
	
	/**
	 * @return the levelNum
	 */
	public int getLevelNum() {
		return levelNum;
	}
	
	/**
	 * Load target.
	 *
	 * @return int target is the number of chips (treasures) that need to be collected
	 */
	public int loadTarget() {
		return mazeObj.getInt("target");
	}

	/**
	 * Parses the tile.
	 *
	 * @param jsonObj the json obj
	 * @param target  the target
	 * @param p the player
	 * @return Tile the tile created from the json object
	 * @throws RuntimeException exception thrown when the json format for items/tiles is incorrect
	 */
	public Tile parseTile(JsonObject jsonObj, int target, Player p) throws RuntimeException {
		
		int bugCount = 1;
		Item item = null;
		Tile tile = null;
		String type = jsonObj.getString("type");
		String var;

		switch (type) {
		// Create cells based on type
		case "Tile":
			var = jsonObj.getString("item");
			switch (var) {
			case "bKey":
				item = new Key(Colour.BLUE);
				break;
			case "rKey":
				item = new Key(Colour.RED);
				break;
			case "gKey":
				item = new Key(Colour.GREEN);
				break;
			case "none":
				break;
			case "treasure":
				item = new Treasure();
				break;
			case "exitLock":
				item = new ExitLock(target);
				break;
			case "player":
				item = p;
				break;
			case "fire":
				item = new Harmful(DangerType.FIRE);
				break;
			case "waterbucket":
				item = new Remedy(Type.BUCKET);
				break;
			default:
				throw new RuntimeException("Invalid JSON input for the item");
			}
			tile = new ItemTile(item); // item is null if incorrect json input for item is present
			break;
		case "Door":
			var = jsonObj.getString("item");
			switch (var) {
			case "green":
				item = new Block(Colour.GREEN);
				break;
			case "red":
				item = new Block(Colour.RED);
				break;
			case "blue":
				item = new Block(Colour.BLUE);
				break;
			default:
				throw new RuntimeException("Invalid JSON input for the door colour");
			}
			tile = new ItemTile(item);
			break;
		case "ExitTile":
			tile = new ExitTile();
			break;
		case "Wall":
			tile = new Wall();
			break;
		case "Info":
			tile = new InfoTile(jsonObj.getString("item"), null);
			break;
		case "Bug":
			//get item to get cords
			JsonObject varObj = jsonObj.getJsonObject("item");
			int x = varObj.getInt("x");
			int y = varObj.getInt("y");
			BugEntity bug = new BugEntity(new Point(x,y),bugCount++);
			tile = new ItemTile(bug);
			bugs.add(bug);
			break;
		default:
			throw new RuntimeException("Json Array element has no matching type");
		}
		return tile;
	}

	/**
	 * Make the board for the maze from the json array.
	 *
	 * @param jList  list of the json objects
	 * @param target the number of treasure on the map
	 * @param p      The player
	 * @return the tile[][]	returns a 2D array of tiles that is the board
	 */
	public Tile[][] makeBoard(JsonArray jList, int target, Player p) {
		int length = jList.size();	//17
		// initialize board
		Tile[][] board = new Tile[length][jList.getJsonObject(0).getJsonArray("row").size()];
		for (int i = 0; i < length; i++) {
			JsonObject jsonObj = jList.getJsonObject(i);
			JsonArray internalList = jsonObj.getJsonArray("row");	//21
			for (int j = 0; j < internalList.size(); j++) {
				JsonObject internalJObj = internalList.getJsonObject(j);
				board[i][j] = parseTile(internalJObj, target, p); // Parse each tile from the row array into the board
																	// of tiles
			}
		}
		return board;
	}
	
}