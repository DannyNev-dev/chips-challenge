package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;


import com.google.common.base.Preconditions;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Collectable;
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

  /**
   * Obtain the Collectable item at this tile and removes it from here.
   * This method should only be invoked when there is an item that can be pick up.
   * Hence if the method executes correctly the return value is different form null.
   * 
   * @return the item which have just been removed from this tile
   * @throws RuntimeException if contains a Collectable item which doesn't implement the interface
   */
  /*
  public Collectable pickupItem() throws RuntimeException {
    Preconditions.checkState(item != null && item.isCollectable(), 
        "There is no item to pick up in this tile");
    
    if (item instanceof Collectable) {
      Collectable collected = (Collectable) item;
      item = null;
      return collected;
    }
    throw new RuntimeException("This tile has a not collectable item "
        + "which however it says that it can be picked up");
  }
  */
  
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
