package nz.ac.vuw.ecs.swen225.gp20.persistence.level2;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.application.GUIWindow;
import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;

// TODO: Auto-generated Javadoc
/**
 * Create a bug entity. Implements collectable as well so that it is required to
 * be able to affect the player when they try to pick it up, in this case making
 * the player die
 *
 * @author Daniel Neville
 *
 */
public class BugEntity implements Entity, Collectable, PropertyChangeListener {

	/** The Constant name. */
	// All bugs share the same name, stored to improve efficiency
	private static final String name = "bug";

	/** The id. */
	public int ID;
	/** The position. */
	private Point position;

	/** The paused. */
	public boolean paused = false;

	/**  ref to the application *. */
	public GUIWindow gw;

	/**  ref to the maze *. */
	private Maze m;

	/**
	 * Instantiates a new bug entity.
	 *
	 * @param p the p
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
		// Get a random direction for the move
		SingleMove sm = SingleMove.createRandomlyMove();
		newPos = sm.getDestination(position);
		// Check if the bug can access it
		if (!board.getTile(newPos).isAccessible(this)) {
			randomAdjacentPos(board);
		}
		return sm;
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
	 * Gets the position.
	 *
	 * @return the position
	 */
	@Override
	public Point getPosition() {
		return position;
	}

	/**
	 * Sets the position.
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
	 * Apply action.
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
	 * Property change.
	 *
	 * @param evt the evt
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Board b = m.getBoardObject();
		SingleMove sm = this.randomAdjacentPos(b);
		m.moveEntity(sm, this); // returns false if game is paused
		// call static application method to notify record and replay
		// GUIWindow.notifyRecorder(sm,name); //name to identify this as bug move
		gw.notifyRecord(sm, this.ID);
	}
	
	/**
	 * Move the bug using the given SingleMove.
	 *
	 * @param sm the sm
	 */
	public void moveBug(SingleMove sm) {
		m.moveEntity(sm, this);
	}
	
	/**
	 * Sets the application.
	 *
	 * @param gw the new application
	 */
	public void setApplication(GUIWindow gw) {
		this.gw = gw;
		gw.addPropertyChangeListener(this);
	}
}
