package test.nz.ac.vuw.ecs.swen225.gp20.maze;

import static nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful.DangerType;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Block;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.ExitLock;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Harmful;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Key.Colour;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Treasure;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ExitTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.InfoTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.ItemTile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Wall;
import org.junit.jupiter.api.Test;

class TilesTest {

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
   * Check name of exit lock tile.
   */
  @Test
  void exitLockNameTest() {
    Tile tile = new ItemTile(new ExitLock(6));
    assertEquals("exitLockTile", tile.getName());
  }
  
  /**
   * Check name of exit tile.
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

}
