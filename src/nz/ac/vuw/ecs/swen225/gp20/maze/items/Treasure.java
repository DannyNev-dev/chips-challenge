package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
    checkNotNull(entity, "An entity needs to be initialized before doing an action");
    checkArgument(entity instanceof Player, "Only a player can pickup a key");
    
    //Casting is used to avoid the need of an additional method
    //Yet the precondition above ensures that the entity will have to be a player
    Player player = (Player) entity;
    int numChipsBefore = player.getChipsCollected();
    
    
    player.collectChip();
    
    assert (player.getChipsCollected() == numChipsBefore + 1);
    
    return SpecialEvent.TREASURE_PICKED_UP;
  }

}
