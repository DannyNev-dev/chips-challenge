package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful.DangerType;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key.Colour;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy.Type;
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
  
  /**
   * Test that the fire can be extinguished with the bucket.
   */
  @Test
  void extinguishFireTest() {
    Player player = new Player(new Point(5, 2));
    Player twin = player.clone();
    Assume.assumeTrue(player.equals(twin));
    
    Tile fire = new ItemTile(new Harmful(DangerType.FIRE));
    Tile bucket = new ItemTile(new Remedy(Type.BUCKET));
    
    //Check that even if the player doesn't have the bucket 
    //it could still move into the fire, of course it would then cause the player to die
    assertTrue(fire.isAccessible(player));
    
    Assume.assumeTrue(new Remedy(Type.BUCKET).equals(new Remedy(Type.BUCKET)));
    //Pick up the bucket
    bucket.applyAction(player);
    Assume.assumeTrue(player.getInventory().contains(new Remedy(Type.BUCKET)));
    //The players should now be different
    Assume.assumeFalse(player.equals(twin));
    
    assertEquals(SpecialEvent.CHAP_DIED_BURNT, fire.applyAction(twin));
    
    assertEquals(SpecialEvent.FIRE_EXTINGUISHED, fire.applyAction(player));
    
    //Check that the bucket didn't affect whether the player can access the fire tile
    assertTrue(fire.isAccessible(player));
    
    
  }
  
  /**
   * Test that the poison can be cured with the medicine.
   */
  @Test
  void poisonCuredTest() {
    Player player = new Player(new Point(5, 2));
    Player twin = player.clone();
    Assume.assumeTrue(player.equals(twin));
    
    Tile poison = new ItemTile(new Harmful(DangerType.POISON));
    Tile medicine = new ItemTile(new Remedy(Type.MEDICINE));
    
    //Check that even if the player doesn't have the medicine 
    //it could still move into the poison, of course it would then cause the player to die
    assertTrue(poison.isAccessible(player));
    
    Assume.assumeTrue(new Remedy(Type.MEDICINE).equals(new Remedy(Type.MEDICINE)));
    //Pick up the medicine
    medicine.applyAction(player);
    Assume.assumeTrue(player.getInventory().contains(new Remedy(Type.MEDICINE)));
    //The players should now be different
    Assume.assumeFalse(player.equals(twin));
    
    assertEquals(SpecialEvent.CHAP_DIED_POISONED, poison.applyAction(twin));
    
    assertEquals(SpecialEvent.POISONED_CURED, poison.applyAction(player));
    
    //Check that the medicine didn't affect whether the player can access the poison tile
    assertTrue(poison.isAccessible(player));
    
    
  }
  
  @Test
  void sameColourKeysEqualsTest() {
    Key key1 = new Key(Key.Colour.GREEN);
    Key key2 = new Key(Key.Colour.GREEN);
    
    assertTrue(key1.equals(key2));
    assertEquals(key1.hashCode(), key2.hashCode());
    assertTrue(key1.isCollectable());
  }
  
  @Test
  void differentColourKeysNotEqualsTest() {
    Key key1 = new Key(Key.Colour.GREEN);
    Key key2 = new Key(Key.Colour.RED);
    
    assertFalse(key1.equals(key2));
    assertFalse(key1.hashCode() == key2.hashCode());
  }
  
  @Test
  void sameItemEqualsTilesTest() {
    Tile t1 = new ItemTile(new Key(Key.Colour.BLUE));
    Tile t2 = new ItemTile(new Key(Key.Colour.BLUE));
    
    assertTrue(t1.equals(t2));    
  }
  
  @Test
  void exitTileCloneTest() {
    Tile t1 = new ExitTile();
    Tile t2 = t1.clone();
    
    assertTrue(t1.equals(t1));
    assertTrue(t1.hashCode() == t2.hashCode());
  }
  
  @Test
  void equalPlayerTest() {
    Player p1 = new Player(new Point(2, 8));
    Player p2 = new Player(new Point(2, 8));
    
    assertTrue(p1.equals(p2));
    assertTrue(p1.hashCode() == p2.hashCode());
  }
  
  @Test
  void diffrentRemedy() {
    Remedy medicine = new Remedy(Type.MEDICINE);
    Remedy waterBucket = new Remedy(Type.BUCKET);
    
    assertFalse(medicine.equals(waterBucket));
    assertFalse(medicine.hashCode() == waterBucket.hashCode());
    
  }
  
  /**
   * This test is used to ensure all actions are marked as unsupported 
   * Until they are overridden by a concrete type. This characteristics
   * Facilitate the addition of additional Entities.
   */
  @Test
  void entityDefaultTest() {
    Entity newType = new Player(new Point(2, 2));
    
    try {
      newType.getEntity();
      //Ignore test, feature must have been implemented
      Assume.assumeFalse(true);
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
    
    try {
      newType.getFile("test");
      //Ignore test, feature must have been implemented
      Assume.assumeFalse(true); 
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
  
  @Test
  void unmodifiablePlayerTest() {
    Entity original = new Player(new Point(2, 2));
    Entity unmodifiable = (Entity) original.unmodifiableItem(original);
    
    try {
      unmodifiable.dropCollectable(new Key(Colour.BLUE));
      assertTrue(false);
      
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
  
  /**
   * Test the default characteristics of an not accessible item.
   */
  @Test
  void createNewItem() {
    Item myItem = new Item() {
      @Override
      public boolean isAccessible(Entity entity) {
        return false;
      }

      @Override
      public String getName() {
        return "myNewItem";
      }
    };
    
    assertFalse(myItem.isCollectable());
    assertFalse(myItem.hasAction());
    try {
      myItem.applyAction(new Player(new Point(2, 2)));
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
  
  /**
   * Test the default characteristics of an accessible item with an action.
   * This test ensures that if hasAction is overridden but applyAction is not
   * an exception will be thrown
   */
  @Test
  void createNewItemAction() {
    Item myItem = new Item() {
      @Override
      public boolean isAccessible(Entity entity) {
        return true;
      }

      @Override
      public String getName() {
        return "myNewItem";
      }
      
      @Override
      public boolean hasAction() {
        return true;
      }
    };
    
    assertTrue(myItem.hasAction());
    try {
      myItem.applyAction(new Player(new Point(2, 2)));
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
  
  /**
   * Test the creation of an unmodifiable item.
   * However items by default are immutable so there are no
   * method from the interface which will be disabled
   */
  @Test
  void unmodifiableItemTest() {
    Item original = new Key(Colour.BLUE);
    Item unmodifiable = original.unmodifiableItem(original);
    
    assertTrue(unmodifiable.getName().equals(original.getName()));
  }

}
