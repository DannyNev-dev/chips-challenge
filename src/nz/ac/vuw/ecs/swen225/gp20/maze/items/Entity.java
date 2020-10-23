package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import java.awt.Point;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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
  
  /**
   * Obtain the concrete implementation of this interface.
   * @return the current entity
   * @throws UnsupportedOperationException by default this feature is not available
   */
  public default Entity getEntity() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This is not a standard features! It can "
        + "be activated to incorporate extentions. It particular it allows the renderer"
        + "to obtain more information about this entity");
  }
 
  /**
   * Get the path for this entity data.
   * @param prefix location of generic data folder
   * @return the path for this entity data
   * @throws UnsupportedOperationException by default this feature is not available
   */
  public default String getFile(String prefix) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This is not a standard features! It can "
        + "be activated to incorporate extentions. It particular it allows the renderer"
        + "to identify where are the files used to represent the extentison.");
  }
  
  @Override
  public default Item unmodifiableItem(Item item) {
    InvocationHandler handler = new InvocationHandler() {
      
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        boolean isMutatingState = methodName.startsWith("collect") || methodName.startsWith("set")
            || methodName.startsWith("drop");
        if (isMutatingState) {
          throw new UnsupportedOperationException();
        } else {
          return method.invoke(item, args);
        }
      }
    };
    return (Entity) Proxy.newProxyInstance(
      Entity.class.getClassLoader(),
      new Class[]{Entity.class},
      handler
    );
  }
  
  @Override
  public default boolean hasAction() {
    return true;
  }

}
