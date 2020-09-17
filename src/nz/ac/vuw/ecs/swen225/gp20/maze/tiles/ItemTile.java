package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;

import com.google.common.base.Preconditions;

import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;

/**
 * Class used to create free tile objects where entities can move without constrains.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class ItemTile extends Tile {
  private static final String emptyTileName = "freeTile";
  
  /**
   * Construct a basic tile which could contain a collectable item.
   * If null is given this cell will be empty 
   * 
   * @param item object which could be collected at this tile
   */
  public ItemTile(Collectable item) {
    super(item);
  }

  

  @Override
  public String getName() {
    if(item == null) {
      return emptyTileName;
    }
    //If this tile contains an item it will be name with its name followed by Tile
    return item.getName()+"Tile";
  }

  @Override
  public boolean isAccessible(Collection<Collectable> invetory) {
    return true;
  }
  
  /**
   * Obtain the Collectable item at this tile and removes it from here.
   * This method should only be invoked when there is an item to pick up.
   * Hence if the method executes correctly the return value is different form null.
   * 
   * @return the item which have just been removed from this tile
   */
  public Collectable pickupItem() {
    Preconditions.checkState(item != null, "There is no item to pick up in this tile");
    Collectable collected = item;
    item = null;
    return collected;
  }

}
