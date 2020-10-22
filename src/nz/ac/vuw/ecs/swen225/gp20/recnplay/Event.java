package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;

/**
 * A class which will be instantiated into specific instance 
 * and will be stored in game history for future replay use
 * such as Move object which is created in Application module.
 * @author YanLu
 */
public class Event {
	
	private Type type;
	
	/**
	 * List all types of Event.
	 * @author YanLu
	 *
	 */
	public enum Type{
		/**
		 * if Event object is SetLevel.
		 */
		SetLevel,  
		
		/**
		 * if Event object is Move of Chap.
		 */
		ChapMove,   
		
		/**
		 * if Event object is Move of Bug.
		 */
		BugMove
	}
	
	private SingleMove move;
	
	private int level;

	private int bugId;

	private Event() {
	}
	
	/**
	 * Constructor of Event.
	 * @param type of the Event
	 * @param level of the Event
	 * @param move of the Event
	 * @param chapDies of the Event
	 */
	public Event(Type type, int level, SingleMove move, int bugId) {
		this();
		this.type = type;
		this.level = level;
		this.move = move;
		this.bugId = bugId;
	}  
	
    /**
     * Construct event when set level occurs.
     * @param level of user selection
     * @return event of SetLevel
     */
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, 0);
    }
	
    /**
     * Construct event when move of chap occurs.
     * @param move object created by user input
     * @return event of ChapMove
     */
    public static Event eventOfChapMove(SingleMove move) {
    	return new Event(Type.ChapMove, 0, move, 0);   	
    }
    
    /**
    * Construct event when a move of bug occurs.
     * @param move object created by computing
     * @return event of BugMove
     */
    public static Event eventOfBugMove(SingleMove move, int bugId) {
    	return new Event(Type.BugMove, 0, move, bugId);   	
    }

    
    /**
	 * Get the current level.
	 * @return current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set level with given information.
	 * @param a given level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Get the current level.
	 * @return current type information
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Set type with given information.
	 * @param a given type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Get the current Move object.
	 * @return current move
	 */
	public SingleMove getMove() {
		return this.move;
	}
	
	/**
	 * Set move with given information.
	 * @param a given move object
	 */
	public void setMove(SingleMove move) {
		this.move = move;
	}
	
	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}
}