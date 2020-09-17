package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;
import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;
import nz.ac.vuw.ecs.swen225.gp20.maze.Key;

/**
 * Create door objects which entity can pass across if they have collected the right key.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Door extends Tile {
  private final Key.Colour keyType;

  // Ending of all the key names, stored to improve efficiency
  private static final String suffix = "LockedTile";

  /**
   * Create a new looked tile with the colour of the key required to unlock them.
   * 
   * @param keyType the type of key required to open this door
   */
  public Door(Key.Colour keyType) {
    //Doors never contain items
    super(null);
    this.keyType = keyType;
  }

  @Override
  public String getName() {
    return keyType.name().toLowerCase() + suffix;
  }

  @Override
  public boolean isAccessible(Collection<Collectable> invetory) {
    // TODO Auto-generated method stub
    return false;
  }

}
