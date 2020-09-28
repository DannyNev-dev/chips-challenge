package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Replay {
	private JsonObject level;
	private JsonArray steps;
	
	public Replay(File gameRecord) {
		try {
			InputStream in = new FileInputStream(gameRecord);
			JsonReader reader = Json.createReader(in);
            JsonObject gameRecordObj = reader.readObject();
            reader.close();
            level = gameRecordObj.getJsonObject("level");
            steps = gameRecordObj.getJsonArray("steps");			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
