package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkNotNull;

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;
import nz.ac.vuw.ecs.swen225.gp20.maze.items.Remedy.Type;

/**
 * An item that when players interacts with these items they will
 * be affected in a negative way. 
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Harmful implements Item {
  
  /**
   * Types of harmful items. This will determine how the player is affected. 
   * @author Emanuel Evans (ID: 300472656)
   *
   */
  public static enum DangerType {
    /**
     * A fire which will burn the player.
     */
    FIRE,
    /**
     * A poisoned item that if the player tries to collect will die.
     */
    POISON
  }

  private final String name;
  
  private final DangerType type;
  
  

  /**
   * Create an harmful item.
   * @param type of danger used to distinguish the tile appearance
   */
  public Harmful(DangerType type) {
    checkNotNull(type, "The type of danger must be well defined");
    this.name = type.name().toLowerCase();
    this.type = type;
  }

  @Override
  public boolean isAccessible(Entity entity) {
    return entity instanceof Player;
  }
  
  @Override
  public boolean hasAction() {
    return true;
  }
  
  @Override
  public SpecialEvent applyAction(Entity entity) {
    checkNotNull(entity, "An entity needs to be initialized before doing an action");
    
    
    if (entity instanceof Player) {
      switch (type) {
        case FIRE: 
          if (entity.getInventory().contains(new Remedy(Type.BUCKET))) {
            entity.dropCollectable(new Remedy(Type.BUCKET));
            return SpecialEvent.FIRE_EXTINGUISHED;
          }
          return SpecialEvent.CHAP_DIED_BURNT;
        case POISON:
          if (entity.getInventory().contains(new Remedy(Type.MEDICINE))) {
            entity.dropCollectable(new Remedy(Type.MEDICINE));
            return SpecialEvent.POISONED_CURED;
          }
          return SpecialEvent.CHAP_DIED_POISONED;
        default:
          return null;
      }
    }
    
    return null;
  }

  @Override
  public String getName() {
    return name;
  }

}
