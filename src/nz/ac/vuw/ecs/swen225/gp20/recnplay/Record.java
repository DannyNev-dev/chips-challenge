package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileNotFoundException;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Create JSon object for recording current game.
 * save current game to JSon file.
 * @author YanLu
 *
 */
public class Record{

	private RecordedGame recordedGame;

	/**
	 * constructor of Record.
	 * Use level -1 by default, when level is set, the event listener will update level to user choice.
	 */
	public Record() {
		this.recordedGame = new RecordedGame(-1);
	}
	
	/**
	 * update current record when any new action performed and a matching event created.
	 * @param event
	 */
	public void update(Event event) {
		recordedGame.addAction(event);
	}
	
	/**
	 * Calculate filename to save, if preferred folder is specified, it shall be passed from Application in initializer.
	 */
	private String getSaveFileName() {	
		String fileName = new SimpleDateFormat("dd_MM_yyyy_HH-mm").format(new Date());
		return "Level" + recordedGame.level + "_" + fileName + ".json";
	}

	/**
	 * this method is open for Application module to call when save file dialog gets file path(name).
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
}
