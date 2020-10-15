package nz.ac.vuw.ecs.swen225.gp20.recnplay.test;

import java.util.ArrayList;

public class Simplest {

    private int level;
    private ArrayList<String> actions;

    private Simplest() {
    }

    public Simplest(int level) {
        this();
        this.level = level;
        this.actions = new ArrayList<String>();
    }

    public int getLevel() {
        return level;
    }
    
    public ArrayList<String> getActions(){
    	return actions;
    }
    
    public void updateActions(String action) {
    	actions.add(action);
    }
}
