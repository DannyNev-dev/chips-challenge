
package nz.ac.vuw.ecs.swen225.gp20.recnplay;

/**
 * An interface will be implemented by ActionSave and ActionLoad
 * which will be called by Application module when clicking button "Save"/"Load".
 * @author Yan Lu
 *
 */
public interface Plugin {
	/**
	 * a method will be called by Application module when actions performed.
	 * @param e
	 */
	void onEvent(Event e);
}
