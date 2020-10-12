package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;

/**
 * Class used to create wall objects which prevent entity to pass across them.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Wall extends Tile {
  
  //All walls have the same name so store it statically to conserve resources
  private static final String name = "wallTile";
  
  /**
   * Create a wall in the maze.
   */
  public Wall() {
    super(null);
  }

  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public Item replaceItem(Item newItem) {
    throw new UnsupportedOperationException("Items can't be added to a wall");
  }

  @Override
  public boolean isAccessible(Entity entity) {
    return false;
  }

}
