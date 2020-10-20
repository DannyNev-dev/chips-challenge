package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * Create an item which can be used as a remedy again Harmful items.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Remedy implements Collectable {
  
  /**
   * Specify what will this remedy is for.
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public static enum Type {
    /**
     * Permanently extinguish a fire.
     */
    BUCKET,
    /**
     * Cure the player from poison.
     */
    MEDICINE
  }
  
  private final Type remedyType;
  
  
  
  public Remedy(Type remedyType) {
    super();
    this.remedyType = remedyType;
  }

  @Override
  public String getName() {
    switch (remedyType) {
      case BUCKET:
        return "waterBucket";
      default:
        assert remedyType == Type.MEDICINE; 
        return "medicine";
    }
  }
  
  @Override
  public SpecialEvent applyAction(Entity entity) {
    // TODO check player in valid tile
    checkNotNull(entity, "An entity needs to be initialized before doing an action");
    checkArgument(entity instanceof Player, "Only a player can pickup a water bucket");
    
    //Casting is used to avoid the need of an additional method
    ((Player) entity).collectItem(this);
    return SpecialEvent.BUCKET_PICKED_UP;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((remedyType == null) ? 0 : remedyType.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Remedy other = (Remedy) obj;
    if (remedyType != other.remedyType)
      return false;
    return true;
  }
}
