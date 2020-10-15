package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Read and deserialize saved JSon file to list of action objects
 * pass the list of action objects to Application for deploy.
 * @author YanLu
 *
 */
public class Replay {
	//private JsonObject level;
	//private JsonArray steps;
	private RecordedGame recordedGame;
	
	public RecordedGame getRecordedGame() {
		return recordedGame;
	}


	public void setRecordedGame(RecordedGame recordedGame) {
		this.recordedGame = recordedGame;
	}

	/**
	 * constructor of Replay.
	 * @param gameRecord
	 */
	public Replay(String filepath) {
//		try {
//			InputStream in = new FileInputStream(gameRecord);
//			JsonReader reader = Json.createReader(in);
//            JsonObject gameRecordObj = reader.readObject();
//            reader.close();
//            level = gameRecordObj.getJsonObject("level");
//            steps = gameRecordObj.getJsonArray("steps");			
//		}
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
		this.setRecordedGame(this.loadRecordedGame(filepath));
	}
	
	
	/**
	 * this method shall be called by Application when file loading dialog gets file path(name).
	 * @param filename
	 * @return a recorded game with a given file name
	 */
	public RecordedGame loadRecordedGame(String filepath) {		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		// JSon file to Java object
		// load the file and deserialize it to a new RecordedGame object, return it.
		try {
			return mapper.readValue(new File(filepath), RecordedGame.class);
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
}
