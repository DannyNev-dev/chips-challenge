package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventIterator;

/**
 * Test save, load, iterate functions.
 * @author YanLu (300520177)
 *
 */
public class TestRecord {
	/**
	 * create SetLevel and move object to test save function.
	 * @param args
	 */
	public static void main(String[] args) {	
		EventListener listener = EventListener.eventListenerFactory();
		// Pad mock events
		listener.onEvent(Event.eventOfLevelSetting(2));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.UP)));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.DOWN)));
		// Save to JSON
		String filepath = EventListener.getRecord().saveToJson();
		// Load the JSON
		System.out.println("Test game saved to " + filepath);
		EventIterator it = EventListener.getRecord().getIteratorByFile(filepath);
		System.out.println("Starting event iteration with latency: " + it.getLatency());
		System.out.println("Recorded game should have 1 setLevel(2) event and 2 ChapMove events");
		while(it.hasNext()) {
			Event e = it.next();
			System.out.println("Iterator emits event: " + e.getType());
		}
		
		// Now test the setLevel event arrival again
		listener.onEvent(Event.eventOfLevelSetting(1));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.LEFT)));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.RIGHT)));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.DOWN)));
		// Save to JSON
		String filepath2 = EventListener.getRecord().saveToJson();
		// Load the JSON
		System.out.println("Test game saved to " + filepath2);
		EventIterator it2 = EventListener.getRecord().getIteratorByFile(filepath2);
		System.out.println("Starting event iteration with latency: " + it2.getLatency());
		System.out.println("Recorded game should have 1 setLevel(1) event and 3ChapMove events");
		while(it2.hasNext()) {
			Event e = it2.next();
			System.out.println("Iterator emits event: " + e.getType());
		}
	}
}