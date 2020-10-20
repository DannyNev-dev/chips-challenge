package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import java.awt.Point;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp20.maze.tiles.Tile;

/**
 * An item which has the ability to move around.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public interface Entity extends Item, Cloneable {
  
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
  
  
  /**
   * Instruct the current entity to drop the given Collectable.
   * By default this action is not allowed
   * @param toDrop collectable to drop
   * @throws IllegalArgumentException if the given collectable is not in the entity inventory
   */
  public default void dropCollectable(Collectable toDrop) throws IllegalArgumentException {
    throw new UnsupportedOperationException("This entity cannot drop an item");
  }
  
  @Override
  public default boolean hasAction() {
    return true;
  }

}
