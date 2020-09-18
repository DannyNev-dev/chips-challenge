package nz.ac.vuw.ecs.swen225.gp20.maze.items;


//import static com.google.common.base.Precondition.checkState;

import com.google.common.base.Preconditions;

/**
 * This interface defines the required properties for an object that can be collected. 
 * Moreover it adds a level of abstraction as any collectable object
 * can be generally described with this interface.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Collectable extends Item {
  
  /**
   * Collect this item.
   * @param player who collects it
   * @return whether the item was successfully picked up
   */
  public boolean pickup(Player player);
  
  
  @Override
  public default boolean isAccessible(Entity entity) {
    return true;
  }
}
