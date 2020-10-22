package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;

/**
 * Class used to create free tile objects where entities can move without constrains.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class ItemTile extends Tile {
  //Store name shared by all empty tiles
  private static final String emptyTileName = "freeTile";
  
  /**
   * Construct a basic tile which could contain a collectable item.
   * If null is given this cell will be empty 
   * 
   * @param item object which might (or might not) be collectable at this tile
   */
  public ItemTile(Item item) {
    super(item);
  }
  
  @Override
  public String getName() {
    if (item == null) {
      return emptyTileName;
    }
    
    //If this tile contains an item it will be name with its name followed by Tile
    return item.getName() + "Tile";
  }
  
  @Override
  public boolean isAccessible(Entity entity) {
    if (item == null) {
      return true;
    }
    
    return item.isAccessible(entity);
  }

  @Override
  public Tile clone() {
    return new ItemTile(item);
  }

}
