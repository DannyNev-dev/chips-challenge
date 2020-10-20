package nz.ac.vuw.ecs.swen225.gp20.persistence.level2;

import java.util.TimerTask;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;

/**
 * The Class SpecialTimerTask.
 * 
 * @author Daniel Neville
 */
public class SpecialTimerTask extends TimerTask{
	
	/** The bug. */
	BugEntity bug;
	
	/** The m. */
	Maze m;
	
	/**
	 * Instantiates a new special timer task.
	 *
	 * @param bug the bug object
	 * @param m the map object
	 */
	public SpecialTimerTask(BugEntity bug,Maze m){
		this.bug = bug;
		this.m = m;
	}
	
	/**
	 * Run method that contains the logic to find and execute a random single move on the bug
	 */
	@Override
	public void run() {
		Board b = m.getBoardObject();
		SingleMove sm = bug.randomAdjacentPos(b);
		m.moveEntity(sm,bug); //returns false if game is paused
		//call static application method to notify record and replay
		//GUIWindow.notifyRecorder(sm,name); //name to identify this as bug move
		
	}

}
