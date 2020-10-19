package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventListener;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.RecordedGame;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.test.TestMove.TestDirection;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event.Type;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.EventIterator;

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
		System.out.println("Test game saved to " + filepath);
		EventIterator it = EventListener.getRecord().getIteratorByFile(filepath);
		System.out.println("Starting event iteration with latency: " + it.getLatency());
		while(it.hasNext()) {
			Event e = it.next();
			System.out.println("Iterator emits event: " + e.getType());
		}
		
//		ObjectMapper mapper = new ObjectMapper();
//
//		mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
//		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		mapper.setVisibility(PropertyAccessor.CREATOR, Visibility.ANY);
//
//		try {
//			String str = "{\"level\":\"2\", \"actions\":[]}";
//			Simplest sp = mapper.readValue(str, Simplest.class);
//			sp.updateActions(new Action(new TestMove(TestDirection.UP)));
//			sp.updateActions(new Action(new TestMove(TestDirection.DOWN)));
//
//			String sp1 = mapper.writeValueAsString(sp);
//            String sp2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sp);
//            System.out.println(sp1);
//            System.out.println(sp2);
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}