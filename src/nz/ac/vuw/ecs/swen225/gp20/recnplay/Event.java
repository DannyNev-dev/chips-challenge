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
		
		Move,  // if Event object is Move
		
		PickUpKey,  // if Event object is PickUpKey
		
		PickUpChip;	  // if Event object is PickUpChip
	}
	
	private SingleMove move;
	
	private int level;
	
	private boolean pickupKey;
	
	private boolean pickupChip;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isPickupKey() {
		return pickupKey;
	}

	public void setPickupKey(boolean pickupKey) {
		this.pickupKey = pickupKey;
	}

	public boolean isPickupChip() {
		return pickupChip;
	}

	public void setPickupChip(boolean pickupChip) {
		this.pickupChip = pickupChip;
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

	public Event(Type type, int level, SingleMove move, boolean pickupKey, boolean pickupChip) {
		this.type = type;
		this.level = level;
		this.move = move;
		this.pickupKey = pickupKey;
		this.pickupChip = pickupChip;
	}  
	
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, false, false);
    }
	
    public static Event eventOfMove(SingleMove move) {  
    	return new Event(Type.Move, 0, move, false, false);
    	
    }
    
    public static Event eventOfPickUpKey(boolean pickUpKey) { 
    	return new Event(Type.PickUpKey, 0, null, true, false);
    }
    
    public static Event eventOfPickUpChip(boolean pickUpChip) {
    	return new Event(Type.PickUpChip, 0, null, false, true);  
    }
}