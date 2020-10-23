package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.Iterator;
import java.sql.Timestamp;

/**
 * Implements Iterator.
 * Pop up saved java objects to GUIWIndow in replay mode.
 * @author YanLu (300520177)
 *
 */
public class EventIterator implements Iterator<Event>{
	
	private static int MAX_SPEED = 10;
	private static int DEFAULT_SPEED = 1;

	
	private RecordedGame rg;

	private int minDelay = 200;
	private int speed;
	
	/**
	 * Set replay speed with a given parameter.
	 * @param speed given speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	private Iterator<Event> it;
	
	/**
	 * Return latency for Swing Timer using.
	 * @return latency which is calculated with selected speed.
	 */
	public int getLatency() {
		// Highest speed: delay = minDelay = 200 (ms)
		// Lowest speed: 3800 + minDelay = 4000 (ms)
		return (int) ((MAX_SPEED - speed) / 0.00237 + minDelay);
	}


	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Event next() {
		Event ev = it.next();
		System.out.println(
				"EventIterator: " + new Timestamp(System.currentTimeMillis()) + " emit event: " + ev.getType());
		return ev;	
	}

	/**
	 * Constructor of EventIterator.
	 * @param rg saved RecordedGame object
	 * @param speed replay speed
	 */
	public EventIterator(RecordedGame rg, int speed) {
		super();
		this.rg = rg;
		this.speed = speed;
		this.it = rg.actions.iterator();
	}
	
	/**
	 * Getter.
	 * @return a saved RecordedGame object
	 */
	public RecordedGame getRg() {
		return rg;
	}

	/**
	 * Constructor of EventIterator.
	 * @param recordedGame saved RecordedGame object
	 */
	public EventIterator(RecordedGame recordedGame) {
		this(recordedGame, EventIterator.DEFAULT_SPEED);
	}
}