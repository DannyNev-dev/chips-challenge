package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.awt.Point;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key.Colour;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Treasure;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;

/**
 * Contains methods and static variables required for reading levels from json files
 * Contains methods that return a maze which will be parsed maze module.
 *
 * @author Daniel Neville
 */
public class LevelReader {
	
	/** The level num. */
	int levelNum;
	
	/** The maze obj. */
	JsonObject mazeObj;
	
	/**
	 * Instantiates a new level reader.
	 * @param levelNum
	 * @throws Exception
	 */
	public LevelReader(int levelNum) throws Exception{
		this.levelNum = levelNum;
		mazeObj = loadJson();
	}
	
	/**
	 * Deserialize level.
	 *
	 * @return the maze
	 */
	public Tile[][] loadBoard() {
		Player p = loadPlayer();
		int target = loadTarget();
	    JsonArray jList = mazeObj.getJsonArray("board");
		return makeBoard(jList,target,p);		
	}
	/**
	 * Load json.
	 *
	 * @return the json object
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public JsonObject loadJson() throws IOException {
		//invalid number check, i assume that int primitive cannot be null
		if(levelNum<=0 || levelNum>1) {
			throw new IOException("Not a valid level Number");
		}	
		Reader reader = Files.newBufferedReader(Paths.get("levels//level"+levelNum+".json"));    
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
	public  Player loadPlayer()
	{
		//create player object from JSON
		int x = mazeObj.getJsonObject("player").getInt("x");
		int y = mazeObj.getJsonObject("player").getInt("y");
	    Player p = new Player(new Point(x,y));
		return p;
	}
	
	/**
	 * Load target.
	 *
	 * @return the int
	 */
	public int loadTarget()  
	{
	    //target is the number of chips (treasures) that need to be collected
	    return mazeObj.getInt("target");
	}
	
	/**
     * Parses the tile.
     *
     * @param jsonObj the json obj
     * @param target the target
     * @return the tile
     * @throws Exception
     */
    public static Tile parseTile(JsonObject jsonObj,int target,Player p) throws RuntimeException { 
           
            Item item = null;
            Tile tile = null;
            String type = jsonObj.getString("type");
            String var;
           
            switch(type) {
    //Create cells based on type
            case "Tile":
                    var = jsonObj.getString("item");       
                    switch(var) {
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
                            default:
                                    throw new RuntimeException("Invalid JSON input for the item");
                                    //System.out.println("");       //may need to throw custom exception
                                   
                    }
                    tile = new ItemTile(item);      //item is null if incorrect json input for item is present
                    break;
            case "Door":
                    var = jsonObj.getString("item");       
                    switch(var) {
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
                                    //System.out.println("Invalid JSON input for the door colour"); //may need to throw custom exception
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
                    tile = new InfoTile(jsonObj.getString("item"),null);
                    break;
            default:
                    throw new RuntimeException("Json Array element has no matching type");
                    //System.out.print("Incorrect Json Format");    //may need to throw custom exception
            }
    return tile;   
    }
/**
 * Make the board for the maze from the json array.
 *
 * @param jList the j list
 * @param target the target
 * @param p the p
 * @return the tile[][]
 */
public Tile[][] makeBoard(JsonArray jList,int target,Player p){
	int length = jList.size();
    //initialize board
    Tile[][] board = new Tile[jList.getJsonObject(0).getJsonArray("row").size()][length];	    
    for(int i=0; i<length; i++) {	    	
    	JsonObject jsonObj = jList.getJsonObject(i);
    	JsonArray internalList = jsonObj.getJsonArray("row");
    	for(int j=0;j<internalList.size();j++) {
    		JsonObject internalJObj = internalList.getJsonObject(j);
    		board[i][j] = parseTile(internalJObj,target,p); //Parse each tile from the row array into the board of tiles
    	}	    		
    }
	return board;
	}
}