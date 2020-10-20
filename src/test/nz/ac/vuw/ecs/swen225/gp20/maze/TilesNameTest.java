package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful.DangerType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Treasure;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy.Type;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;
import org.junit.jupiter.api.Test;

/**
 * Check that the name of the tile are formed properly.
 * This test are particularly useful for checking how the filename of the images should be
 * @author Emanuel Evans (ID: 300472656)
 *
 */
class TilesNameTest {
  
  /**
   * Check name of an empty tile.
   * This is represented as an ItemTile without any item
   */
  @Test
  void emptyTileNameTest() {
    Tile tile = new ItemTile(null);
    assertEquals("freeTile", tile.getName());
  }
  
  /**
   * Check that a basic tile with chip in top returns the right name.
   */
  @Test
  void playerTileNameTest() {
    Tile tile = new ItemTile(new Player(new Point(1, 1)));
    assertEquals("chipTile", tile.getName());
  }
  
  /**
   * Check name of wall tile.
   */
  @Test
  void wallTileNameTest() {
    Tile tile = new Wall();
    assertEquals("wallTile", tile.getName());
  }

  
  /**
   * Check name of exit lock tile.
   */
  @Test
  void exitLockNameTest() {
    Tile tile = new ItemTile(new ExitLock(6));
    assertEquals("exitLockTile", tile.getName());
  }
  
  /**
   * Check name of exit tile with player.
   */
  @Test
  void exitTileNameTest() {
    Tile tile = new ExitTile();
    assertEquals("exitTile", tile.getName());
  }
  
  /**
   * Check name of treasure tile.
   */
  @Test
  void treasureTileNameTest() {
    Tile tile = new ItemTile(new Treasure());
    assertEquals("treasureTile", tile.getName());
  }
  
  /**
   * Check name of info tile.
   */
  @Test
  void infoTileNameTest() {
    Tile tile = new InfoTile("use the arrow keys to move");
    assertEquals("infoTile", tile.getName());
  }
  
  /**
   * Check name of info tile.
   */
  @Test
  void playerInfoTileNameTest() {
    Tile tile = new InfoTile("look for elements of the same colour", new Player(new Point(5, 5)));
    assertEquals("chipInfoTile", tile.getName());
  }
  
  /**
   * Check the locked tile for each block colour.
   */
  @Test
  void blockNameTests() {
    for (Key.Colour type : Key.Colour.values()) {
      Tile locked = new ItemTile(new Block(type));
      
      assertEquals(type.name().toLowerCase() + "LockedTile", locked.getName());
    }
  }
  
  /**
   * Check each the key tile name for each colour.
   */
  @Test
  void keyNameTests() {
    for (Key.Colour type : Key.Colour.values()) {
      Tile keyTile = new ItemTile(new Key(type));
      
      assertEquals(type.name().toLowerCase() + "KeyTile", keyTile.getName());
    }
  }
  
  /**
   * Test the creation of a fire cell.
   */
  @Test
  void fireNameTest() {
    Tile tile = new ItemTile(new Harmful(DangerType.FIRE));
    assertEquals("fireTile", tile.getName());
  }
  
  /**
   * Test the creation of a poison cell.
   */
  @Test
  void poisonNameTest() {
    Tile tile = new ItemTile(new Harmful(DangerType.POISON));
    assertEquals("poisonTile", tile.getName());
  }
  
  /**
   * Test the creation of a water bucket.
   */
  @Test
  void waterBucketNameTest() {
    Tile tile = new ItemTile(new Remedy(Type.BUCKET));
    assertEquals("waterBucketTile", tile.getName());
  }
  
  /**
   * Test the creation of the medicine item.
   */
  @Test
  void medicineNameTest() {
    Tile tile = new ItemTile(new Remedy(Type.MEDICINE));
    assertEquals("medicineTile", tile.getName());
  }

}
