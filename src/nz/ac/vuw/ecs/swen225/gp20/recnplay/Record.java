package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Create JSon object for recording current game.
 * save current game to JSon file.
 * @author YanLu (300520177)
 *
 */
public class Record{

	private RecordedGame recordedGame;

	/**
	 * Constructor of Record.
	 * Use level -1 by default, when level is set, the event listener will update level to user choice.
	 */
	public Record() {
		this.recordedGame = new RecordedGame(-1);
	}
	
	/**
	 * Update current record when any new action performed and a matching event created.
	 * @param event happened when game is playing
	 */
	public void update(Event event) {
		recordedGame.addAction(event);
	}
	
	/**
	 * Calculate filename to save, if preferred folder is specified, it shall be passed from Application in initializer.
	 * @return the name of saved JSon file.
	 */
	public String getSaveFileName() {	
		String fileName = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss").format(new Date());
		return "Level" + recordedGame.level + "_" + fileName + ".json";
	}

	/**
	 * This method is open for Application module to call when save file dialog gets file path(name).
	 * @return the name of saved JSon file.
	 */
	public String saveToJson() {				
		ObjectMapper mapper = new ObjectMapper();
		// convert Java objects to JSON file
		// call getSaveFileName to dump recordedGame into file
		String filepath = getSaveFileName();
        try {       
            mapper.writeValue(new File(filepath), this.recordedGame);
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.recordedGame);
            System.out.println(jsonString); 
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return filepath;
	}
	
	
	private RecordedGame loadRecordedGame(String filepath) {		
		ObjectMapper mapper = new ObjectMapper();

		mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.setVisibility(PropertyAccessor.CREATOR, Visibility.ANY);
		// JSon file to Java object
		// load the file and deserialize it to a new RecordedGame object, return it.
		try {
			RecordedGame rg = mapper.readValue(new File(filepath), RecordedGame.class);
			String rg1 = mapper.writeValueAsString(rg);
			String rg2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rg);
			System.out.println(rg1);
			System.out.println(rg2);
			return rg;
		}
		catch(FileNotFoundException e) {    
			e.printStackTrace();
			System.out.println("File can not be found.");
			return null;
		} 
		catch (JsonParseException | JsonMappingException e) {
			e.printStackTrace();
			System.out.println("File can not be parsed.");
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unknwon error happened");
			return null;
		}
	}

	/**
	 * Constructor of EventIterator.
	 * @param filepath of saved JSon file
	 * @param speed of replay
	 * @return EventIterator instance
	 */
	public EventIterator getIteratorByFile(String filepath, int speed) {
		return new EventIterator(loadRecordedGame(filepath), speed);	
	}
	
	/**
	 * Constructor of EventIterator.
	 * @param filepath of saved JSon file
	 * @return EventIterator instance
	 */
	public EventIterator getIteratorByFile(String filepath) {
		return new EventIterator(loadRecordedGame(filepath));	
	}
}