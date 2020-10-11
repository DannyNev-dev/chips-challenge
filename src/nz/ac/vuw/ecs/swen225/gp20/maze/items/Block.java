package nz.ac.vuw.ecs.swen225.gp20.maze.items;


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
    for (Collectable item : entity.getInventory()) {
      //Look if this collectable item is a key of the right colour
      //if (item instanceof Key && ((Key) item).getColour() == this.keyType) {
        
      if (key.equals(item)) {
        //Equals method would checks whether the item is a key and if so if the colour is the same
        //however at the moment the block has the colour not the key hence it would either need to
        //be constructed with a key or make a new one before using equals
        
        //Drop the key
        entity.dropCollectable(key);
        //Door could be opened
        return true;
        
        
      }
    }
    //Correct key has not been found
    return false;
  }

}
