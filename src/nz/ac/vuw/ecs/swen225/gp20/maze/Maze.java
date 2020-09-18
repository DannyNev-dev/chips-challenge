package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.awt.Point;

import com.google.common.base.Preconditions;

import nz.ac.vuw.ecs.swen225.gp20.application.Window;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * This class manages the logic for the maze.
 * 
 * @author Emanuel Evans
 *
 */
public class Maze {
  
  //Coordinates of the Tile containing the player
  private Point playerPos;
  
  //2-D array of tiles grouping each location on the maze
  private Tile[][] board;
  
  //Chips left to collect
  private int target;
  
  
  

  /**
   * Load a maze.
   * 
   * @param playerPos coordinates of the Tile containing the player
   * @param board 2-D array of tiles grouping each location on the maze
   * @param target Chips left to collect
   */
  public Maze(Point playerPos, Tile[][] board, int target) {
    super();
    this.playerPos = playerPos;
    this.board = board;
    this.target = target;
  }


  /**
   * Move the player from it's current position to a different tile in the board.
   * 
   * @param move indicates where should the player move to
   * @return whether the move was successful
   */
  public boolean movePlayer(SingleMove move) {
    return false;
  }


  /**
   * Test setup.
   * 
   * @param args initial arguments
   */
  public static void main(String... args) {
    //Preconditions.checkState(test, "test");
    System.out.println(Key.Colour.BLUE);
    
    //assert(false);
  }

}
