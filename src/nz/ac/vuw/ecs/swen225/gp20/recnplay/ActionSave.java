package nz.ac.vuw.ecs.swen225.gp20.recnplay;


/**
 * Implement the Action interface
 * contains run method which will be called by Application module
 * when user clicking "Save" button. 
 * @author Yan Lu
 */
public class ActionSave implements Action {

	@Override
	public void run(Event e) {
		save(e);
	}

	private void save(Event e) {
		
	}

	@Override
	public void run() {
	}

}
