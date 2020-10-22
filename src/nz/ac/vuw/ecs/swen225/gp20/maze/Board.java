package nz.ac.vuw.ecs.swen225.gp20.maze;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * Representation of the board state.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Board implements Cloneable {
  
  //2-D array of tiles grouping each location on the maze
  private Tile[][] boardData;
  
  //Initial size of the board, which can't be changed
  /**
   * How many columns does the board have.
   */
  public final int width;
  /**
   * How many rows does the board have.
   */
  public final int height;
  
  /**
   * Create a new board.
   * @param board 2-D array of tiles grouping each location on the maze
   */
  public Board(Tile[][] board) {
    super();
    this.boardData = cloneData(board);
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
    
    return boardData[coordiantes.x][coordiantes.y];
  }
  
  /**
   * Check if a point is inside the board.
   * @param pos the point to check
   * @return true if the given point is within the board boundaries, false otherwise
   */
  public boolean isPointInsideBoard(Point pos) {
    return pos.x >= 0 && pos.x < boardData.length 
        && pos.y >= 0 && pos.y < boardData[0].length;
  }
  
  /**
   * Count how many items of the given type there are in the board.
   * @param type the class indicating what items to count
   * @return how many items of the given type there are in the board
   */
  public int countItems(Class<? extends Item> type) {
    int count = 0;
    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        if (boardData[row][col].containsItemType(type)) {
          count++;
        }
      }
    }
    return count;
  }
  
  
  /**
   *Required for compatibility reasons.
   *@return board
   */
  public Tile[][] getBoard() {
    return cloneData(boardData);
  }
  
  /**
   * Make a copy of the 2-D array storing the data of the board.
   * @param data to copy
   * @return a new 2-D array which holds clones of the data Tiles
   */
  private Tile[][] cloneData(Tile[][] data) {
    int rows = data.length;
    int cols = data[0].length;
    Tile[][] copy = new Tile[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        copy[row][col] = data[row][col].clone();
      }
    }
    return copy;
  }
  
  @Override
  public Board clone() {
    return new Board(boardData);
  }

}
