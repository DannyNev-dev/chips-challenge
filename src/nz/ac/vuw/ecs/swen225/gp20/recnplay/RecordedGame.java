package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for storing key information of a game which the user wants to save.
 * @author YanLu (300520177)
 *
 */
public class RecordedGame {

	int level = -1;
	List<Event> actions;
	
	private RecordedGame() {
	}

	/**
	 * Constructor of RecordedGame.
	 * @param level of current game
	 */
	public RecordedGame(int level) {
		this();
		this.level = level;
		// initiate list of Event object 
		this.clearActions();
	}
	
	private void clearActions() {
		System.out.println("RecordedGame: clear actions");
		this.actions = new ArrayList<Event>();
	}
	
	/**
	 * Set actions with given parameter.
	 * @param actions happened during game playing
	 */
	public void setActions(List<Event> actions) {
		this.actions = actions;
	}

	/**
	 * Get list of happened actions.
	 * @return list of event occurred when game is playing 
	 */
	public List<Event> getActions(){
		return actions;
	}
	
	/**
	 * Get level.
	 * @return current level
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * Set level with given information.
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Add new performed action to list of event.
	 * @param event happened when game is playing 
	 */
	public void addAction(Event event) {
		if (event.getType().equals(Event.Type.SetLevel)) {
			setLevel(event.getLevel());
			clearActions();
		}else {
			if (getLevel() < 0) {
				throw new IllegalArgumentException("Level shall be set prior to other events");
			}
		}
		this.actions.add(event);
	}
}
