package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.Event.Type;

public class TestRecord {

	public static void main(String[] args) {
		
		RecordedGame recordedGame = createRecordedGame();
		new Record(recordedGame).saveToJson();
	}
	
	
	private static RecordedGame createRecordedGame() {

        RecordedGame rg = new RecordedGame(1);
        Event event1 = Event.eventOfLevelSetting(1);
        Event event2 = Event.eventOfMove(new SingleMove(Direction.DOWN));
        Event event3 = Event.eventOfMove(new SingleMove(Direction.LEFT));
        Event event4 = Event.eventOfMove(new SingleMove(Direction.DOWN));
        Event event5 = Event.eventOfMove(new SingleMove(Direction.RIGHT));
//        Event event2 = new Event(Type.Move, 1, new SingleMove(Direction.DOWN), false, false);
//        Event event3 = new Event(Type.Move, 1, new SingleMove(Direction.LEFT), false, false);
//        Event event4 = new Event(Type.Move, 1, new SingleMove(Direction.DOWN), false, false);
//        Event event5 = new Event(Type.Move, 1, new SingleMove(Direction.RIGHT), false, false);
//        Event event6 = new Event(Type.Move, 1, new SingleMove(Direction.UP), false, false);
        rg.add(event1);
        rg.add(event2);
        rg.add(event2);
        rg.add(event3);
        rg.add(event4);
        rg.add(event5);
//        rg.add(event6);
        
        
        return rg;

    }

}
