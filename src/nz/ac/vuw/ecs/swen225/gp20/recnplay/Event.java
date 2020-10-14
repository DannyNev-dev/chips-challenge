package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
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
	 * list all types of Event.
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
	

	/**
	 * get the current level.
	 * @return current level
	 */
	public int getLevel() {
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
	 * get the current level.
	 * @return current type information
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * set type with given information.
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * get the current Move object.
	 * @return current move
	 */
	public SingleMove getMove() {
		return this.move;
	}
	
	/**
	 * set move with given information.
	 * @param move
	 */
	public void setMove(SingleMove move) {
		this.move = move;
	}
	
	/**
	 * constructor of Event.
	 * @param type
	 * @param level
	 * @param move
	 * @param chapDies
	 */
	public Event(Type type, int level, SingleMove move, boolean chapDies) {
		this.type = type;
		this.level = level;
		this.move = move;
		this.chapDies = chapDies;
	}  
	
    /**
     * construct event when set level occurs.
     * @param level
     * @return event of SetLevel
     */
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, false);
    }
	
    /**
     * construct event when move of chap occurs.
     * @param move
     * @return event of ChapMove
     */
    public static Event eventOfChapMove(SingleMove move) {
    	return new Event(Type.ChapMove, 0, move, false);   	
    }
    
    /**
    * construct event when a move of bug occurs.
     * @param move
     * @return event of BugMove
     */
    public static Event eventOfBugMove(SingleMove move) {
    	return new Event(Type.BugMove, 0, move, false);   	
    }
    
    /**
     * construct event when chap dies.
     * @param chapDies
     * @return event of ChapDies
     */
    public static Event eventOfChapDies(boolean chapDies) {
    	return new Event(Type.ChapDies, 0, null, true);   	
    } 
}
