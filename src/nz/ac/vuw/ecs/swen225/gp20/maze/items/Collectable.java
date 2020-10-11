package nz.ac.vuw.ecs.swen225.gp20.maze.items;

//import static com.google.common.base.Precondition.checkState;

import com.google.common.base.Preconditions;

/**
 * This interface defines the required properties for an object that can be
 * collected. Moreover it adds a level of abstraction as any collectable object
 * can be generally described with this interface.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Collectable extends Item {

  /**
   * Collect this item.
   * 
   * @param player who collects it
   * @return whether the item was successfully picked up
   */
  public boolean pickup(Player player);

  /**
   * Check if this item can be added to the player inventory.
   * By default Collectable can be collected
   * 
   * @return whether this item can be picked up by the player
   */
  public default boolean isCollectable() {
    return true;
  }

  /**
   * Identify whether this item can allow the player to move into it.
   * When that occurs the item is removed from tile
   * By default accessible only by players not other Entity. 
   * 
   * @param entity who is trying to access this tile
   * @return whether this item a player to access it's tile
   */
  public default boolean isAccessible(Entity entity) {
    return entity instanceof Player;
  }
}
