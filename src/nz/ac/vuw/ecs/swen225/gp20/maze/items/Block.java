package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * Create door objects which entity can pass across if they have collected the right key.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Block implements Item {
  //private final Key.Colour keyType;
  private final Key key;

  // Ending of all the key names, stored to improve efficiency
  private static final String suffix = "Locked";

  /**
   * Create a new looked tile with the colour of the key required to unlock them.
   * 
   * @param keyType the type of key required to open this door
   */
  public Block(Key.Colour keyType) {
    this.key = new Key(keyType);
  }
  
  /**
   * Create a new looked tile with the key required to unlock them.
   * Note that two keys with the same colour are equals
   * 
   * @param key the type of key required to open this door
   */
  public Block(Key key) {
    this.key = key;
  }

  @Override
  public String getName() {
    return key.getColour().name().toLowerCase() + suffix;
  }

  @Override
  public boolean isAccessible(Entity entity) {
    checkNotNull(entity, "An entity needs to be initialized before checking where it can move");
    //Look if this collectable item is a key of the right colour
    //Note contains uses equals method, two keys of the same colour are equals
    return entity.getInventory().contains(key);
    
  }
  
  
  @Override 
  public boolean hasAction() {
    return true;
  }
  
  @Override
  public SpecialEvent applyAction(Entity entity) {
    checkState(isAccessible(entity), 
        "The given entity doesn't have the requirment to remove this block (open door)");
    //Use the key to remove this block and remove it from the inventory
    entity.dropCollectable(key);
    return SpecialEvent.DOOR_OPENED;
    
  }

}
