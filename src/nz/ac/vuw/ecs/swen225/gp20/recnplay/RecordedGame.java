package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for storing key information of 
 * @author YanLu
 *
 */
public class RecordedGame {
	
	int level;
	List<Event> actions;
	
	public void setActions(List<Event> actions) {
		this.actions = actions;
	}

	/**
	 * constructor of RecordedGame.
	 * @param level
	 */
	public RecordedGame(int level) {
		this.level = level;
		// initiate list of Event object 
		this.actions = new ArrayList<Event>();
	}

	/**
	 * get list of happened actions.
	 * @return list of Event
	 */
	public List<Event> getActions(){
		return actions;
	}
	
	/**
	 * get level.
	 * @return current level
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * set level with given information.
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * add new performed action to list of event.
	 * @param event
	 */
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
