
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
 * when a new event occurs(set level, move, pick up key, pick up chip etc.). 
 * @author Yan Lu
 */
public class EventListener implements Plugin {

	// This event listener shall be called by application in two kinds of state change
	// level set: in GUIWindow.setLevelNumber()
	//            In this case, an event being created and level being set, the event object being passed in 
	//            callback onEvent()
	// Move+pick: in ?? method:
	//            if it is a move, what's the movement class, SingleMove? 
	//            for example, if it is SingleMove, an Event shall be new() to reflect this move,
	//            aka. assign the move attribute to this SingleMove obj
	//            if it is a pickupKey, then pickupKey is set to true in new Event() and the new event obj
	//            is passed in callback onEvent() as argument
	//            in replay mode, DO NOT create Event instance
    // Application shall have a mode flag to distinguish if it/s in replaying
	// DO NOT RUN PLUGIN CALLBACK WHEN mode is REPLAYING!!!
	
	@Override
	public void onEvent(Event e) {
		if (e.getType().equals("SetLevel")) {}
		else if (e.getType().equals("Move")) {}
		else if (e.getType().equals("PickupKey")) {}
		else {}
	}
	
	public static EventListener eventListenerFactory() {
		return new EventListener();
	}
}


