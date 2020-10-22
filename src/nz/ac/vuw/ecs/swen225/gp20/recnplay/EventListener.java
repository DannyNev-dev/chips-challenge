package nz.ac.vuw.ecs.swen225.gp20.recnplay;

/**
 * Contains onEvent method which will be called by Application module.
 * when a new event occurs(set level, move, pick up key, pick up chip etc.). 
 * @author YanLu
 */
public class EventListener implements Plugin {

	private static Record record;
	
	//private static Replay replay;
	/**
	 * A static method to get current record instance
	 * can be called for all project.
	 * @return Record instance
	 */
	public static Record getRecord() {
		return record;
	}

	@Override
	public void onEvent(Event e) {
		// All event listener instances update the same record instance
		
		EventListener.record.update(e);
	}
	
	/**
	 * Constructor of EventListener.
	 * @return EventListener instance
	 */
	public static EventListener eventListenerFactory() {
		if (record == null) {
			record = new Record();
		}
		return new EventListener();
	}
}