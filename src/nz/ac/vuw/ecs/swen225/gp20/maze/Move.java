package nz.ac.vuw.ecs.swen225.gp20.maze;

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
  public enum Direction{
    /**
     * Towards the North of the board
     */
    UP,
    /**
     * Towards the East of the board
     */
    RIGHT,
    /**
     * Towards the South of the board
     */
    DOWN,
    /**
     * Towards the West of the board
     */
    lEFT
  }

}
