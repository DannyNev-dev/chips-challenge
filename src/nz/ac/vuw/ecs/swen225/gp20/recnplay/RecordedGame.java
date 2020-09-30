
package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;


/**
 * A class for storing key information of 
 * @author YanLu
 *
 */
public class RecordedGame {
	
	int level;
	ArrayList<Event> actions;
	
	public RecordedGame(int level) {
		this.level = level;
		// init arraylist
		this.actions = new ArrayList<Event>();
	}

	public ArrayList<Event> getActions(){
		return actions;
	}
	
	public int getLevel(){
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addAction(Event event) {
		if (event.getType().equals(Event.Type.SetLevel)) {
			setLevel(event.getLevel());
		}
		else {
			event.setLevel(this.level);
			this.actions.add(event);
		}	
	}
}
