package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.util.ArrayList;

/**
 * A mock class for test usage.
 * Similar to RecordedGame class.
 * @author YanLu
 *
 */
public class TestRecordedGame {

    private int level;
    private ArrayList<TestEvent> tEvents;

    private TestRecordedGame() {
    }

    /**
     * Constructor of Simplest.
     * @param level of the game which to be saved
     */
    public TestRecordedGame(int level) {
        this();
        this.level = level;
        this.tEvents = new ArrayList<TestEvent>();
    }

    /**
     * Getter.
     * @return level of the game which to be saved
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Getter.
     * @return list of TestEvent objects
     */
    public ArrayList<TestEvent> getActions(){
    	return tEvents;
    }
    
    /**
     * Update events.
     * @param event created
     */
    public void updateActions(TestEvent tEvent) {
    	tEvents.add(tEvent);
    }
}