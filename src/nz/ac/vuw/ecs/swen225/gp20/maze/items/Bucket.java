package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * Create an item which can be used to permanently extinguish a fire.
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Bucket implements Collectable {
  
  private static final String name = "waterBucket";

  @Override
  public String getName() {
    return name;
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

}
