package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;
import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;

/**
 * Class used to create free tile objects where entities can move without constrains.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class FreeTile implements Tile {
  private static final String name = "freeTile";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isAccessible(Collection<Collectable> invetory) {
    return true;
  }

}
