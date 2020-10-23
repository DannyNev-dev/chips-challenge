package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import static com.google.common.base.Preconditions.checkArgument;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Player;

/**
 * Exit of the maze, ending tile.
 * Once Chap reaches this tile, the game level is finished. 
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class ExitTile extends Tile {
  
  //All exit tiles have the same name so store it statically to conserve resources
  private static final String name = "exitTile";

  /**
   * Create an exit tile. It can't be initialised with an item.
   * This will prevent possible errors in the maze creation that might prevent 
   * The game to be finished. For example if a poison is on the exit tile.
   */
  public ExitTile() {
    super(null);
  }
  
  /**
   * Copy constructor to allow the tile to be cloned.
   */
  private ExitTile(Item item) {
    super(item);
    checkArgument(item == null || item instanceof Player, 
        "At the current state only players are supported to be on the exit tile");
   
  }
  
  @Override
  public Item replaceItem(Item newItem) throws RuntimeException {
    checkArgument(item == null || item instanceof Player, 
        "At the current state only players are supported to be on the exit tile");
    return super.replaceItem(newItem);
  }
  
  @Override
  public String getName() {
    if (item == null) {
      return name;
    }
    
    //If this tile contains an item it will be name with its name followed by Tile
    //The suffix will be the same as the tile name but needs capitalisation
    return item.getName() + name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  @Override
  public boolean isAccessible(Entity entity) {
    return entity instanceof Player;
  }

  @Override
  public Tile clone() {
    return new ExitTile(item);
  }

}
