package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import java.awt.Point;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * An item which has the ability to move around.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Entity extends Item {
  
  
  /**
   * Check whether a given item has been already collected by this entity.
   * @param item to verify if it was collected
   * @return whether the given item is in the inventory
   */
  public boolean hasColleced(Collectable item);
  
  /**
   * Get the collection of collectable items which might affect where the tile can be accessed.
   * @return unmodifiable list of collected items
   */
  public List<Collectable> getInventory();
  
  /**
   * Get the number of chips (treasures) which have been collected by this entity.
   * @return the number of collected chips
   */
  public int getChipsCollected();
  
  
  /**
   * Get the Coordinates of the tile object where this entity is located.
   * @return the entity position
   */
  public Point getPosition();
  
  /**
   * Change the Coordinates of the tile object where this entity is located.
   * @param newCoordinates the entity new position
   */
  public void setPosition(Point newCoordinates);

}
