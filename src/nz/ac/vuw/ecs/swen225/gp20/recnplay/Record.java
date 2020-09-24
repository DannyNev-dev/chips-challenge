package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;

import javax.json.JsonArray;

/**
 * The record adds functionality to record game play
 * and stores the recorded games in a file (in JSON format). 
 * It also adds the dual functionality to load a recorded game, and to replay it. 
 * @author Yan Lu
 *
 */
public class Record {

	private ArrayList<Event> actionRecords;
	private JsonArray level;
	
	
	public void save(Event event) {
		actionRecords = new ArrayList<Event>(); // the stack of action history; stack top on index [0]		
	}
	
	public void load() {}
	
	
	
}
