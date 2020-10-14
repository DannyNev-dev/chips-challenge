package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.RecordedGame;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Replay;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event.Type;

/**
 * test save function.
 * @author YanLu
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
		RecordedGame rg = new Replay(filepath).getRecordedGame();
		System.out.println(rg.getLevel());
		System.out.println(rg.getActions());
	}
}
