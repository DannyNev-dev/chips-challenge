package nz.ac.vuw.ecs.swen225.gp20.recnplay;

/**
 * Implement the Action interface
 * contains run method which will be called by Application module
 * when user clicking "Load" button. 
 * @author Yan Lu
 */
public class ActionLoad implements Action{

	@Override
	public void run() {
		load();
	}

	private void load() {
		
	}

	@Override
	public void run(Event e) {		
	}

}
