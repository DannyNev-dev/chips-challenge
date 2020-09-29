package nz.ac.vuw.ecs.swen225.gp20.recnplay;

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
	
	private SingleMove movement;
	
	private int level;
	
	private boolean pickupKey;
	
	private boolean pickupChip;

	public Type getType() {
		return type;
	}
	        
	public Event(Type type, int level, SingleMove move, boolean pickupKey, boolean pickupChip) {}  
	
    public static Event eventOfLevelSetting(int level) {
    	return new Event(Type.SetLevel, level, null, false, false);
    }
	
    public static Event eventOfMove(SingleMove move) {  
    	return new Event(Type.Move, 1, move, false, false);
    	
    }
    
    public static Event eventOfPickUpKey(boolean pickUpKey) { 
    	return new Event(Type.PickUpKey, 1, null, true, false);
    }
    
    public static Event eventOfPickUpChip(boolean pickUpChip) {
    	return new Event(Type.PickUpChip, 1, null, false, true);  
    }
}
