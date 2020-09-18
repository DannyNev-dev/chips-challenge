package nz.ac.vuw.ecs.swen225.gp20.maze.items;

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
   * 
   * @return whether this item can be picked up by the player
   */
  public default boolean isCollectable() {
    return false;
  }

  /**
   * Get unique identifier for this collectable item. 
   * Each concrete type should return a different value.
   * 
   * @return the sequence of characters identifying this collectable item
   */
  public String getName();
}
