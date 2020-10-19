package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.recnplay.test.TestMove;

/**
 * A class for storing key information of 
 * @author YanLu
 *
 */
public class RecordedGame {

	int level = -1;
	List<Event> actions;
	
	private RecordedGame() {
	}

	/**
	 * constructor of RecordedGame.
	 * @param level
	 */
	public RecordedGame(int level) {
		this();
		this.level = level;
		// initiate list of Event object 
		this.actions = new ArrayList<Event>();
	}
	
	
	public void setActions(List<Event> actions) {
		this.actions = actions;
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
		}else {
			if (getLevel() < 0) {
				throw new IllegalArgumentException("Level shall be set prior to other events");
			}
		}
		this.actions.add(event);
	}
}
