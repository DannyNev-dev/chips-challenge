package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import com.google.common.base.Preconditions;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Entity;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Item;



/**
 * This abstract class defines the required properties for a tile. 
 * For example it can be used to represent a cell in a two dimensional board. 
 * Adds a level of abstraction as each type of tile can be generally described with this interface.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public abstract class Tile {
  
  /**
   * An item which might be collectable by the player from this tile.
   * By having an item objects such as player can be on different backgorunds
   * such as the empty tile or the info tile
   */
  protected Item item;
  

  /**
   * Constructor that will call by the sub-types to initiate the logic of collectable items. 
   * 
   * @param item an object which can be collected by the player from this tile
   */
  public Tile(Item item) {
    super();
    this.item = item;
  }

  /**
   * Get unique identifier for this tile. Each concrete type should return a different value.
   * 
   * @return the sequence of characters identifying this tile
   */
  public abstract String getName();

  /**
   * Specify whether an entity could access the given tile, this might depend by
   * the items collected by the entity. Note this method should not alter the
   * items contains in the collection passed as a parameter nor their properties.
   * Indeed, this method should be given an unmodifiable collection.
   * 
   * @param entity the entity which would like to access this tile
   * @return whether this tile can be currently accessed
   */
  public abstract boolean isAccessible(Entity entity);
  
  
  /**
   * Check whether this tile has an item.
   * @return whether the tile contains an Item
   */
  public boolean containsItem() {
    return item != null;
    
  }
  
  /**
   * Check whether this tile has an item of the given type.
   * @param type class which will be checked if item is instance of it
   * @return whether the tile contains an Item
   */
  public boolean containsItemType(Class type) {
    if (containsItem()) {
      return type.isInstance(item);
    }
    return false;
    
  } 
  
  /**
   * Insert an item to this cell.
   * @param newItem item to add to this tile
   * @return the item which was previously in this time, null if it was empty
   * @throws RuntimeException if an item can't be added to this tile
   */
  public Item replaceItem(Item newItem) throws RuntimeException {
    Item previous = item;
    item = newItem;
    return previous;
  }
  /*
  //@Override
  //public abstract Tile clone();
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Tile other = (Tile) obj;
    if (item == null) {
      if (other.item != null) {
        return false;
      }
    } else if (!item.equals(other.item)) {
      return false;
    }
    return true;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((item == null) ? 0 : item.hashCode());
    return result;
  }
  */
 
  
  /*
   * Obtain the Collectable item at this tile and removes it from here.
   * This method should only be invoked when there is an item to pick up.
   * Hence if the method executes correctly the return value is different form null.
   * 
   * @return the item which have just been removed from this tile
   */
  /*
  public Collectable pickupItem() {
    Preconditions.checkState(containsItem(), "There is no item to pick up in this tile");
    Collectable collected = item;
    item = null;
    return collected;
  }
  */

}
