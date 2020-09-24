package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;

/**
 * Tile which displays info when the player stands on it.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class InfoTile extends Tile {
  
  private static final String name = "infoTile";
  private final String info;
  
  /**
   * Create an info tile which gives hints about the current level to the player.
   * @param info text to display
   */
  public InfoTile(String info) {
    super(null);
    this.info = info;
  }
  
  /**
   * Create an info tile which gives hints about the current level to the player.
   * @param info text to display
   * @param item Only an Entity is allowed to stand on the info tile
   */
  public InfoTile(String info, Item item) {
    super(item);
    this.info = info;
  }

  /**
   * Get the hint for this level.
   * @return the message to show the player
   */
  public String getInfo() {
    return info;
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
    return true;
  }

  
  
  
  
  
  

}