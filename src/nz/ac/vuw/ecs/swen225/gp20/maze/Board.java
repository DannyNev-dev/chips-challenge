package nz.ac.vuw.ecs.swen225.gp20.maze;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Representation of the board state.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Board {
  
  //2-D array of tiles grouping each location on the maze
  private Tile[][] board;
  
  //Initial size of the board, which can't be changed
  private final int width;
  private final int height;
  
  
  
  
  /**
   * Create a new board.
   * @param board 2-D array of tiles grouping each location on the maze
   */
  public Board(Tile[][] board) {
    super();
    this.board = board;
    this.width = board.length;
    this.height = board[0].length;
  }


  /**
   * Get a tile given a pair of coordinates values.
   * @param coordiantes pair of coordinates values
   * @return the tile at the given coordinates in the board
   */
  public Tile getTile(Point coordiantes) {
    checkArgument(isPointInsideBoard(coordiantes), "The given coordinates are outisde the board");
    
    return board[coordiantes.x][coordiantes.y];
  }
  
  
  public boolean isPointInsideBoard(Point pos) {
    return pos.x >= 0 && pos.x < board.length 
        && pos.y >= 0 && pos.y < board[0].length;
  }
  
  
  /**
   *Merge board.
   *@return board
   */
  public Tile[][] getBoard() {
    return board;
  }

}
