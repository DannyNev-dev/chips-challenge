package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;
import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;

/**
 * Represent keys which can be take up a whole tile in the board of be collected by an entity.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Key implements Tile, Collectable {

  // Ending of all the key names, stored to improve efficiency
  private static final String suffix = "KeyTile";

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
    super();
    this.colour = colour;
  }

  @Override
  public String getName() {
    return colour.name().toLowerCase() + suffix;
  }

  @Override
  public boolean isAccessible(Collection<Collectable> invetory) {
    return true;
  }

  /**
   * Obtain the colour of this key.
   * 
   * @return the colour identifying the type of this key
   */
  public Key.Colour getColour() {
    return colour;
  }

}
