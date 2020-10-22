package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.awt.Point;
import java.util.List;

/**
 * Groups the behaviour required by all type of movements.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Move {
  /**
   * Directions in which a move could be made.
   * 
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public enum Direction {
    /**
     * Towards the North of the board.
     */
    UP,
    /**
     * Towards the East of the board.
     */
    RIGHT,
    /**
     * Towards the South of the board.
     */
    DOWN,
    /**
     * Towards the West of the board.
     */
    LEFT
  }
  
  /**
   * Get the new coordinates of where this move brings.
   * @param old location before the move is applied
   * @return new location
   */
  public Point getDestination(Point old);
  
  /**
   * Get the direction the last step is pointing to.
   * It can be used to know where the player will be facing
   * @return were the move is going to
   */
  public Direction getLastDirection();
  
  /**
   * Get the coordinates of the cell the entity passed through while moving.
   * If only one step is made in a move getDestination will be sufficient.
   * This method had been included in the interface for when multiple step moves will be added.
   * In that case knowing the steps taken will be required for displaying the path taken.
   * @return the ordered list of coordinates for each visited tile
   */
  public default List<Point> stepsTaken() {
    throw new UnsupportedOperationException("This feature has not yet been implemented");
  }
  
  /**
   * Apply a move to a new board and returns it.
   * @param board the current state of the board
   * @return a new board where the move have been applied
   */
  public default Board apply(Board board) {
    //This is not the current way in which entities are moved
    throw new UnsupportedOperationException("This feature has not yet been implemented");
  }
}
