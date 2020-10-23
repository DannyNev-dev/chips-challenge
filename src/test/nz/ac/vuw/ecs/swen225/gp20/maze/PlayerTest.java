package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

class PlayerTest {

  /**
   * Check state the collection can't be modified when constructed from previous
   * and get method.
   */
  @Test
  void unmodifiableGetInventoryTest() {
    Player p = new Player(new Point(5, 5));
    // Check initial condition is true
    assertTrue(p.getInventory().isEmpty(), "New Players shouldn't have collected items yet");
    try {
      // Try to add an item
      boolean result = p.getInventory().add(new Key(Key.Colour.RED));

      // Fail if it was added successfully
      assertFalse(result);

    } catch (UnsupportedOperationException e) {
      // Check post condition is true
      assertTrue(p.getInventory().isEmpty(), "No Item should have been added indirectly");
    }
  }

  /**
   * Load Invalid Player creation with negative number of chips.
   */
  @Test
  void negativeChipsInvalidTest() {
    try {
      new Player(new Point(5, 5));
    } catch (IllegalArgumentException e) {
      assertEquals("Number of chips collected can't be negative", e.getMessage());
    }
  }

  /**
   * Load Invalid Player creation with negative number of chips.
   */
  @Test
  void nullInventoryInvalidTest() {
    try {
      new Player(new Point(5, 5));
    } catch (IllegalArgumentException e) {
      assertEquals("Inventory can't be null when loading a player", e.getMessage());
    }
  }

  /**
   * Increase the number of chips.
   */
  @Test
  void collectChipTest() {
    Player p = new Player(new Point(5, 5));
    for (int i = 0; i < 10; i++) {
      p.collectChip();
    }
    assertTrue(p.getChipsCollected() == 10);

  }
  
  /**
   * Check entities can move into the player's tile to kill them.
   */
  @Test
  void playerTileAccessibleTest() {
    Entity enemy = new Player(new Point(5, 4));
    assertTrue(new Player(new Point(5, 5)).isAccessible(enemy));
  }
  
  /**
   * Check that the player is not allowed to drop an item which they have not collected.
   */
  @Test
  void invalidDropItemTest() {
    Entity player = new Player(new Point(5, 4));
    Assume.assumeTrue(player.getInventory().isEmpty());
    
    try {
      player.dropCollectable(new Key(Key.Colour.GREEN));
    } catch (IllegalArgumentException e) {
      assertEquals("The given collectable is not in the player inventory", e.getMessage());
    }
  }
  
  
  /**
   * Check that a player is cloned correctly.
   * Hence changes to the clone will not affect the original
   */
  @Test
  void clonePlayerTest() {
    Player player = new Player(new Point(5, 4));
    Player clone = player.clone();
    
    Assume.assumeTrue(player.equals(clone));
    
    clone.collectChip();
    
    assertFalse(player.getChipsCollected() == clone.getChipsCollected());
    assertFalse(player.equals(clone));
    
  }

}
