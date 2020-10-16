package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
//import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * Represent keys which can be take up a whole tile in the board of be collected by an entity.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Key implements Collectable {

  // Ending of all the key names, stored to improve efficiency
  private static final String suffix = "Key";

  /**
   * Group and define the finite type of keys that are available.
   * 
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public static enum Colour {
    /**
     * Keys with this type of colour can unlock red doors.
     */
    RED,
    /**
     * Keys with this type of colour can unlock green doors.
     */
    GREEN,
    /**
     * Keys with this type of colour can unlock blue doors.
     */
    BLUE
  }

  private final Key.Colour colour;

  /**
   * Construct a new key.
   * @param colour identifying the type of this key.
   */
  public Key(Colour colour) {
    checkNotNull(colour);
    this.colour = colour;
  }

 

  /**
   * Obtain the colour of this key.
   * 
   * @return the colour identifying the type of this key
   */
  public Key.Colour getColour() {
    return colour;
  }

  @Override
  public String getName() {
    return colour.name().toLowerCase() + suffix;
  }



  @Override
  public SpecialEvent applyAction(Entity entity) {
    // TODO check player in valid tile
    checkNotNull(entity, "An entity needs to be initialized before doing an action");
    checkArgument(entity instanceof Player, "Only a player can pickup a key");
    
    //Casting is used to avoid the need of an additional method
    ((Player) entity).collectItem(this);
    return SpecialEvent.KEY_PICKED_UP;
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((colour == null) ? 0 : colour.hashCode());
    return result;
  }



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
    Key other = (Key) obj;
    if (colour != other.colour) {
      return false;
    }
    return true;
  }
  
  

}
