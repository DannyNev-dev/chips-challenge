package test.nz.ac.vuw.ecs.swen225.gp20.recnreplay;

import java.io.IOException;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;

/**
 * Test record and replay functions.
 * @author YanLu
 *
 */
public class RecordTest {
	
	@test
	public void recordTest() {
		EventListener listener = EventListener.eventListenerFactory();
		// Pad mock events
		listener.onEvent(Event.eventOfLevelSetting(2));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.UP)));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.DOWN)));
		// Save to JSON
		String filepath = listener.getRecord().saveToJson();
		System.out.println("Test game saved to " + filepath);		
	}
}
