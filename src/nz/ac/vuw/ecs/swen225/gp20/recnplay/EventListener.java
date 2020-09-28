package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import javax.json.Json;
import javax.json.JsonObject;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;

/**
 * Contains onEvent method which will be called by Application module
 * when a new event occurs(move, pick up key, pick up chip etc.). 
 * @author Yan Lu
 */
public class EventListener implements Plugin {


	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		if (e.getType().equals("Move")) {}
		else if (e.getType().equals("PickupKey")) {}
		else {}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void save(Maze maze) {
		parseTileToString(maze);
	}
	
	
	public void saveToJson(Maze maze) {
		
	
	}
	
	private void parseTileToString(Maze maze) {
			
		Tile[][] board = maze.getBoard();
		
		JsonObject jsonObj = jList.getJsonObject(i);
    	JsonArray internalList = jsonObj.getJsonArray("row");

		for(int i=0; i<board.length; i++) {
			
		
			for(int j=0; j<board.length; j++) {
				Tile tile = board[i][j];
				if(tile instanceof Tile) {
					if(tile instanceof Wall) {
						JsonObject gameRecord = Json.createObjectBuilder()
								.add("Tile", "Wall").build();
					}
					else if(tile instanceof ExitTile) {
						JsonObject gameRecord = Json.createObjectBuilder()
								.add("Tile", "ExitTile").build();
					}
					else if(tile instanceof InfoTile) {
						JsonObject gameRecord = Json.createObjectBuilder()
								.add("Tile", "InfoTile").build();
					}
					else if(tile instanceof ItemTile) {
						JsonObject gameRecord = Json.createObjectBuilder()
								.add("Tile", "InfoTile").build();
					}

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
					    			System.out.println("Invalid JSON input for the item");	//may need to throw custom exception
					    			break;
				    		}
				    		tile = new ItemTile(item);
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
					    			System.out.println("Invalid JSON input for the door colour");	//may need to throw custom exception
					    			break;
				    		}
				    		tile = new ItemTile(item);
				    		break;
				    	
				    	
				    	}
			    	return tile;	

			}
		} 

		JsonObject gameRecord = Json.createObjectBuilder().build();
		
		
		
		
		/*JsonObject value = Json.createObjectBuilder()
				.add("move", Json.createObjectBuilder()
						.add("direction", m.getDirection())
						.build()).build();
		System.out.print(value.toString());
		*/
	



}
