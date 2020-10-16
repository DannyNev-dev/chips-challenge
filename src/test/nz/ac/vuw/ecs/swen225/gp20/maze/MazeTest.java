package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.GameState;
import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.IOException;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

class MazeTest {

  @Test
  void movePlayerTest() {
    MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(1, 1));
    
    //check player is in the initial position
    Assume.assumeTrue(mazeWrapper.getBoard()[1][1].containsItemType(Player.class));
    
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.UP));
    //Check new position
    assertTrue(mazeWrapper.getBoard()[0][1].containsItemType(Player.class));
    
    //Check that a special event was not recorded
    assertTrue(mazeWrapper.getMaze().getLastSpecialEvent() == null);
    
  }
  
  @Test
  void consecutiveMovesPlayerTest() {
    MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(1, 1));
    
    //check player is in the initial position
    Assume.assumeTrue(mazeWrapper.getBoard()[1][1].containsItemType(Player.class));
    
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.UP));
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.RIGHT));
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.RIGHT));
    //Check new position
    assertTrue(mazeWrapper.getBoard()[0][3].containsItemType(Player.class));
    //Check that the game has not ended yet
    assertEquals(mazeWrapper.maze.getStatus(), GameState.PLAYING);
  }
  
  
  @Test
  void testMazeLevel() {
    Tile[][] board = makeSimpleBoard(4);
    Player player = new Player(new Point(3, 3));
    board[3][3].replaceItem(player);
    
    Maze maze = new Maze(player, board, 0, 1);
      
    assertEquals(1, maze.getLevel());
  }
  
  @Test
  void invalidMoveExitingBoard() {
    MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(0, 0));
    
    //check player is in the initial position
    Assume.assumeTrue(mazeWrapper.getBoard()[0][0].containsItemType(Player.class));
    
    mazeWrapper.getMaze().movePlayer(new SingleMove(Move.Direction.LEFT));
    //Check position didn't change
    assertTrue(mazeWrapper.getBoard()[0][0].containsItemType(Player.class));
  }
  
  @Test
  void invalidNullMove() {
    MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(4, 4));
    
    try {
    
      mazeWrapper.getMaze().movePlayer(new SingleMove(null));
    } catch (IllegalArgumentException e) {
      assertEquals("The direction of a move can't be undefiened", e.getMessage());
    }
  }
  
  /**
   * When creating a board the player needs to be within the maze boundaries.
   */
  @Test
  void invalidPlayerOutsideBoard() {
    try {
      new Maze(new Player(new Point(4, 4)), makeSimpleBoard(4), 0, 1);
    } catch (RuntimeException e) {
      assertEquals("The player must be within the board boundaries", e.getMessage());
    }
  }
  
  /**
   * The player doesn't just have to be within the boundaries.
   * It also have to be assigned as item in the appropriate tile.
   */
  @Test
  void invalidPlayerNotInBoard() {
    try {
      //Note makeSimpleBoard doesn't assign the player to any tile.
      //Hence since that operation is not made the program throws an exception
      new Maze(new Player(new Point(3, 3)), makeSimpleBoard(4), 0, 1);
      
    } catch (RuntimeException e) {
      assertEquals("The position of the given player doesn't match the player tile in the board", 
          e.getMessage());
    }
  }
  
  void invalidNullPlayer() {
    try {
      //Note makeSimpleBoard doesn't assign the player to any tile.
      //Hence since that operation is not made the program throws an exception
      new Maze(null, makeSimpleBoard(4), 0, 1);
      
    } catch (RuntimeException e) {
      assertEquals("There must be a player on the board", e.getMessage());
    }
  }
  
  
  
  
  //----------------------------------------------------------------//
  //                        HELPER METHODS                          //
  //----------------------------------------------------------------//
  
  
  /**
   * Make a board of item tiles.
   * Note that the player is not added
   * @param size width and height of the board
   * @return the create board
   */
  private Tile[][] makeSimpleBoard(int size){
    Tile[][] board = new Tile[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        board[row][col] = new ItemTile(null);
      }
    }
    return board;
  }
  
  /**
   * Allows wider access to the board for testing purpose.
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  private class MazeWrapper {
    private Maze maze;
    private Tile[][] board;
    
    private MazeWrapper(int size, Point playerPos) {
      board = makeSimpleBoard(size);
      Player p = new Player(playerPos);
      
      board[playerPos.x][playerPos.y].replaceItem(p);
      
      maze = new Maze(p, board, 0, 1);
    }
    
    private Maze getMaze(){ 
      return maze; 
    }
    
    private Tile[][] getBoard(){ return board; }
    
  }

  
  //Check there is exactly one playerTile in the board

}
