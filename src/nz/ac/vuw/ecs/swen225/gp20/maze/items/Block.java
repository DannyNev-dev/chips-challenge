package nz.ac.vuw.ecs.swen225.gp20.maze.items;


/**
 * Create door objects which entity can pass across if they have collected the right key.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Block implements Item {
  private final Key.Colour keyType;

  // Ending of all the key names, stored to improve efficiency
  private static final String suffix = "Locked";

  /**
   * Create a new looked tile with the colour of the key required to unlock them.
   * 
   * @param keyType the type of key required to open this door
   */
  public Block(Key.Colour keyType) {
    this.keyType = keyType;
  }

  @Override
  public String getName() {
    return keyType.name().toLowerCase() + suffix;
  }

  @Override
  public boolean isAccessible(Entity entity) {
    for (Collectable item : entity.getInventory()) {
      //Look if this collectable item is a key of the right colour
      if ((item instanceof Key && ((Key) item).getColour() == this.keyType)) {
        //Door could be opened
        return true;
      }
    }
    //Correct key has not been found
    return false;
  }

}
