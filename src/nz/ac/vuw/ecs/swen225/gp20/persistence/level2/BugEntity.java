package nz.ac.vuw.ecs.swen225.gp20.persistence.level2;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.application.GuiWindow;
import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;

/**
 * Create a bug entity. Implements collectable as well so that it is required to
 * be able to affect the player when they try to pick it up, in this case making
 * the player die
 * Also implements the property listener so that it can add itself into the application 
 * to be updated using the observer pattern
 *
 * @author Daniel Neville
 *
 */
public class BugEntity implements Entity, Collectable, PropertyChangeListener {

	/** The Constant bug name. */
	// All bugs share the same name, stored to improve efficiency
	private static final String name = "bug";

	/** The id. */
	public int ID;
	/** The position. */
	private Point position;

	/** The paused. */
	public boolean paused = false;

	/** ref to the application *. */
	public GuiWindow gw;

	/** ref to the maze *. */
	private Maze m;

	/**
	 * Instantiates a new bug entity.
	 *
	 * @param p  the p
	 * @param id the id
	 */
	public BugEntity(Point p, int id) {
		this.position = p;
		this.ID = id;
	}

	/**
	 * Sets the maze ref in bug.
	 *
	 * @param m the maze
	 */
	public void setMaze(Maze m) {
		this.m = m;
	}

	/**
	 * Return a random adjacent position (coordinates of a tile in the board). The
	 * result will be accessible for the bug. Change to the board are not applied
	 * here
	 * 
	 * @param board state of the board used to decide which the new position will be
	 * @return an accessible random adjacent position
	 */
	public SingleMove randomAdjacentPos(Board board) {
		Point newPos;
		SingleMove sm=null;
		int retries = 0;
		boolean accessible = false;
		// (1/4)^5 ~= 0.001, if in worst case it hits the 0.1%, emit a to-be-rejected move and wait for next round
		while(retries <=5 && !accessible) {
			// Get a random direction for the move
			sm = SingleMove.createRandomlyMove();
			newPos = sm.getDestination(position);
			// Check if the bug can access it
			accessible = board.getTile(newPos).isAccessible(this);
			retries++;
		}
		if (sm==null) {
			System.out.println("randomAdjacentPos: failed to pop valid random move in time, return a move to be rejected in this round");
		}
		return sm;
	}
	
	
	/**
     * Gets the file.
     *
     * @param prefix the prefix for the image
     * @return an image 
     */
	@Override
    public String getFile(String prefix) {
        return "src/nz/ac/vuw/ecs/swen225/gp20/persistence/level2/BugImages/" + prefix + ".png";
    }
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the entity.
	 *
	 * @return this entity
	 */
	@Override
    public Entity getEntity() {
        return this;
    }
	
	/**
	 * Gets the inventory if i had one.
	 *
	 * @return the inventory that doesn't exist..
	 */
	@Override
	public List<Collectable> getInventory() {
		return null; // indicates this does not have an inventory
	}

	/**
	 * Bugs don't collect chips.
	 *
	 * @return the chips collected if it had any
	 */
	@Override
	public int getChipsCollected() {
		throw new RuntimeException("Unsupported method, bugs can't collect chips");
	}

	/**
	 * Gets the position of the bug.
	 *
	 * @return the position
	 */
	@Override
	public Point getPosition() {
		return position;
	}

	/**
	 * Sets the position of the bug.
	 *
	 * @param newCoordinates the new position
	 */
	@Override
	public void setPosition(Point newCoordinates) {
		// if a invalid position is passed here it could cause bugs and unexpected
		// behavour
		position = newCoordinates;
	}

	/**
	 * Apply action. Returns a special event that kills chap
	 *
	 * @param entity entity thats triggering the action
	 * @return the special event to kill chap
	 */
	@Override
	public SpecialEvent applyAction(Entity entity) {
		checkNotNull(entity, "An entity needs to be initialized before doing an action");
		if (entity instanceof Player) {
			return SpecialEvent.CHAP_DIED_MURDERED;
		}
		return null;
	}

	/**
	 * Pickup.
	 *
	 * @param player the player
	 * @return false as you cannot pickup the bug
	 */
	public boolean pickup(Player player) {
		return false;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (paused ? 1231 : 1237);
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/**
	 * Checks if is accessible.
	 *
	 * @param entity the entity
	 * @return true, if is accessible
	 */
	@Override
	public boolean isAccessible(Entity entity) {
		// Other entity can move into the player's tile to kill them
		return true;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BugEntity other = (BugEntity) obj;
		if (paused != other.paused)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	/**
	 * Checks for action.
	 *
	 * @return true, as it has an action
	 */
	@Override
	public boolean hasAction() {
		return true;
	}

	/**
	 * Property change, this is called when GUI notifies the listeners Finds a
	 * random single move accessible to the bug and executes that move on the bug
	 * then calls applications notify method to record the entity move.
	 *
	 * @param evt the event passed by the event creator
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!this.gw.inRunMode()) {
			return;
		}
		
		Board b = m.getBoardObject();
		SingleMove sm = this.randomAdjacentPos(b);
		// moveEntity() returns false if game is paused
		if (m.moveEntity(sm, this))
		{
			// call static application method to notify record and replay
			gw.notifyRecord(sm, this.ID);
		}else {
			System.err.println("PropertyChange: The random move is rejected");
		}
	}

	/**
	 * Move the bug using the given SingleMove.
	 *
	 * @param sm the the single move you desire the bug to execute
	 */
	public boolean moveBug(SingleMove sm) {
		return m.moveEntity(sm, this);
	}

	/**
	 * Sets the application.
	 *
	 * @param gw the new application
	 */
	public void setApplication(GuiWindow gw) {
		this.gw = gw;
		gw.addPropertyChangeListener(this);
	}
}
