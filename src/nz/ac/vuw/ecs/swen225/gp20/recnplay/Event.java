package nz.ac.vuw.ecs.swen225.gp20.recnplay;


/**
 * A super class which will be extended and instantiated into specific instance 
 * and will be stored in game history for future replay use
 * such as Move object which is created in Application module.
 * @ Yan Lu
 */
public class Event {
	
	private Type type;
	
	public enum Type{
		Move,  // if Event object is Move
		
		PickUpKey,  // if Event object is PickUpKey
		
		PickUpChip;	  // if Event object is PickUpChip
	}

	public Type getType() {
		return type;
	}
	                     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private String type;
	private Map<String, String> property = new HashMap<String, String>();
	
	
	public Event(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Map<String, String> getProperty(){
		return this.property;
	}
*/
}
