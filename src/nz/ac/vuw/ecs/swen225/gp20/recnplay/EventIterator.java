package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.util.Iterator;

public class EventIterator implements Iterator<Event>{
	
	private static int MAXSPEED = 10;
	private static int MINSPEED = 1;
	private static int DEFAULT_SPEED = 5;
	
	private RecordedGame rg;

	private double delayUnit = 200;
	private int speed;
	private Iterator<Event> it;
	
	public long getLatency() {
		return (long)((MAXSPEED-speed)/0.005 + delayUnit);
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Event next() {
		long latency = getLatency();
		try {
			Thread.sleep(latency);
			return it.next();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}		
	}

	public EventIterator(RecordedGame rg, int speed) {
		super();
		this.rg = rg;
		this.speed = speed;
		this.it = rg.actions.iterator();
		System.out.println("EventIterator created with latency: " + getLatency());
	}
	
	public RecordedGame getRg() {
		return rg;
	}

	public EventIterator(RecordedGame recordedGame) {
		this(recordedGame, EventIterator.DEFAULT_SPEED);
	}
	

}
