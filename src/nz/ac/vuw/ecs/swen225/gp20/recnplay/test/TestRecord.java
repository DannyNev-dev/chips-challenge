package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.io.File;

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
//		EventListener listener = EventListener.eventListenerFactory();
//		// Pad mock events
//		listener.onEvent(Event.eventOfLevelSetting(2));
//		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.UP)));
//		listener.onEvent(Event.eventOfChapMove(new SingleMove(Direction.DOWN)));
//		// Save to JSON
//		String filepath = listener.getRecord().saveToJson();
//		// Load the JSON
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
//		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.setVisibility(PropertyAccessor.CREATOR, Visibility.ANY);

		
		
		//RecordedGame rg = null;
		try {
			String str = "{\"simplest\":\"test\"}";
			Simplest sp = mapper.readValue(str, Simplest.class);
			System.out.println(sp.getSimplest());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(rg.getActions());
	}
}
