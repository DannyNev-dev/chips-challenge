package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.awt.Point;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Let's Move.
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
   * Get the coordinates of the cell the entity passed through while moving.
   * @return the ordered list of coordinates for each visited tile
   */
  public List<Point> getSteps();
  
  /**
   * Apply a move to a new board and returns it
   * @param board the current state of the board
   * @return a new board where the move have been applied
   */
  public Tile[][] apply(Tile[][] board);
  
  
  /**
   * Get the new cooridates of where this move brings.
   * @param old location before the move is applied
   * @return new location
   */
  public Point getDestination(Point old);

}
