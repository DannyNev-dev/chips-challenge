package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Create Json object for recording current game
 * save current game to Json file.
 * @author Yan Lu
 *
 */
public class Record{

	private RecordedGame recordedGame;

	public Record() {
		// Use level -1 by default, when level is set, the event listener will update level to user choice.
		this.recordedGame = new RecordedGame(-1);
	}
	
	public void update(Event event) {
		recordedGame.addAction(event);
	}
	
	private String getSaveFileName() {	
		String fileName = new SimpleDateFormat("dd_MM_yyyy_HH-mm").format(new Date());
		return "Level" + recordedGame.level + "_" + fileName + ".json";
		// Calculate filename to save, if preferred folder is specified, it shall be passed from App in initializer
	}

	// this method is open for app to call when save file dialog gets filepathI(name)
	public void saveToJson() {
		// call getSaveFileName to dump recordedGame into file		
		ObjectMapper mapper = new ObjectMapper();

        try {
            // Java objects to JSON file
            mapper.writeValue(new File(getSaveFileName()), this.recordedGame);
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.recordedGame);
            System.out.println(jsonString);
            
        } catch (IOException e) {
        	e.printStackTrace();
        }       
	}
	
	
	// this method shall be called by Application when file loading dialog gets filepath(name).
	private RecordedGame loadGame(String filename) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			// Json file to Java object
			
			RecordedGame rg = mapper.readValue(new File(filename), RecordedGame.class);
			this.recordedGame = rg;
		}
		catch(IOException e) {    // will be add more specific exception cases
			e.printStackTrace();
			return null;
		}
		// load the file and deserialize it to a new RecordedGame object, return it.
		return this.recordedGame;
	}
	
}