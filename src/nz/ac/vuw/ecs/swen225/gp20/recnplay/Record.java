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
	
	//private FileWriter jsonFile;
	

	public Record(int level){  // create a new record with empty recordedGame
		this.recordedGame = new RecordedGame(level);
	}
	
	
	public Record(RecordedGame rg) {
		this.recordedGame = rg;
		
	}
	
	public void updateSteps(Event event) {
		recordedGame.steps.add(event);
	}
	
	private String getSaveFileName() {	
		//DateFormat df = new SimpleDateFormat("dd-MM-yy");
		//Date dateobj = new Date();
		//System.out.println(df.format(dateobj));
		return "Level" + recordedGame.level + ".json";
		// Calculate filename to save, if preferred folder is specified, it shall be passed from App in initializer
	}

	// this method is open for app to call when save file dialog gets filepathI(name)
	public void saveToJson() {
		// call getSaveFileName to dump recordedGame into file		
		ObjectMapper mapper = new ObjectMapper();

        try {
            // Java objects to JSON file
            mapper.writeValue(new File(getSaveFileName()), this.recordedGame);

            // Java objects to JSON string - compact-print
            // String jsonString = mapper.writeValueAsString(this.recordedGame);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.recordedGame);

            System.out.println(jsonInString2);

        } catch (IOException e) {
        	e.printStackTrace();
        }       
	}
	
	
	// this method shall be called by Application when file loading dialog gets filepath(name).
	private RecordedGame loadGame(String filename) {
		// load the file and deserialize it to a new RecordedGame object, return it.
		return null;
	}
}