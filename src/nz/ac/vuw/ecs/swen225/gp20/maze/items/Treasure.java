package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
//import static com.google.common.base.Preconditions;
//import static nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent; //imported by interface

import nz.ac.vuw.ecs.swen225.gp20.maze.Maze.SpecialEvent;

/**
 * Treasures are the chips which have to be collected to finish the game.
 * They will not go in the entity inventory
 * @author Emanuel Evans (ID: 300472656)
 *
 */
public class Treasure implements Collectable {
  
  //All treasures have the same name so store it statically to conserve resources
  private static final String name = "treasure";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public SpecialEvent applyAction(Entity entity) {
    //TODO player will never been in the exact tile as it can contain at most one item
    //Preconditions.checkState(, "Item can't be collected unless the player is in the same tile");
    
    checkNotNull(entity, "An entity needs to be initialized before doing an action");
    checkArgument(entity instanceof Player, "Only a player can pickup a key");
    
    //Casting is used to avoid the need of an additional method
    ((Player) entity).collectChip();
    return SpecialEvent.TREASURE_PICKED_UP;
  }

}
