package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;
import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;

/**
 * Class used to create wall objects which prevent entity to pass across them.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Wall implements Tile {
  private static final String name = "wallTile";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isAccessible(Collection<Collectable> invetory) {
    return false;
  }

}
