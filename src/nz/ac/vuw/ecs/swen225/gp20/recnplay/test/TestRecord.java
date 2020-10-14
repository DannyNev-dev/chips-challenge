package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
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
		listener.onEvent(Event.eventOfLevelSetting(2));
		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.UP)));
		EventListener.getRecord().saveToJson();		
	}
}
