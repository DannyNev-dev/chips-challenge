package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.util.ArrayList;

public class Simplest {

    private int level;
    private ArrayList<Action> actions;

    private Simplest() {
    }

    public Simplest(int level) {
        this();
        this.level = level;
        this.actions = new ArrayList<Action>();
    }

    public int getLevel() {
        return level;
    }
    
    public ArrayList<Action> getActions(){
    	return actions;
    }
    
    public void updateActions(Action action) {
    	actions.add(action);
    }
}
