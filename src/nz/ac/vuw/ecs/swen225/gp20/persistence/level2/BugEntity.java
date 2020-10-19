package nz.ac.vuw.ecs.swen225.gp20.persistence.level2;

import java.awt.Point;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
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
  
  //All bugs share the same name, stored to improve efficiency
  private static final String name = "bug";
  private Point position;
  
  /**
   * Return a random adjacent position (coordinates of a tile in the board). 
   * The result will be accessible for the bug.
   * Change to the board are not applied here
   * 
   * @param board state of the board used to decide which the new position will be
   * @return an accessible random adjacent position
   */
  private Point randomAdjacentPos(Board board) {
    Point newPos = null;
    boolean accessible = false;
    
    while (!accessible) {
      //Get a random direction for the move
      Move possibleMove = SingleMove.createRandomlyMove();
      newPos = possibleMove.getDestination(position);
      
      //Check if the bug can access it
      accessible = board.getTile(newPos).isAccessible(this);
    }
    return newPos;
    
  }
  
  @Override
  public String getName() {
    return name;
  }

  public boolean hasColleced(Collectable item) {
    throw new RuntimeException("Unsupported method, bugs have no inventory");
  }

  @Override
  public List<Collectable> getInventory() {
    throw new RuntimeException("Unsupported method, bugs have no inventory");
  }

  @Override
  public int getChipsCollected() {
    throw new RuntimeException("Unsupported method, bugs can't collect chips");
  }

  @Override
  public Point getPosition() {
    return position;
  }
  
  @Override
  public void setPosition(Point newCoordinates) {
    //TODO not quite safe
    position = newCoordinates;
  }

  public boolean pickup(Player player) {
    // TODO Auto-generated method stub
    return false;
  }

}

