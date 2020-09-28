package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * The record adds functionality to record each step in a game play
 * and stores the recorded game in a file (in JSON format). 
 * @author Yan Lu
 *
 */
public class Record {

	private JsonObject level;
	private JsonArrayBuilder steps;
		
	public Record(JsonObject level){
		this.level = level;
		steps = (JsonArrayBuilder) Json.createArrayBuilder().build();
	}
	
	public void addActions(JsonObjectBuilder action) {
		steps.add(action);
	}
		
	public void save() {
		JsonObject gameRecord = Json.createObjectBuilder()
				.add("Level", level)
				.add("Steps", steps.build())
				.build();
		try {
			FileWriter recordedFile = new FileWriter("levels//level"+".json");
			recordedFile.write(gameRecord.toString());
		}
		catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
