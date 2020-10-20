package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.util.ArrayList;

/**
 * A mock class for test usage.
 * @author YanLu
 *
 */
public class Simplest {

    private int level;
    private ArrayList<Action> actions;

    private Simplest() {
    }

    /**
     * Constructor of Simplest.
     * @param level
     */
    public Simplest(int level) {
        this();
        this.level = level;
        this.actions = new ArrayList<Action>();
    }

    /**
     * Getter.
     * @return level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Getter.
     * @return list of Action objects
     */
    public ArrayList<Action> getActions(){
    	return actions;
    }
    
    /**
     * Update actions.
     * @param action
     */
    public void updateActions(Action action) {
    	actions.add(action);
    }
}