package nz.ac.vuw.ecs.swen225.gp20.maze;


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
public interface Collectable {
  
  /**
   * Add this item to the player inventory.
   * @param player g
   * @return gStr
   */
  public default boolean pickup(String player) {  
    boolean test = true;
    Preconditions.checkState(test, "Item can't be collected unless the player is in the same tile");
     
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
