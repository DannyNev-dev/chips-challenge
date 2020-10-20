package nz.ac.vuw.ecs.swen225.gp20.persistence.level2;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Point;
import java.util.List;


import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;

/**
 * Create a bug entity.
 * Implements collectable as well so that it is required to be able to affect the player
 * when they try to pick it up, in this case making the player die
 *
 * @author Daniel Neville
 *
 */
public class BugEntity implements Entity, Collectable {
  
  /** The Constant name. */
  //All bugs share the same name, stored to improve efficiency
  private static final String name = "bug";
  
  /** The position. */
  private Point position;
  
  /** The paused. */
  public boolean paused = false;
  
  /**
   * Instantiates a new bug entity.
   *
   * @param p the p
   */
  public BugEntity(Point p) {
	  this.position = p;
  }
  
  /**
   * Execute bug move.
   *
   * @param m the maze
   */
  public void executeBugMove(Maze m) {
	//initial delay to wait for board to be returned to maze
	//loop with delay to move bug
	Board b = m.getBoardObject();
	SingleMove sm = randomAdjacentPos(b);
	m.moveEntity(sm,this); //returns false if game is paused
	//call static application method to notify record and replay
  }
  
  /**
   * Return a random adjacent position (coordinates of a tile in the board). 
   * The result will be accessible for the bug.
   * Change to the board are not applied here
   * 
   * @param board state of the board used to decide which the new position will be
   * @return an accessible random adjacent position
   */
  public SingleMove randomAdjacentPos(Board board) {
    Point newPos;  
    //Get a random direction for the move
    SingleMove sm = SingleMove.createRandomlyMove();
    newPos = sm.getDestination(position);
    //Check if the bug can access it
    if(!board.getTile(newPos).isAccessible(this)) {randomAdjacentPos(board);}
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
   * Checks for colleced.
   *
   * @param item the item
   * @return true, if successful
   */
  public boolean hasColleced(Collectable item) {
    throw new RuntimeException("Unsupported method, bugs have no inventory");
  }

  /**
   * Gets the inventory.
   *
   * @return the inventory
   */
  @Override
  public List<Collectable> getInventory() {
    throw new RuntimeException("Unsupported method, bugs have no inventory");
  }

  /**
   * Gets the chips collected.
   *
   * @return the chips collected
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
    //if a invalid position is passed here it could cause bugs and unexpected behavour 
    position = newCoordinates;
  }
  
  /**
   * Apply action.
   *
   * @param entity the entity
   * @return the special event
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
   * @return true, if successful
   */
  public boolean pickup(Player player) {
    // TODO Auto-generated method stub
    return false;
  }

/**
 * Checks for action.
 *
 * @return true, if successful
 */
@Override
public boolean hasAction() {
	return true;
}

}

