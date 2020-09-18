package nz.ac.vuw.ecs.swen225.gp20.maze.items;

import com.google.common.base.Preconditions;

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
  public boolean isAccessible(Entity entity) {
    return true;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean pickup(Player player) {  
    //TODO player will never been in the exact tile as it can contain at most one item
    //Preconditions.checkState(, "Item can't be collected unless the player is in the same tile");
     
    player.collectChip();
    return true;
  }

}
