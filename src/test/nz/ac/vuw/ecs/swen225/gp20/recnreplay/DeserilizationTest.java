package test.nz.ac.vuw.ecs.swen225.gp20.recnreplay;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.RecordedGame;

public class DeserilizationTest {

	@test
	public void deserilizationTest() {
		EventListener listener = EventListener.eventListenerFactory();
		// Pad mock events
		listener.onEvent(Event.eventOfLevelSetting(2));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.UP)));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.DOWN)));
		// Save to JSON
		String filepath = listener.getRecord().saveToJson();
		// Load the JSON
		System.out.println("Test game saved to " + filepath);
		RecordedGame rg = listener.getRecord().loadRecordedGame(filepath);
		System.out.println("Recorded Game is in level: " + rg.getLevel());
	}
}
