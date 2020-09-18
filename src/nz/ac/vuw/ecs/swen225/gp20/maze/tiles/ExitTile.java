package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;

/**
 * Exit of the maze, ending tile.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class ExitTile extends Tile {
  
  //All exit tiles have the same name so store it statically to conserve resources
  private static final String name = "exitTile";

  /**
   * Create an exit tile which will never have an item.
   */
  public ExitTile() {
    super(null);
  }
  
  @Override
  public Item replaceItem(Item newItem) {
    throw new RuntimeException("Items can't be added to an exit tile");
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isAccessible(Entity entity) {
    return true;
  }

}
