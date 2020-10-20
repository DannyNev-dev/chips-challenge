package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.Move.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.SingleMove;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import org.junit.jupiter.api.Test;

/**
 * Tests for special events.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class GameEventsTest {
  /**
   * Player collects a chip from level 1.
   */
  @Test
  void collectChipLevelOneTest() {
    Maze maze = MazeTest.mazeSample();
    int target = maze.getChipsLeft();
    //Move the player to a tile containing a chip
    maze.movePlayer(new SingleMove(Direction.LEFT));
    maze.movePlayer(new SingleMove(Direction.LEFT));
    
    //Check the chips left has decrease by exactly one
    assertEquals(target - 1, maze.getChipsLeft());
    
    //Check that a special action was recorded
    assertEquals(maze.getLastSpecialEvent(), SpecialEvent.TREASURE_PICKED_UP);

  }
  
  /**
   * Player collects a key from level 1.
   */
  @Test
  void collectKeyLevelOneTest() {
    Maze maze = MazeTest.mazeSample();
    assertTrue(maze.getPlayerInventory().isEmpty(), 
        "No items should be in the inventory at the start of level one");
    final int target = maze.getChipsLeft();
    //Move the player to a tile containing a key
    maze.movePlayer(new SingleMove(Direction.DOWN));
    maze.movePlayer(new SingleMove(Direction.LEFT));
    maze.movePlayer(new SingleMove(Direction.LEFT));
    
    //Check the chips left has not changed
    assertEquals(target, maze.getChipsLeft());
    //Check that inventory contains only one item
    assertEquals(maze.getPlayerInventory().size(), 1);
    //Check the collected item is a key
    assertTrue(maze.getPlayerInventory().get(0) instanceof Key);
    //Check that a special action was recorded
    assertEquals(maze.getLastSpecialEvent(), SpecialEvent.KEY_PICKED_UP);

  }
  
  /**
   * Player open door.
   */
  @Test
  void openDoorLevelOneTest() {
    Maze maze = MazeTest.mazeSample();
    assertTrue(maze.getPlayerInventory().isEmpty(), 
        "No items should be in the inventory at the start of level one");
    final int target = maze.getChipsLeft();
    //Move the player to a tile containing a key
    maze.movePlayer(new SingleMove(Direction.DOWN));
    maze.movePlayer(new SingleMove(Direction.LEFT));
    maze.movePlayer(new SingleMove(Direction.LEFT));
    //Check the chips left has not changed
    assertEquals(target, maze.getChipsLeft());
    //Check a key has been picked up
    assertTrue(maze.getPlayerInventory().get(0) instanceof Key);
    
    maze.movePlayer(new SingleMove(Direction.DOWN));
    assertTrue(maze.movePlayer(new SingleMove(Direction.LEFT)), 
        "The player hasn't be able to successfully move through the door, "
        + "even though they had the correct key");
    
    
    //Check that the key disappeared since it can only be used once
    assertTrue(maze.getPlayerInventory().isEmpty());
    //Check that a special action was recorded
    assertEquals(maze.getLastSpecialEvent(), SpecialEvent.DOOR_OPENED);
  }
  
  /**
   * Check info tile is giving the message.
   */
  @Test
  void displayInfoTileTest() {
    Maze maze = MazeTest.mazeSample();
    //Move the player to the info tile
    maze.movePlayer(new SingleMove(Direction.UP));
    
    String info = maze.getInfo();
    
    //assertTrue(info != null, "There should be an info message");
    
    assertEquals("Collect all of the treasures to access the exit \n"
        + " The colourful keys unlock the colourful doors ", info);
    
   
    //Check that a special action was recorded
    assertEquals(maze.getLastSpecialEvent(), SpecialEvent.INFO_POINT);
  }
  
  /**
   * Check message is not available when not on info tile.
   */
  @Test
  void invalidDisplayInfoTileTest() {
    Maze maze = MazeTest.mazeSample();
      
    String info = maze.getInfo();
    
    assertTrue(info == null, "There should not be an info message");
    
    //Check that a special action was recorded
    assertEquals(maze.getLastSpecialEvent(), null);
  }

}
