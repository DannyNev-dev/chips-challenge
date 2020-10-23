package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.GameState;
import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.IOException;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.LevelReader;

import org.junit.Assume;
import org.junit.jupiter.api.Test;

class MazeTest {

  @Test
  void movePlayerTest() {
    //MazeWrapper mazeWrapper = new MazeWrapper(5, new Point(1, 1));
    Maze maze = mazeSample();
    Point start = new Point(8, 8);
    //Check player is in the initial position
    Assume.assumeTrue(maze.getBoardObject().getTile(start).containsItemType(Player.class));
    
    maze.movePlayer(new SingleMove(Direction.DOWN));
    //Check new position
    assertEquals(maze.getPlayerPosition(), new Point(9, 8));
    
    //Check that a special event was not recorded
    assertTrue(maze.getLastSpecialEvent() == null);
    
    //Check that the player orientation is not the same as the move direction
    assertEquals(Direction.DOWN, maze.getPlayer().getOrientation());
  }
  
  @Test
  void consecutiveMovesPlayerTest() {
    Maze maze = mazeSample();
    Point start = new Point(8, 8);
    //Check player is in the initial position
    Assume.assumeTrue(maze.getBoardObject().getTile(start).containsItemType(Player.class));
    
    maze.movePlayer(new SingleMove(Move.Direction.UP));
    maze.movePlayer(new SingleMove(Move.Direction.RIGHT));
    maze.movePlayer(new SingleMove(Move.Direction.RIGHT));
    //Check new position
    assertEquals(maze.getPlayerPosition(), new Point(7, 10));
    
    //Check that a special event was not recorded
    assertTrue(maze.getLastSpecialEvent() == SpecialEvent.KEY_PICKED_UP);
    
    //Check that the game has not ended yet
    assertEquals(maze.getStatus(), GameState.PLAYING);
  }
  
  /**
   * Create a SingleMove with a random direction using the static factory method.
   */
  @Test
  void randomSingleMoveTest() {
    Move randomMove = SingleMove.createRandomlyMove();
    
    assertTrue(randomMove.getLastDirection() != null);
  }
  
  @Test
  void testMazeLevel() {
    Maze maze = mazeSample();
      
    assertEquals(1, maze.getLevel());
  }
  
  @Test
  void invalidMoveExitingBoard() {
    Maze maze = mazeSample();
    //Check player is in the initial position
    Assume.assumeTrue(maze.getPlayerPosition().equals(new Point(8, 8)));
    
    maze.movePlayer(new SingleMove(Move.Direction.LEFT));
    //Check position didn't change
    assertTrue(maze.getPlayerPosition().equals(new Point(8, 7)));
  }
  
  @Test
  void invalidNullMove() {
    try {
      LevelReader loader = new LevelReader(1);
      Maze maze = new Maze(loader);
    
      maze.movePlayer(new SingleMove(null));
      
    } catch (IOException e) {
      Assume.assumeTrue("Test can't run without the definition of level one", false);
    } catch (IllegalArgumentException e) {
      assertEquals("The direction of a move can't be undefiened", e.getMessage());
    }
  }
  
  /**
   * Check that there are no side effects on the board being cloned.
   */
  @Test
  void cloneBoardTest() {
    Maze maze = mazeSample();
    Board board = maze.getBoardObject();
    
    maze.movePlayer(new SingleMove(Direction.DOWN));
    
    Point playerPos = new Point(9, 8);
    
    Assume.assumeTrue(maze.getPlayerPosition().equals(playerPos));
    
    assertFalse(board.getTile(playerPos).containsItemType(Player.class));  
    
  }
  
  
  /**
   * Move player without updating the board.
   */
  @Test
  void invalidPlayerPosition() {
    Maze maze = mazeSample();
    Tile[][] board = maze.getBoard();
    
    Player newPlayer = new Player(new Point(5, 0));
    board[9][0].replaceItem(newPlayer);
    newPlayer.collectChip();
    
    //Check that the changes on the board were not applied to the maze
    //This because the board should have been cloned
    assertTrue(maze.movePlayer(new SingleMove(Direction.DOWN)));
    
  }
  
  
  
  //----------------------------------------------------------------//
  //                        HELPER METHODS                          //
  //----------------------------------------------------------------//
  
  /**
   * Create a new level one maze and catches IOException.
   * @return a new maze for level one
   */
  protected static Maze mazeSample() {
    try {
      return new Maze(1);
    } catch (IOException e) {
      Assume.assumeTrue("Test can't run without the definition of level one", false);
    } catch (Exception e) {
      Assume.assumeTrue("The Maze package couldn't be tested due to an error "
          + "while loading the data from the persistence package", false);
    }
    return null;
  }
 

  
  //Check there is exactly one playerTile in the board

}
