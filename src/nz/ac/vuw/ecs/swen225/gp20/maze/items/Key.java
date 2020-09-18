package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import com.google.common.base.Preconditions;

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
    Preconditions.checkNotNull(colour);
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
  public boolean pickup(Player player) {
    // TODO check player in valid tile
    
    return player.collectItem(this);
  }

}
