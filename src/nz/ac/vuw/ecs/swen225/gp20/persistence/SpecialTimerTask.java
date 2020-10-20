package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.util.TimerTask;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.persistence.level2.BugEntity;

public class SpecialTimerTask extends TimerTask{
	
	BugEntity bug;
	Maze m;
	
	public SpecialTimerTask(BugEntity bug,Maze m){
		this.bug = bug;
		this.m = m;
	}
	
	@Override
	public void run() {
		Board b = m.getBoardObject();
		SingleMove sm = bug.randomAdjacentPos(b);
		m.moveEntity(sm,bug); //returns false if game is paused
		//call static application method to notify record and replay
		//GUIWindow.notifyRecorder(sm,name); //name to identify this as bug move
		
	}

}
