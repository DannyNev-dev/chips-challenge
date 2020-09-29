
package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;


/**
 * A class for storing key information of 
 * @author YanLu
 *
 */
public class RecordedGame {
	
	int level;
	ArrayList<Event> steps;
	
	public RecordedGame(int level) {
		this.level = level;
		// init arraylist
		this.steps = new ArrayList<Event>();
	}

	public ArrayList<Event> getSteps(){
		return steps;
	}
	
	public int getLevel(){
		return level;
	}

	public void add(Event event) {
		this.steps.add(event);
	}
}
