package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
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
      new Player(new ArrayList<Collectable>(), new Point(5, 5), -8);
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
      new Player(null, new Point(5, 5), 13);
    } catch (IllegalArgumentException e) {
      assertEquals("Inventory can't be null when loading a player", e.getMessage());
    }
  }
  
  /**
   * Invalid Player creation with negative number of chips.
   */
  @Test
  void nullPositionInvalidTest() {
    try {
      new Player(null);
    } catch (IllegalArgumentException e) {
      assertEquals("The player must have a well defeined position", e.getMessage());
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

}
