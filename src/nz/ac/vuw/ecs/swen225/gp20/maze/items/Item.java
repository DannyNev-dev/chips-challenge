package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * State the properties of an object which could occupy a tile.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Item {
  
  /**
   * Identify whether this item can allow the player to move into it.
   * When that occurs the item is removed from tile
   * @param entity who is trying to access this tile
   * @return whether this item a player to access it's tile
   */
  public boolean isAccessible(Entity entity);
  
  /**
   * Check if this item can be added to the player inventory.
   * By default items can't be collected
   * 
   * @return whether this item can be picked up by the player
   */
  public default boolean isCollectable() {
    return false;
  }
  
  /**
   * Check if this item is linked with an action.
   * By default items do not have an action
   * 
   * @return whether this item has an associated
   */
  public default boolean hasAction() {
    return false;
  }
  
  /**
   * Apply the action linked with this item.
   * This might affects who interacts with this item.
   * This method should be overridden whenever hasAction is overridden
   * 
   * @param entity who is interacting with this item
   * @return This method return a special event or null depending by what happens 
   */
  public default SpecialEvent applyAction(Entity entity) {
    if (!this.hasAction()) {
      throw new UnsupportedOperationException("This item does not have an action!");
    }
    //This exception prevents bugs if hasActions gets overridden but not this method
    throw new UnsupportedOperationException("This item's action has not yet been implemented");
  }

  /**
   * Get unique identifier for this collectable item. 
   * Each concrete type should return a different value.
   * 
   * @return the sequence of characters identifying this collectable item
   */
  public String getName();
}
