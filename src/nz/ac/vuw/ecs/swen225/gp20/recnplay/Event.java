package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;

/**
 * A class which will be instantiated into specific instance 
 * and will be stored in game history for future replay use
 * such as Move object which is created in Application module.
 * @ Yan Lu
 */
public class Event {
	
	private Type type;
	
	public enum Type{
		SetLevel, // if Event object is SetLevel
		
		ChapMove,  // if Event object is Move of Chap
		
		BugMove,  // if Event object is Move of Bug
		
		ChapDies;  // if Event object is ChapDies
	}
	
	private SingleMove move;
	
	private int level;
	
	private boolean chapDies;
	

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setMove(SingleMove move) {
		this.move = move;
	}

	public Type getType() {
		return type;
	}
	
	public SingleMove getMove() {
		return this.move;
	}
	
	
	public Event(Type type, int level, SingleMove move, boolean chapDies) {
		this.type = type;
		this.level = level;
		this.move = move;
		this.chapDies = chapDies;
	}  
	
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, false);
    }
	
    public static Event eventOfChapMove(SingleMove move) {
    	return new Event(Type.ChapMove, 0, move, false);   	
    }
    
    public static Event eventOfBugMoveDies(SingleMove move) {
    	return new Event(Type.BugMove, 0, move, false);   	
    }
    
    public static Event eventOfChapDies(boolean chapDies) {
    	return new Event(Type.ChapDies, 0, null, true);   	
    } 
}