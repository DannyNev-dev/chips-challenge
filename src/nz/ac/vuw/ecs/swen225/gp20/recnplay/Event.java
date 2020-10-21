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
		BugMove,   
		
		/**
		 * if Event object is ChapDies.
		 */
		ChapDies;  
	}
	
	private SingleMove move;
	
	private int level;
	
	private boolean chapDies;
	
	private Event() {
	}
	
	/**
	 * Constructor of Event.
	 * @param type
	 * @param level
	 * @param move
	 * @param chapDies
	 */
	public Event(Type type, int level, SingleMove move, boolean chapDies) {
		this();
		this.type = type;
		this.level = level;
		this.move = move;
		this.chapDies = chapDies;
	}  
	
    /**
     * Construct event when set level occurs.
     * @param level
     * @return event of SetLevel
     */
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, false);
    }
	
    /**
     * Construct event when move of chap occurs.
     * @param move
     * @return event of ChapMove
     */
    public static Event eventOfChapMove(SingleMove move) {
    	return new Event(Type.ChapMove, 0, move, false);   	
    }
    
    /**
    * Construct event when a move of bug occurs.
     * @param move
     * @return event of BugMove
     */
    public static Event eventOfBugMove(SingleMove move) {
    	return new Event(Type.BugMove, 0, move, false);   	
    }
    
    /**
     * Construct event when chap dies.
     * @param chapDies
     * @return event of ChapDies
     */
    public static Event eventOfChapDies(boolean chapDies) {
    	return new Event(Type.ChapDies, 0, null, true);   	
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
	 * @param level
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
	 * @param type
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
	 * @param move
	 */
	public void setMove(SingleMove move) {
		this.move = move;
	}
	
	/**
	 * Get the current chapDies.
	 * @return current chapDies
	 */
	public boolean getChapDies() {
		return this.chapDies;
	}
	
	/**
	 * Set chapDies with given information.
	 * @param chapDies
	 */
	public void setChapDies(boolean chapDies) {
		this.chapDies = chapDies;
	}
}