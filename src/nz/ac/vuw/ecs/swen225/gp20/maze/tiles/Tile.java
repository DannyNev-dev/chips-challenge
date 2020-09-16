package nz.ac.vuw.ecs.swen225.gp20.maze.tiles;

import java.util.Collection;
import nz.ac.vuw.ecs.swen225.gp20.maze.Collectable;

/**
 * This interface defines the required properties for a tile. 
 * For example it can be used to represent a cell in a two dimensional board. 
 * Adds a level of abstraction as each type of tile can be generally described with this interface.
 * 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Tile {

  /**
   * Get unique identifier for this tile. Each concrete type should return a different value.
   * 
   * @return the sequence of characters identifying this tile
   */
  public String getName();

  /**
   * Specify whether an entity could access the given tile, this might depend by
   * the items collected by the entity. Note this method should not alter the
   * items contains in the collection passed as a parameter nor their properties.
   * Indeed, this method should be given an unmodifiable collection.
   * 
   * @param invetory the collection of collectable items which might affect where
   *                 the tile can be accessed
   * @return whether this tile can be currently accessed
   */
  public boolean isAccessible(Collection<Collectable> invetory);

}
