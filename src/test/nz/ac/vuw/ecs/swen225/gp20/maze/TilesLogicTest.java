package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful.DangerType;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key.Colour;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

/**
 * Test that the tiles are behaving as expected.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class TilesLogicTest {
  
  /**
   * Check that is invalid adding an item to a wall.
   */
  @Test
  void addItemWallIvalidTest() {
    Tile tile = new Wall();
    try {
      tile.replaceItem(new Key(Colour.GREEN));
    } catch (RuntimeException e) {
      assert (true);
    }
  }
  
  /**
   * Check the player can move into the exit tile.
   */
  @Test
  void exitTilePlayerNameTest() {
    Player player = new Player(new Point(1, 1));
    Tile finalDestination = new ExitTile();
    
    assertTrue(finalDestination.isAccessible(player));
    finalDestination.replaceItem(player);
    assertEquals("chipExitTile", finalDestination.getName());
  }
  
  /**
   * Check block (door) is not accessible if the player doesn't have the key.
   */
  @Test
  void blockNotAccessibleTest() {
    int target = 6;
    Tile tile = new ItemTile(new Block(new Key(Key.Colour.BLUE)));
    Player player = new Player(new Point(1, 1));
    
    Assume.assumeTrue(player.getChipsCollected() == 0);
    
    assertFalse(tile.isAccessible(player), 
        "The tile should not be accessible since the player doesn't have the key");
  }
  
  /**
   * Check exit lock tile is not accessible if there are still treasure to collect.
   */
  @Test
  void exitLockNotAccessibleTest() {
    int target = 6;
    Tile tile = new ItemTile(new ExitLock(target));
    Player player = new Player(new Point(1, 1));
    
    Assume.assumeTrue(player.getChipsCollected() == 0);
    
    assertFalse(tile.isAccessible(player), 
        "The tile should not be accessible because not enough treasures have been collected");
  }
  
  @Test
  void exitLockAccessibleTest() {
    int target = 0;
    Tile tile = new ItemTile(new ExitLock(target));
    Player player = new Player(new Point(1, 1));
    
    Assume.assumeTrue(player.getChipsCollected() == 0);
    
    assertTrue(tile.isAccessible(player), 
        "The tile should be accessible because enough treasures have been collected");
  }
  
  @Test
  void fireTileActionTest() {
    Tile tile = new ItemTile(new Harmful(DangerType.FIRE));
    Player player = new Player(new Point(2, 3));
    
    assertTrue(tile.isAccessible(player));
    assertTrue(tile.hasAction());
    assertEquals(tile.applyAction(player), SpecialEvent.CHAP_DIED_BURNT);
    
  }
  
  @Test
  void poisonTileActionTest() {
    Tile tile = new ItemTile(new Harmful(DangerType.POISON));
    Player player = new Player(new Point(2, 3));
    
    assertTrue(tile.isAccessible(player));
    assertTrue(tile.hasAction());
    assertEquals(tile.applyAction(player), SpecialEvent.CHAP_DIED_POISONED);
    
  }
  
  @Test
  void sameColourKeysEqualsTest() {
    Key key1 = new Key(Key.Colour.GREEN);
    Key key2 = new Key(Key.Colour.GREEN);
    
    assertTrue(key1.equals(key2));
    assertEquals(key1.hashCode(), key2.hashCode());
    assertTrue(key1.isCollectable());
  }
  
  void differentColourKeysNotEqualsTest() {
    Key key1 = new Key(Key.Colour.GREEN);
    Key key2 = new Key(Key.Colour.RED);
    
    assertFalse(key1.equals(key2));
    assertFalse(key1.hashCode() == key2.hashCode());
  }

}
